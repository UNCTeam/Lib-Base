package fr.teamunc.base_unclib.models.scoreboards;

import lombok.Getter;

@Getter
public class UNCScoreboardType {
    private String title;
    private UNCScoreboardLinesActualiser uncScoreboardLinesActualiser;

    public UNCScoreboardType(String title,UNCScoreboardLinesActualiser uncScoreboardLinesActualiser) {
        this.title = title;
        this.uncScoreboardLinesActualiser = uncScoreboardLinesActualiser;
    }
}
