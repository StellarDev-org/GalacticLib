package org.stellardev.galacticlib.handler.fallback;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.stellardev.galacticlib.handler.IShopHandler;

public class FallbackShopHandler implements IShopHandler {

    @Override
    public double getSellPrice(ItemStack itemStack, Player player, long amount) {
        return 0;
    }
}
