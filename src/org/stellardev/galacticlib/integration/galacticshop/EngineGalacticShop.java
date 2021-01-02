package org.stellardev.galacticlib.integration.galacticshop;

import com.massivecraft.massivecore.Engine;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.stellardev.galacticlib.handler.IShopHandler;
import org.stellardev.galacticshop.api.ShopAPI;

public class EngineGalacticShop extends Engine implements IShopHandler {

    private static final EngineGalacticShop i = new EngineGalacticShop();
    public static EngineGalacticShop get() { return i; }

    @Override
    public double getSellPrice(ItemStack itemStack, Player player, long amount) {
        return ShopAPI.getSellWorth(player, itemStack, amount);
    }
}
