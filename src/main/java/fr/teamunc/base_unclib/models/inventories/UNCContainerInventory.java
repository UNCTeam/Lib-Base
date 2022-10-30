package fr.teamunc.base_unclib.models.inventories;

import fr.teamunc.base_unclib.models.jsonEntities.UNCEntitiesContainer;
import lombok.Getter;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class UNCContainerInventory extends UNCEntitiesContainer {
    private HashMap<UUID, Inventory> inventories;
}
