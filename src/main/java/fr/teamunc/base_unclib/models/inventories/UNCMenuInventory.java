package fr.teamunc.base_unclib.models.inventories;

import fr.teamunc.base_unclib.models.tickloops.IUNCInventoryAction;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class UNCMenuInventory extends UNCInventory {

    public UNCMenuInventory(String title, Integer size, IUNCInventoryAction clickAction, IUNCInventoryAction closeAction,
                            HashMap<Integer, UNCItemMenu> fixedItems) {
        super(title, size, closeAction, fixedItems);
    }
}
