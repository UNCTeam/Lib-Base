package fr.teamunc.base_unclib.controllers;

import fr.teamunc.base_unclib.BaseLib;
import lombok.var;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;

public class UNCEventRunnable extends BukkitRunnable {
    @Override
    public void run() {
        // baselib init ?
        if (!BaseLib.isInit()) {
            BaseLib.getUNCPhaseController().cancelTickAction();
            return;
        }

        // any event registered ?
        if (BaseLib.getUNCEventController().getRegistredEvents().size() == 0) return;

        // is in game ?
        if (!BaseLib.getUNCPhaseController().isGameStarted()) return;


        // is it time for end or start an event ?
        Date now = new Date();
        UNCEventController eventController = BaseLib.getUNCEventController();


    }
}
