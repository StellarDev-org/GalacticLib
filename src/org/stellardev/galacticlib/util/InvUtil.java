package org.stellardev.galacticlib.util;

import com.massivecraft.massivecore.mixin.MixinMessage;
import com.massivecraft.massivecore.util.InventoryUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.stellardev.galacticlib.entity.Conf;
import org.stellardev.galacticlib.mixin.MixinInventory;

import java.util.*;

@UtilityClass
public class InvUtil {

    private Set<Material> TRANSPARENT_MATERIALS;

    public int getFreeSlots(Inventory inventory) {
        int count = 0;

        for(int i = 0; i < inventory.getSize(); i++) {
            ItemStack itemStack = inventory.getItem(i);

            if(InventoryUtil.isNothing(itemStack)) {
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

    public Set<Material> getTransparentMaterial() {
        if(TRANSPARENT_MATERIALS != null) {
            return TRANSPARENT_MATERIALS;
        }

        TRANSPARENT_MATERIALS = new HashSet<>();

        Arrays.stream(Material.values())
                .filter(Objects::nonNull)
                .filter(Material::isTransparent)
                .forEach(TRANSPARENT_MATERIALS::add);

        TRANSPARENT_MATERIALS.add(Material.WATER);

        return TRANSPARENT_MATERIALS;
    }

    public static Inventory clone(Inventory inventory, InventoryType inventoryType) {
        // Evade
        if(inventory == null) return null;

        // Create
        Inventory ret = MixinInventory.get().createInventory(inventory.getHolder(), inventoryType, inventory.getType().getDefaultTitle());

        // Fill
        ItemStack[] all = InventoryUtil.getContentsAll(inventory);
        all = InventoryUtil.clone(all);
        InventoryUtil.setContentsAll(ret, all);

        // Return
        return ret;
    }

    public static int roomLeft(Inventory inventory, InventoryType inventoryType, ItemStack itemStack, int limit) {
        inventory = clone(inventory, inventoryType);

        int ret = 0;
        while (limit <= 0 || ret < limit)
        {
            HashMap<Integer, ItemStack> result = inventory.addItem(itemStack.clone());
            if (result.size() != 0) return ret;
            ret++;
        }

        return ret;
    }
}
