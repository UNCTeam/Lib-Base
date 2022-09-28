package teamunc.base_unclib;

import org.bukkit.plugin.java.JavaPlugin;
import teamunc.base_unclib.models.jsonEntities.UNCEntitiesContainer;

public class BaseLib {
    private static JavaPlugin plugin;

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void init(JavaPlugin plugin) {
        BaseLib.plugin = plugin;

        // init json entities
        UNCEntitiesContainer.init(plugin.getDataFolder());
    }

    public static void log(String message) {
        plugin.getLogger().info(message);
    }

    public static void logError(String message) {
        plugin.getLogger().severe(message);
    }

    public static void logWarning(String message) {
        plugin.getLogger().warning(message);
    }

    public static void logDebug(String message) {
        if (plugin.getConfig().getBoolean("debug")) {
            plugin.getLogger().info("[DEBUG] " + message);
        }
    }
}
