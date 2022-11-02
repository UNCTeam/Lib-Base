package fr.teamunc.base_unclib;

import org.bukkit.plugin.java.JavaPlugin;

public final class Base_UNCLib extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        BaseLib.initGameListener(this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (BaseLib.IsInit()) {
            BaseLib.getGameActualState().save("gameActualState");
            BaseLib.getUNCInventoryController().getContainerInventory().save("inventories");
        }

        BaseLib.getPlugin().getServer().getOnlinePlayers().forEach(player -> {
            player.closeInventory();
        });
    }
}
