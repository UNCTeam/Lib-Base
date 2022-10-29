package fr.teamunc.base_unclib.controllers;

import fr.teamunc.base_unclib.models.inventories.UNCChestInventory;
import fr.teamunc.base_unclib.models.inventories.UNCContainerInventory;
import fr.teamunc.base_unclib.models.inventories.UNCInventory;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UNCInventoryController {

    @Getter
    private HashMap<String, UNCInventory> inventories;
    @Getter
    private UNCContainerInventory containerInventory;

    public void addInventory(UNCInventory inventory) {
        inventories.put(inventory.getTitle(), inventory);
    }

    public void removeInventory(UNCInventory inventory) {
        inventories.remove(inventory.getTitle());
    }

    public String registerChestInventory(UNCChestInventory inventory) {
        containerInventory.getInventories().put(inventory.getUuid(), inventory);
        return inventory.getUuid().toString();
    }

    public void removeChestInventory(UNCChestInventory inventory) {
        containerInventory.getInventories().remove(inventory.getUuid());
    }

    public void openChestInventory(String uuid, Player player) {
        UNCChestInventory inventory = containerInventory.getInventories().get(UUID.fromString(uuid));
        player.openInventory(inventory.getInventory());
    }
}
