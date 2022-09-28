package teamunc.base_unclib;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import teamunc.base_unclib.models.utils.helpers.gameStates.GameActualState;
import teamunc.base_unclib.models.jsonEntities.UNCEntitiesContainer;
import teamunc.base_unclib.models.tickloops.UNCPhaseController;

/**
 * Behaviors of the Base_UNCLib :
 * - init with the plugin
 *
 */
public class BaseLib {
    @Getter
    private static JavaPlugin plugin;
    @Getter
    private static GameActualState gameActualState;
    @Getter
    private static UNCPhaseController UNCPhaseController;

    public static void init(JavaPlugin plugin) {
        BaseLib.plugin = plugin;

        // init game state
        gameActualState = initGameState();

        // init tick action
        UNCPhaseController = new UNCPhaseController(gameActualState);

        // init json entities
        UNCEntitiesContainer.init(plugin.getDataFolder());
    }

    public static boolean IsInit() {
        return plugin != null;
    }

    private static GameActualState initGameState() {
        GameActualState res;
        try {
            return UNCEntitiesContainer.loadContainer("gameActualState",GameActualState.class);
        } catch (Exception e) {
            res = new GameActualState();
            res.save("gameActualState");
        }
        return res;
    }
}
