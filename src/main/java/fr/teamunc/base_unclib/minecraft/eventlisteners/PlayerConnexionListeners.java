package fr.teamunc.base_unclib.minecraft.eventlisteners;

import fr.teamunc.base_unclib.BaseLib;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnexionListeners implements Listener {
    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        // create or actualise the scoreboard if one
        if (BaseLib.isInit()) {
            BaseLib.getUNCScoreboardController().addAPlayerToScoreboardActualisers(event.getPlayer());
        }
    }
}
