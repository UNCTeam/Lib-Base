package fr.teamunc.base_unclib.controllers;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.models.events.UNCEvent;
import fr.teamunc.base_unclib.models.events.UNCEventContainer;
import fr.teamunc.base_unclib.models.jsonEntities.UNCEntitiesContainer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UNCEventController implements IUNCController {

    private UNCEventRunnable runnable;
    @Getter
    private final List<Class<UNCEvent>> registredEvents;

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

    }

    public void stopEvent(UNCEvent eventToStop) {

    }

    public List<UNCEvent> getActiveEvents() {
        return this.activeEventContainer.getEvents();
    }

    /**
     * Register an event to the controller
     * @param event the event class to instantiate
     * @param executionDate the exact date when the event need to be start automatically
     * @apiNote note that if the event start date is in the past, the event will be start immediately after the registration and the event name (class name) will be saved as disabled in the json file
     */
    public void registerEvent(Class<UNCEvent> event, Date executionDate) {
        registredEvents.add(event);
    }

    public void save() {
        activeEventContainer.save(EVENT_CONTAINER_NAME);
    }
}
