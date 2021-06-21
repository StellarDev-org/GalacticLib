package org.stellardev.galacticlib.engine;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.util.InventoryUtil;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class EngineAnvil extends Engine {

    private static final EngineAnvil i = new EngineAnvil();
    public static EngineAnvil get() { return i; }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onRename(InventoryClickEvent event) {
        HumanEntity humanEntity = event.getWhoClicked();

        if(MUtil.isntPlayer(humanEntity)) return;

        Player player = (Player) humanEntity;
        Inventory inventory = event.getInventory();

        if(!(inventory instanceof AnvilInventory)) return;

        AnvilInventory anvilInventory = (AnvilInventory) inventory;
        InventoryView inventoryView = event.getView();;
        int rawSlot = event.getRawSlot();

        if(rawSlot == inventoryView.convertSlot(rawSlot)) {
            // slot 0: left item
            // slot 1: right item
            // slot 2: result item

            if(rawSlot != 2) return;

            ItemStack[] items = anvilInventory.getContents();
            ItemStack slot1 = items[0];
            ItemStack slot2 = items[1];

            if(InventoryUtil.isNothing(slot1) || InventoryUtil.isNothing(slot2)) return;

            // handle nms to pick up RepairCost from item
        }
    }

}
