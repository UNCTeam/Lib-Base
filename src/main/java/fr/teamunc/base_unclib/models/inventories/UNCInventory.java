package fr.teamunc.base_unclib.models.inventories;

import fr.teamunc.base_unclib.models.tickloops.IUNCInventoryAction;
import fr.teamunc.base_unclib.models.tickloops.IUNCInventoryCloseAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@AllArgsConstructor
public class UNCInventory {

    /* The inventory key */
    protected String key;
    /* Title of the inventory */
    protected String title;
    /* Inventory */
    protected Inventory inventory;
    /* Size of the inventory */
    protected Integer size;
    /* Action when the inventory is closed */
    protected IUNCInventoryCloseAction closeAction;
    /* Items that are fixed in the inventory */
    protected List<UNCItemMenu> fixedItems;
    /* slots thant we want to cancel click in actionClick */
    protected List<CancelSlot> cancelSlotList;

    @Builder
    public UNCInventory(String key, String title, Integer size, IUNCInventoryCloseAction closeAction,
                        List<UNCItemMenu> fixedItems, CancelSlot... cancelSlots) {
        this.key = key;
        this.title = title;
        this.size = size;
        this.closeAction = closeAction;
        this.fixedItems = fixedItems;

        // Creation de l'inventaire
        this.inventory = Bukkit.createInventory(new UNCInventoryHolder(key), size, title);
        this.inventory.getHolder();

        // Initialisation des items fixes
        this.initializeFixedItems();

    }

    public static UNCInventoryBuilder builder(String key, String title, Integer size) {
        return new UNCInventoryBuilder().key(key).title(title).size(size);
    }

    private void initializeFixedItems() {
        for(UNCItemMenu itemMenu : this.fixedItems) {
            inventory.setItem(itemMenu.getSlot(), itemMenu.getItemStack());
        }
    }

    public void openInventory(Player player) {
        player.openInventory(inventory);
    }
}
