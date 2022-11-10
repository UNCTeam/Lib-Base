package fr.teamunc.base_unclib.controllers;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.models.scoreboards.UNCScoreboard;
import fr.teamunc.base_unclib.models.scoreboards.UNCScoreboardLinesActualiser;
import fr.teamunc.base_unclib.models.scoreboards.UNCScoreboardType;
import fr.teamunc.base_unclib.utils.helpers.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class UNCScoreboardController {
    private final List<UNCScoreboard> scoreboards;
    private UNCScoreboardType scoreboardType;

    private BukkitRunnable bukkitRunnable;

    public UNCScoreboardController() {
        this.scoreboards = new ArrayList<>();

        bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                for (UNCScoreboard scoreboard : scoreboards) {
                    scoreboard.actualise();
                }
            }
        };
    }

    public void registerUNCScoreboardType(String title, UNCScoreboardLinesActualiser uncScoreboardLinesActualiser) {
        this.scoreboardType = new UNCScoreboardType(title, uncScoreboardLinesActualiser);
        bukkitRunnable.runTaskTimer(BaseLib.getPlugin(), 0, 20L);
    }

    public void addAPlayerToScoreboardActualisers(Player player) {
        if (this.scoreboardType == null) {
            Message.Get().broadcastMessageToConsole("A scoreboard type need to be build and register to the UNCScoreboardController in order to use it !");
            return;
        }

        scoreboards.add(new UNCScoreboard(player,this.scoreboardType.getTitle(), this.scoreboardType.getUncScoreboardLinesActualiser()));
    }
}
