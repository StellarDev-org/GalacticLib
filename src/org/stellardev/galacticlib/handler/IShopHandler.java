package org.stellardev.galacticlib.handler;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IShopHandler {

    double getSellPrice(ItemStack itemStack, Player player, long amount);

}
