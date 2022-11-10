package fr.teamunc.base_unclib.controllers;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.models.tickloops.UNCPhase;
import lombok.Getter;
import fr.teamunc.base_unclib.utils.helpers.Message;
import fr.teamunc.base_unclib.models.tickloops.GameActualState;
import lombok.var;

import javax.annotation.Nullable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * This class is the controller of the game tick loop.
 * register your tick action for each phase of the game.
 * The tick action is a function that will be called each tick.
 */
public class UNCPhaseController {
    @Getter
    private GameActualState gameActualState;
    /**
     * key int is the priority of the phase
     */
    @Getter
    private HashMap<Integer, UNCPhase> uncPhasesRegistered;

    private UNCPhaseRunnable runnable;

    public UNCPhaseController(GameActualState gameActualState){
        super();
        this.gameActualState = gameActualState;
        this.uncPhasesRegistered = new HashMap<>();

        // check if the game is started already and if the timer need to be followed
        if (gameActualState.getActualPhaseNumber() != -1) {
            StartTickAction();
        }
    }

    /**
     * Register a tick action for a phase.
     * note that the phase number must be unique.
     *
     * @param phaseImportance the phase number (must be unique)
     *                        the phase with the lowest number will be executed first.
     *
     *                        this phase 0 is when the game is waiting for players. (lobby)
     * @param phase the phase class
     */
    public void registerTickLoop(Integer phaseImportance, UNCPhase phase) {
        uncPhasesRegistered.put(phaseImportance,phase);
    }

    public void StartTickAction() {
        if (!BaseLib.isInit()) return;
        if (runnable != null && !runnable.isCancelled()) return;

        this.runnable = new UNCPhaseRunnable();
        this.runnable.runTaskTimer(
                BaseLib.getPlugin(),
                1L,
                1L);
    }

    @Nullable
    public UNCPhase getActualPhaseInstance() {
        if (!BaseLib.isInit()) return null;
        if (uncPhasesRegistered.size() == 0) return null;

        if (uncPhasesRegistered.containsKey(getGameActualState().getActualPhaseNumber())) {
            return uncPhasesRegistered.get(getGameActualState().getActualPhaseNumber());
        }
        return null;
    }

    /**
     * Calculate an approximated ending date if the actual phase is not with a DueDate System
     * Give the DueDate if it's a DueDate phase
     *
     * Note : Should be used only for display/info
     * @return a LocalDateTime
     */
    public LocalDateTime getEndingDate() {
        if (!BaseLib.isInit()) return null;
        UNCPhase actualPhase = getActualPhaseInstance();

        if (actualPhase == null)
            return LocalDateTime.of(0,0,0,0,0);

        if (actualPhase.isWithADueDate()) {
            return actualPhase.getEndingDate();
        } else {
            double trueSecondesTimeLeft = (actualPhase.getMaxTick() - getGameActualState().getActualTicksInPhase()) * 50;
            return LocalDateTime.now().plus(Math.round(trueSecondesTimeLeft),ChronoUnit.MILLIS).truncatedTo(ChronoUnit.SECONDS);
        }
    }

    public Duration getDurationLeft() {
        if (!BaseLib.isInit()) return null;

        UNCPhase actualPhase = getActualPhaseInstance();

        if (actualPhase == null)
            return Duration.ofSeconds(0);

        return Duration.between(LocalDateTime.now(),getEndingDate());
    }

    public int getPhaseNumber() {
        if (!BaseLib.isInit()) return -1;
        return getGameActualState().getActualPhaseNumber();
    }
    public int getActualTicksInPhase() {
        return getGameActualState().getActualTicksInPhase();
    }

    /**
     * switch the phase of the game
     * trigger the ending #onPhaseEnd method of the actual phase
     * go to the phase numbered as : actualPhaseNumber + 1
     * or stopGame if the actual phase as isLastPhase to true
     * resetTickInPhase to 0
     */
    public void nextPhase() {
        getActualPhaseInstance().onPhaseEnd();
        getGameActualState().resetTimeInPhaseAt(0);

        if (getActualPhaseInstance().isLastPhase()) {
            stopGame();
        } else {
            getGameActualState().setActualPhaseNumber(getGameActualState().getActualPhaseNumber() + 1);
            getActualPhaseInstance().onPhaseStart();
        }
    }

    public void setPhase(int phase, boolean resetingTickPhase, boolean executingPhaseEnd) {

        if (executingPhaseEnd)
            getActualPhaseInstance().onPhaseEnd();

        if (resetingTickPhase)
            getGameActualState().resetTimeInPhaseAt(0);

        getGameActualState().setActualPhaseNumber(phase);
    }

    public void cancelTickAction() {
        if (runnable == null || runnable.isCancelled()) return;

        this.runnable.cancel();
    }

    public boolean isActualPhaseOver() {
        return (getActualPhaseInstance().isWithADueDate() &&
                (getActualPhaseInstance().getEndingDate().isBefore(LocalDateTime.now()) ||
                        getActualPhaseInstance().getEndingDate().isEqual(LocalDateTime.now()))) ||
                (!getActualPhaseInstance().isWithADueDate() &&
                        (getGameActualState().getActualTicksInPhase() >= getActualPhaseInstance().getMaxTick()));
    }

    /**
     * start the game, don't and return false if no phases are registered
     * need a phase 0 to start
     * start the runnable
     * @return
     */
    public boolean startGame() {
        if (uncPhasesRegistered == null || uncPhasesRegistered.size() == 0) {
            Message.Get().broadcastMessageToConsole("No phase registered");
            return false;
        }

        if (uncPhasesRegistered.containsKey(0)) {
            getGameActualState().setActualPhaseNumber(0);
            getGameActualState().resetTimeInPhaseAt(0);
            getActualPhaseInstance().onPhaseStart();

            StartTickAction();
            return true;
        } else {
            Message.Get().broadcastMessageToConsole("Phase number 0 not found");
            return false;
        }
    }

    /**
     * stop the game, ending the runnable + reseting game state (set actualPhase to -1)
     * @return
     */
    public boolean stopGame() {
        if (!BaseLib.isInit()) return false;
        if (uncPhasesRegistered.size() == 0) return false;

        resetActualGameState();

        // stop runnable
        cancelTickAction();
        return true;
    }

    public void resetActualGameState() {
        gameActualState = new GameActualState();
        gameActualState.save("gameActualState");
    }

    public void save(String gameActualState) {
        this.gameActualState.save(gameActualState);
    }
}
