package fr.teamunc.base_unclib.models.tickloops;

public abstract class UNCPhase {
    public abstract void onTick();
    public abstract void onPhaseStart();
    public abstract void onPhaseEnd();
    public abstract boolean isInGamePhase();
    public abstract boolean isLastPhase();
    public abstract int getMaxTick();
}
