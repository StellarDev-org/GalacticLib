package org.stellardev.galacticlib.util;

import com.massivecraft.massivecore.MassiveCoreMConf;
import com.massivecraft.massivecore.mixin.MixinMessage;
import com.massivecraft.massivecore.util.InventoryUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.stellardev.galacticlib.entity.Conf;

@UtilityClass
public class InvUtil {

    public int getFreeSlots(Inventory inventory) {
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

    public void giveItemStack(Player player, ItemStack itemStack) {
        if(player == null || itemStack == null) return;

        if(player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
            MixinMessage.get().msgOne(player, Conf.get().msgInventoryFull);
        } else {
            player.getInventory().addItem(itemStack);
        }
    }

    public void removeSingleItemInHand(Player player, ItemStack itemStack) {
        if(itemStack.getAmount() > 1) {
            itemStack.setAmount(itemStack.getAmount() - 1);
        } else {
            player.setItemInHand(new ItemStack(Material.AIR));
        }
    }
}
