package fr.teamunc.base_unclib;

import fr.teamunc.base_unclib.minecraft.commandsExecutors.gameLoop.GameLaunchCommands;
import lombok.Getter;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import fr.teamunc.base_unclib.models.tickloops.GameActualState;
import fr.teamunc.base_unclib.models.jsonEntities.UNCEntitiesContainer;
import fr.teamunc.base_unclib.controllers.UNCPhaseController;

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

        // init json entities
        UNCEntitiesContainer.init(plugin.getDataFolder());

        // init game state
        gameActualState = initGameState();

        // init tick action
        UNCPhaseController = new UNCPhaseController();

        if (gameActualState.getActualPhaseNumber() != -1) {
            UNCPhaseController.StartTickAction();
        }

        // register commands
        initCommands();
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

    public static void resetActualGameState() {
        gameActualState = new GameActualState();
        gameActualState.save("gameActualState");
    }

    private static void initCommands() {
        PluginCommand teamCommand = plugin.getCommand("uncgame");
        if (teamCommand != null) {
            teamCommand.setExecutor(new GameLaunchCommands());
        }
    }

}
