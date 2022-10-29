package fr.teamunc.base_unclib.models.inventories;

import fr.teamunc.base_unclib.models.tickloops.IUNCInventoryAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@Getter
@AllArgsConstructor
public abstract class UNCInventory {

    protected String title;
    protected Inventory inventory;
    protected Integer size;
    protected IUNCInventoryAction clickAction;
    protected IUNCInventoryAction closeAction;
    protected HashMap<Integer, UNCItemMenu> fixedItems;

    public UNCInventory(String title, Integer size, IUNCInventoryAction clickAction, IUNCInventoryAction closeAction,
                        HashMap<Integer, UNCItemMenu> fixedItems) {
        this.title = title;
        this.size = size;
        this.clickAction = clickAction;
        this.closeAction = closeAction;
        this.fixedItems = fixedItems;

        // Creation de l'inventaire
        this.inventory = Bukkit.createInventory(null, size, title);

        // Initialisation des items fixes
        this.initializeFixedItems();

    }

    private void initializeFixedItems() {
        for(Integer slot : fixedItems.keySet()) {
            inventory.setItem(slot, fixedItems.get(slot).getItemStack());
        }
    }

    public void openInventory(Player player) {
        player.openInventory(inventory);
    }
}
