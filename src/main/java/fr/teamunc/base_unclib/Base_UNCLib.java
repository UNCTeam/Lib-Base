package fr.teamunc.base_unclib;

import org.bukkit.plugin.java.JavaPlugin;

public final class Base_UNCLib extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (BaseLib.IsInit()) {
            BaseLib.getGameActualState().save("gameActualState");
        }
    }
}
