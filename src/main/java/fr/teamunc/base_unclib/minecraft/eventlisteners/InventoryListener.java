package fr.teamunc.base_unclib.minecraft.eventlisteners;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.models.inventories.UNCInventory;
import fr.teamunc.base_unclib.models.inventories.UNCItemMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        UNCInventory inventory = BaseLib.getUNCInventoryController().getInventories().get(title);
        if(inventory != null) {
            UNCItemMenu item = inventory.getFixedItems().get(event.getSlot());
            // On lance l'action associé au slot si elle existe
            if(item != null && item.getAction() != null) {
                item.getAction().handleEvent(event);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        String title = event.getView().getTitle();
        UNCInventory inventory = BaseLib.getUNCInventoryController().getInventories().get(title);
        if(inventory != null) {
            inventory.getCloseAction().handleEvent(event);
        }
    }
}
