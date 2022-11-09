package fr.teamunc.base_unclib.models.tickloops;

import fr.teamunc.base_unclib.models.jsonEntities.UNCEntitiesContainer;
import lombok.*;

/**
 * This class is used to store the actual state of the game
 * actualPhaseNumber == -1 means that the game is not started
 *
 * #passSecondInPhase increase both actualSecondsInPhase and actualSecondsInGame
 * #resetTimeInPhaseAt reset actualSecondsInPhase to value
 */
@Getter @Setter
public class GameActualState extends UNCEntitiesContainer {

    private int actualPhaseNumber;

    private int actualTicksInPhase;
    private int actualTicksInGame;
    public GameActualState() {
        super();
        this.actualPhaseNumber = -1;
        this.actualTicksInPhase = 0;
        this.actualTicksInGame = 0;
    }

    /**
     * Increase actualSecondsInPhase and actualSecondsInGame
     */
    public void passSecondInPhase() {
        actualTicksInPhase++;
        actualTicksInGame++;
    }

    /**
     * reset actualSecondsInPhase to value
     * @param seconds the new value
     */
    public void resetTimeInPhaseAt(int seconds) {
        actualTicksInPhase = seconds;
    }
}
