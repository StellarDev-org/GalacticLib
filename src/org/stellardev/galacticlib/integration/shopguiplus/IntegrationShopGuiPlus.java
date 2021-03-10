package org.stellardev.galacticlib.integration.shopguiplus;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.IShopHandler;
import org.stellardev.galacticlib.provider.IShopHandlerProvider;

public class IntegrationShopGuiPlus extends Integration implements IShopHandlerProvider {

    private static final IntegrationShopGuiPlus i = new IntegrationShopGuiPlus();
    public static IntegrationShopGuiPlus get() { return i; }

    private IntegrationShopGuiPlus() {
        setPluginName("ShopGUIPlus");
    }

    @Override
    public Engine getEngine() {
        return EngineShopGuiPlus.get();
    }

    @Override
    public IShopHandler getShopHandler() {
        return EngineShopGuiPlus.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        GalacticLib.get().registerShopHandler(getShopHandler());
    }
}
