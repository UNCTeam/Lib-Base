package fr.teamunc.base_unclib.models.tickloops;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface IUNCInventoryAction {
    void handleEvent(InventoryClickEvent event);
}
