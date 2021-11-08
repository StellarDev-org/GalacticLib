package org.stellardev.galacticlib.mixin;

import com.massivecraft.massivecore.mixin.Mixin;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MixinInventory extends Mixin {

    private static final MixinInventory d = new MixinInventory();
    private static MixinInventory i = d;
    public static MixinInventory get() { return i; }

    public Inventory createInventory(InventoryHolder inventoryHolder, InventoryType inventoryType, String title) {
        return Bukkit.createInventory(inventoryHolder, inventoryType, title);
    }

}
