package fr.teamunc.base_unclib.controllers;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.models.events.UNCEvent;
import fr.teamunc.base_unclib.models.events.UNCEventContainer;
import fr.teamunc.base_unclib.models.jsonEntities.UNCEntitiesContainer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class UNCEventController implements IUNCController {

    private UNCEventRunnable runnable;
    @Getter
    private List<UNCEvent> registredEvents;

    private UNCEventContainer activeEventContainer;
    private static final String EVENT_CONTAINER_NAME = "activeEvents";

    public UNCEventController() {
        this.registredEvents = new ArrayList<>();
        this.activeEventContainer = initActiveEventContainer();

        this.runnable = new UNCEventRunnable();
        this.runnable.runTaskTimer(BaseLib.getPlugin(), 0, 1);
    }

    private UNCEventContainer initActiveEventContainer() {
        UNCEventContainer res;
        try {
            return UNCEntitiesContainer.loadContainer(EVENT_CONTAINER_NAME,UNCEventContainer.class);
        } catch (Exception e) {
            res = new UNCEventContainer();
            res.save(EVENT_CONTAINER_NAME);
        }
        return res;
    }

    public void startEvent(UNCEvent eventToStart) {
        eventToStart.startEvent();
        activeEventContainer.getEvents().add(eventToStart);
        save();
    }

    public void stopEvent(UNCEvent eventToStop) {
        eventToStop.stopEvent();
        activeEventContainer.getEvents().remove(eventToStop);
        save();
    }

    public List<UNCEvent> getActiveEvents() {
        return this.activeEventContainer.getEvents();
    }

    /**
     * Register an event to the controller
     * @param event the event to register
     */
    public void registerEvent(UNCEvent event) {
        registredEvents.add(event);
    }

    public void save() {
        activeEventContainer.save(EVENT_CONTAINER_NAME);
    }

    public UNCEvent getEvent(String arg) {
        for (UNCEvent event : registredEvents) {
            if (event.getClass().getSimpleName().equals(arg)) {
                return event;
            }
        }
        return null;
    }
}
