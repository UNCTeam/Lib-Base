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
import java.util.List;

@Getter
@AllArgsConstructor
public abstract class UNCInventory {

    /* Title of the inventory */
    protected String title;
    /* Inventory */
    protected Inventory inventory;
    /* Size of the inventory */
    protected Integer size;
    /* Action when the inventory is closed */
    protected IUNCInventoryAction closeAction;
    /* Items that are fixed in the inventory */
    protected HashMap<Integer, UNCItemMenu> fixedItems;
    /* slots thant we want to cancel click in actionClick */
    protected List<CancelSlot> cancelSlotList;

    public UNCInventory(String title, Integer size, IUNCInventoryAction closeAction,
                        HashMap<Integer, UNCItemMenu> fixedItems, CancelSlot... cancelSlots) {
        this.title = title;
        this.size = size;
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
