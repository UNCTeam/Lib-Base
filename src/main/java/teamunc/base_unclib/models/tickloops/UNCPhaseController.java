package teamunc.base_unclib.models.tickloops;

import org.bukkit.scheduler.BukkitRunnable;
import teamunc.base_unclib.BaseLib;
import teamunc.base_unclib.models.utils.helpers.Message;
import teamunc.base_unclib.models.utils.helpers.gameStates.GameActualState;

import java.util.HashMap;

/**
 * This class is the controller of the game tick loop.
 * register your tick action for each phase of the game.
 * The tick action is a function that will be called each tick.
 *
 */
public class UNCPhaseController extends BukkitRunnable {

    private HashMap<Integer, UNCPhase> tickLoops;
    private GameActualState gameActualState;
    public UNCPhaseController(GameActualState gameActualState){
        super();
        this.gameActualState = gameActualState;
    }

    @Override
    public void run() {
        if (tickLoops == null) return;
        if (tickLoops.size() == 0) return;

        if (tickLoops.containsKey(gameActualState.getActualPhaseNumber())) {
            getActualPhaseInstance().onTick();
        }
    }

    public void registerTickLoop(Integer phaseImportance , UNCPhase tickLoop) {
        tickLoops.put(phaseImportance,tickLoop);
    }

    public void StartTickAction() {
        if (!BaseLib.IsInit()) return;

        this.runTaskTimer(
                BaseLib.getPlugin(),
                0L,
                1L
        );
    }

    public UNCPhase getActualPhaseInstance() {
        if (tickLoops == null) return null;
        if (tickLoops.size() == 0) return null;

        if (tickLoops.containsKey(gameActualState.getActualPhaseNumber())) {
            return tickLoops.get(gameActualState.getActualPhaseNumber());
        }
        return null;
    }

    public int getHourLeft() {
        int secRestantes = getActualPhaseInstance().getMaxTick() - gameActualState.getActualTickInPhase();

        return (secRestantes - (secRestantes % 3600)) / 3600;
    }

    public int getMinuteLeft() {
        int secRestantes = getActualPhaseInstance().getMaxTick() - gameActualState.getActualTickInPhase();

        return (secRestantes % 3600 - (secRestantes % 60) )/ 60;
    }

    public int getSecondLeft() {
        int secRestantes = getActualPhaseInstance().getMaxTick() - gameActualState.getActualTickInPhase();

        return secRestantes % 60;
    }

    public void nextPhase() {
        getActualPhaseInstance().onPhaseEnd();
        gameActualState.resetTickInPhaseAt(0);
    }

    public void cancelTickAction() {
        Message.Get().broadcastMessageToConsole("Stoping Tick Loop...");
        this.cancel();
    }
}
