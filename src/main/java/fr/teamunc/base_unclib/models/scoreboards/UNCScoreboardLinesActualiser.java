package fr.teamunc.base_unclib.models.scoreboards;

import org.bukkit.entity.Player;

import java.util.List;

public interface UNCScoreboardLinesActualiser {
    List<String> actualiseLines(Player player);
}
