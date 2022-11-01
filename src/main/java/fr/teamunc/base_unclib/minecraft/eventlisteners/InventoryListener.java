package fr.teamunc.base_unclib.minecraft.eventlisteners;

import fr.teamunc.base_unclib.BaseLib;
import fr.teamunc.base_unclib.models.inventories.CancelSlot;
import fr.teamunc.base_unclib.models.inventories.UNCInventory;
import fr.teamunc.base_unclib.models.inventories.UNCInventoryHolder;
import fr.teamunc.base_unclib.models.inventories.UNCItemMenu;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getInventory().getHolder() instanceof UNCInventoryHolder) {
            UNCInventoryHolder holder = (UNCInventoryHolder) event.getInventory().getHolder();
            UNCInventory inventory = BaseLib.getUNCInventoryController().getInventories().get(holder.getKey());
            if(inventory == null) {
                Bukkit.broadcastMessage("Inventory not found!");
                return;
            }
            /** Détermine si le click est cancel */
            for(CancelSlot cancelSlot : inventory.getCancelSlotList()) {
                if(cancelSlot.getSlots().contains(event.getSlot())) {
                    Bukkit.broadcastMessage("cancel!");
                    event.setCancelled(true);
                }
            }
            /** Détermine si click doit effectuer une action */
            UNCItemMenu item = inventory.getFixedItems().stream()
                    .filter(i -> i.getSlot() == event.getSlot())
                    .findFirst()
                    .orElse(null);
            // On lance l'action associé au slot si elle existe
            if(item != null && item.getAction() != null) {
                item.getAction().handleEvent(event);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if(event.getInventory().getHolder() instanceof UNCInventoryHolder) {
            UNCInventoryHolder holder = (UNCInventoryHolder) event.getInventory().getHolder();
            UNCInventory inventory = BaseLib.getUNCInventoryController().getInventories().get(holder.getKey());
            if(inventory != null) {
                inventory.getCloseAction().handleEvent(event);
            }
        }
    }
}
