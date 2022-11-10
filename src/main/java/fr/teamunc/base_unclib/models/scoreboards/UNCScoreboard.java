package fr.teamunc.base_unclib.models.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class UNCScoreboard extends VScoreboard{
    private final UNCScoreboardLinesActualiser uncScoreboardLinesActualiser;

    /**
     * Creates a new FastBoard.
     *
     * @param player the player the scoreboard is for
     * @param title
     */
    public UNCScoreboard(Player player, String title, UNCScoreboardLinesActualiser uncScoreboardLinesActualiser) {
        super(player, title);
        this.uncScoreboardLinesActualiser = uncScoreboardLinesActualiser;
        actualise();
    }

    @Override
    public List<String> getLines() {
        return this.uncScoreboardLinesActualiser.actualiseLines(getPlayer());
    }
}
