package org.stellardev.galacticlib.util;

import com.massivecraft.massivecore.util.InventoryUtil;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InvUtil {

    public static int getFreeSlots(Inventory inventory) {
        inventory = InventoryUtil.clone(inventory, false);

        int count = 0;

        for(int i = 0; i < inventory.getSize(); i++) {
            ItemStack itemStack = inventory.getItem(i);

            if(itemStack == null || itemStack.getType() == Material.AIR) {
                count++;
            }
        }

        return count;
    }
}
