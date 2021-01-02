package org.stellardev.galacticlib.integration.galacticshop;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.IShopHandler;
import org.stellardev.galacticlib.provider.IShopHandlerProvider;

public class IntegrationGalacticShop extends Integration implements IShopHandlerProvider {

    private static final IntegrationGalacticShop i = new IntegrationGalacticShop();
    public static IntegrationGalacticShop get() { return i; }

    private IntegrationGalacticShop() {
        setPluginName("GalacticShop");
    }

    @Override
    public Engine getEngine() {
        return EngineGalacticShop.get();
    }

    @Override
    public IShopHandler getShopHandler() {
        return EngineGalacticShop.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        GalacticLib.get().registerShopHandler(getShopHandler());
    }
}
