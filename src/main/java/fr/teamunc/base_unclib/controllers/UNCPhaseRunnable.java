package fr.teamunc.base_unclib.controllers;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.utils.helpers.Message;
import lombok.var;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;

public class UNCPhaseRunnable extends BukkitRunnable {
    @Override
    public void run() {
        // baselib init ?
        if (!BaseLib.isInit()) {
            BaseLib.getUNCPhaseController().cancelTickAction();
            return;
        }

        var uncPhasesRegistered = BaseLib.getUNCPhaseController().getUncPhasesRegistered();

        // any phase registered ?
        if (uncPhasesRegistered.size() == 0) return;

        // phase number needed is present ?
        if (!uncPhasesRegistered.containsKey(BaseLib.getUNCPhaseController().getGameActualState().getActualPhaseNumber())) {
            Message.Get().broadcastMessageToConsole("Phase number " + BaseLib.getUNCPhaseController().getGameActualState().getActualPhaseNumber() + " not found");
            BaseLib.getUNCPhaseController().cancelTickAction();
            return;
        }

        // check if the phase is over
        if (BaseLib.getUNCPhaseController().isActualPhaseOver()) {
            BaseLib.getUNCPhaseController().nextPhase();
            return;
        }

        // do action onTick
        BaseLib.getUNCPhaseController().getActualPhaseInstance().onTick();

        // passing seconds
        BaseLib.getUNCPhaseController().getGameActualState().passSecondInPhase();
    }
}
