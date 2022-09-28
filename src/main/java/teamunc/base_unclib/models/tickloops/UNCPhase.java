package teamunc.base_unclib.models.tickloops;

import lombok.Builder;

@Builder
public abstract class UNCPhase {
    public abstract void onTick();
    public abstract void onPhaseStart();
    public abstract void onPhaseEnd();
    public abstract boolean isInGamePhase();
    public abstract int getMaxTick();
}
