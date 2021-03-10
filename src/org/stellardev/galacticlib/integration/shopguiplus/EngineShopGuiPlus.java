package org.stellardev.galacticlib.integration.shopguiplus;

import com.massivecraft.massivecore.Engine;
import net.brcdev.shopgui.ShopGuiPlusApi;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.stellardev.galacticlib.handler.IShopHandler;

public class EngineShopGuiPlus extends Engine implements IShopHandler {

    private static final EngineShopGuiPlus i = new EngineShopGuiPlus();
    public static EngineShopGuiPlus get() { return i; }

    @Override
    public double getSellPrice(ItemStack itemStack, Player player, long amount) {
        return ShopGuiPlusApi.getItemStackPriceSell(player, itemStack) * amount;
    }
}
