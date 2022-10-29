package fr.teamunc.base_unclib.controllers;

import fr.teamunc.base_unclib.models.inventories.UNCInventory;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class UNCInventoryController {

    @Getter
    private HashMap<String, UNCInventory> inventories;

    public void addInventory(UNCInventory inventory) {
        inventories.put(inventory.getTitle(), inventory);
    }

    public void removeInventory(UNCInventory inventory) {
        inventories.remove(inventory.getTitle());
    }
}
