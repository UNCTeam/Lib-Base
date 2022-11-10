package fr.teamunc.base_unclib.minecraft.eventlisteners;

import fr.teamunc.base_unclib.BaseLib;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnexionListeners implements Listener {
    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent event) {
        // create or actualise the scoreboard if one
        if (BaseLib.isInit() && !BaseLib.getUNCScoreboardController().isPlayerInScoreboardActualisers(event.getPlayer())) {
            BaseLib.getUNCScoreboardController().addAPlayerToScoreboardActualisers(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        // remove the player from the scoreboard if one
        if (BaseLib.isInit() && BaseLib.getUNCScoreboardController().isPlayerInScoreboardActualisers(event.getPlayer())) {
            BaseLib.getUNCScoreboardController().removeAPlayerFromScoreboardActualisers(event.getPlayer());
        }
    }
}
