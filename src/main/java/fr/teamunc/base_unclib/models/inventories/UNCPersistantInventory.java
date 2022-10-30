package fr.teamunc.base_unclib.models.inventories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UNCPersistantInventory {
    private String title;
    private UUID uuid;
    private Inventory inventory;
}
