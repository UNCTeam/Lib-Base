package fr.teamunc.base_unclib.models.scoreboards;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class VScoreboard extends FastBoard {
    /**
     * Creates a new FastBoard.
     *
     * @param player the player the scoreboard is for
     */
    public VScoreboard(Player player,String title) {
        super(player);
        this.updateTitle(title);
    }

    public abstract List<String> getLines();

    public void actualise() {
        this.updateLines(this.getLines());
    }
}
