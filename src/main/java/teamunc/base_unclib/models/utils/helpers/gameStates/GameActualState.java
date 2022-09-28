package teamunc.base_unclib.models.utils.helpers.gameStates;

import lombok.*;
import teamunc.base_unclib.models.jsonEntities.UNCEntitiesContainer;

@Getter @Setter
public class GameActualState extends UNCEntitiesContainer {
    public GameActualState() {
        super();
        this.actualPhaseNumber = 0;
        this.actualTickInGame = 0;
        this.actualTickInPhase = 0;
    }
    @Getter @Setter
    private int actualPhaseNumber;
    @Getter
    private int actualTickInPhase;
    @Getter
    private int actualTickInGame;

    public void addTickInPhase() {
        actualTickInPhase++;
        actualTickInGame++;
    }

    public void resetTickInPhaseAt(int tick) {
        actualTickInPhase = tick;
    }
}
