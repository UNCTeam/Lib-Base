package fr.teamunc.base_unclib.controllers;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.models.tickloops.IUNCExpression;
import fr.teamunc.base_unclib.models.tickloops.UNCPhase;
import org.bukkit.scheduler.BukkitRunnable;
import fr.teamunc.base_unclib.utils.helpers.Message;
import fr.teamunc.base_unclib.models.tickloops.GameActualState;

import java.util.*;

/**
 * This class is the controller of the game tick loop.
 * register your tick action for each phase of the game.
 * The tick action is a function that will be called each tick.
 * register a final lambda expression to execute at the end of the game.
 */
public class UNCPhaseController extends BukkitRunnable {
    private IUNCExpression finalExpression;
    private HashMap<Integer, UNCPhase> tickLoops;
    public UNCPhaseController(){
        super();
        this.tickLoops = new HashMap<>();
    }

    public GameActualState getGameActualState() {
        return BaseLib.getGameActualState();
    }

    @Override
    public void run() {
        if (tickLoops == null) return;
        if (tickLoops.size() == 0) return;

        if (tickLoops.containsKey(getGameActualState().getActualPhaseNumber())) {
            getActualPhaseInstance().onTick();
        } else {
            Message.Get().broadcastMessageToConsole("Phase number " + getGameActualState().getActualPhaseNumber() + " not found");
            return;
        }

        // passing seconds
        getGameActualState().passSecondInPhase();

        // check if the phase is over
        if (getGameActualState().getActualSecondsInPhase() >= getActualPhaseInstance().getMaxTick()) {
            // phase is over
            this.nextPhase();
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
     * @param tickLoop the tick action class
     */
    public void registerTickLoop(Integer phaseImportance, UNCPhase tickLoop) {
        tickLoops.put(phaseImportance,tickLoop);
    }

    /**
     * register a final lambda expression to execute at the end of the game.
     * @param finalExpression
     */
    public void registerFinalExpression(IUNCExpression finalExpression) {
        this.finalExpression = finalExpression;
    }

    public void StartTickAction() {
        if (!BaseLib.IsInit()) return;

        this.runTaskTimer(
                BaseLib.getPlugin(),
                0L,
                20L
        );
    }
    public UNCPhase getActualPhaseInstance() {
        if (tickLoops == null) return null;
        if (tickLoops.size() == 0) return null;

        if (tickLoops.containsKey(getGameActualState().getActualPhaseNumber())) {
            return tickLoops.get(getGameActualState().getActualPhaseNumber());
        }
        return null;
    }
    public int getHourLeft() {
        int secRestantes = getActualPhaseInstance().getMaxTick() - getGameActualState().getActualSecondsInPhase();

        return (secRestantes - (secRestantes % 3600)) / 3600;
    }
    public int getMinuteLeft() {
        int secRestantes = getActualPhaseInstance().getMaxTick() - getGameActualState().getActualSecondsInPhase();

        return (secRestantes % 3600 - (secRestantes % 60) )/ 60;
    }
    public int getSecondLeft() {
        int secRestantes = getActualPhaseInstance().getMaxTick() - getGameActualState().getActualSecondsInPhase();

        return secRestantes % 60;
    }

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
        this.cancel();
    }

    public boolean startGame() {
        if (tickLoops == null) return false;
        if (tickLoops.size() == 0) return false;

        if (tickLoops.containsKey(0)) {
            getGameActualState().setActualPhaseNumber(0);
            getGameActualState().resetTimeInPhaseAt(0);
            getActualPhaseInstance().onPhaseStart();
            return true;
        }

        return true;
    }

    public boolean stopGame() {
        if (tickLoops == null) return false;
        if (tickLoops.size() == 0) return false;
        if (finalExpression != null) {
            finalExpression.execute();
        }

        BaseLib.resetActualGameState();

        cancelTickAction();
        return true;
    }
}
