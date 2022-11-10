package fr.teamunc.base_unclib.models.tickloops;

import java.time.LocalDateTime;

public abstract class UNCPhase {
    public abstract String getName();
    public abstract void onTick();
    public abstract void onPhaseStart();
    public abstract void onPhaseEnd();
    public abstract boolean isInGamePhase();
    public abstract boolean isLastPhase();

    /**
     * describe if the phase is going to use an ending date #getEndingDate (terminate the phase when reached)
     * or not : the phase use a tick time before ended #getMaxTick (terminate when the number of ticks is passed in the phase)
     *
     * if true with a valid #getEndingDate => when the server is off, the phase continue to advance
     * if false with a valid #getMaxTick => when the server is off, the phase pause with it
     * @return
     */
    public abstract boolean isWithADueDate();
    /**
     * max tick before ending phase
     * TO USE ONLY IF THIS PHASE IS SET TO isWithADueDate = false
     * ELSE RETURNED VALUE NOT GOING BEE TAKEN IN COUNT
     * @return int max tick before ending the phase
     */
    public abstract int getMaxTick();

    /**
     * date of the phase ending
     * TO USE ONLY IF THIS PHASE IS SET TO isWithADueDate = true
     * ELSE RETURNED VALUE NOT GOING BEE TAKEN IN COUNT
     * @return date when the phase is going to end
     */
    public abstract LocalDateTime getEndingDate();
}
