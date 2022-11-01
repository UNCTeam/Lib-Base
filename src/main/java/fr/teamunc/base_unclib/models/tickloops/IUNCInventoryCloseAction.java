package fr.teamunc.base_unclib.models.tickloops;

import org.bukkit.event.inventory.InventoryCloseEvent;

public interface IUNCInventoryCloseAction {
    void handleEvent(InventoryCloseEvent event);
}
