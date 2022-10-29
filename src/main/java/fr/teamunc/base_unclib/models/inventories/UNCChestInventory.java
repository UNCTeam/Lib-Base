package fr.teamunc.base_unclib.models.inventories;

import fr.teamunc.base_unclib.models.tickloops.IUNCInventoryAction;
import lombok.Getter;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class UNCChestInventory extends UNCInventory {
    @Getter
    private final UUID uuid;

    public UNCChestInventory(String title, Inventory inventory, Integer size, IUNCInventoryAction closeAction, HashMap<Integer, UNCItemMenu> fixedItems) {
        super(title, inventory, size, closeAction, fixedItems);
        this.uuid = UUID.randomUUID();
    }
}
