package fr.teamunc.base_unclib.models.inventories;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class UNCItemMenu {
    private String title;
    private ItemStack itemStack;
    private List<String> lore;

    public UNCItemMenu(String title, ItemStack itemStack, String... lore) {
        this.title = title;
        this.itemStack = itemStack;
        this.lore = Arrays.asList(lore);
    }

    public ItemStack getItemStack() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(title);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
