package fr.teamunc.base_unclib.models.inventories;

import fr.teamunc.base_unclib.models.tickloops.IUNCInventoryAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public class UNCItemMenu {
    private Integer slot;
    private ItemStack itemStack;

    private IUNCInventoryAction action;

    public UNCItemMenu(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
