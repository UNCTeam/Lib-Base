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
    public GameActualState() {
        super();
        this.actualPhaseNumber = -1;
        this.actualSecondsInGame = 0;
        this.actualSecondsInPhase = 0;
    }
    @Getter @Setter
    private int actualPhaseNumber;
    @Getter
    private int actualSecondsInPhase;
    @Getter
    private int actualSecondsInGame;

    /**
     * Increase actualSecondsInPhase and actualSecondsInGame
     */
    public void passSecondInPhase() {
        actualSecondsInPhase++;
        actualSecondsInGame++;
    }

    /**
     * reset actualSecondsInPhase to value
     * @param seconds the new value
     */
    public void resetTimeInPhaseAt(int seconds) {
        actualSecondsInPhase = seconds;
    }
}
