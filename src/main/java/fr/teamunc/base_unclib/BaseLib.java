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
    private static UNCPhaseController UNCPhaseController;

    public static void init(JavaPlugin plugin) {
        BaseLib.plugin = plugin;

        // init json entities
        UNCEntitiesContainer.init(plugin.getDataFolder());

        // init tick action
        UNCPhaseController = new UNCPhaseController(initGameState());

        // register commands
        initCommands();
    }

    public static boolean isInit() {
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

    private static void initCommands() {
        PluginCommand teamCommand = plugin.getCommand("uncgame");
        if (teamCommand != null) {
            teamCommand.setExecutor(new GameLaunchCommands());
            teamCommand.setTabCompleter(new GameLaunchCommands());
        }
    }

}
