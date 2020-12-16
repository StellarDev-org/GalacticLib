package org.stellardev.galacticlib;

import com.massivecraft.massivecore.MassivePlugin;
import org.stellardev.galacticlib.coll.ConfColl;
import org.stellardev.galacticlib.engine.EngineGui;
import org.stellardev.galacticlib.exception.HandlerAlreadyRegisteredException;
import org.stellardev.galacticlib.handler.IDataHandler;
import org.stellardev.galacticlib.handler.IShopHandler;
import org.stellardev.galacticlib.handler.fallback.FallbackDataHandler;
import org.stellardev.galacticlib.handler.fallback.FallbackShopHandler;
import org.stellardev.galacticlib.integration.fastasyncworldedit.IntegrationFastAsyncWorldEdit;
import org.stellardev.galacticlib.integration.luckperms.IntegrationLuckPerms;
import org.stellardev.galacticlib.integration.plotsquared.IntegrationPlotSquared;
import org.stellardev.galacticlib.integration.silkspawners.IntegrationSilkSpawners;
import org.stellardev.galacticlib.integration.worldedit.IntegrationWorldEdit;
import org.stellardev.galacticlib.nms.NmsArmorStandPacket;
import org.stellardev.galacticlib.nms.NmsPacket;
import org.stellardev.galacticlib.nms.NmsSkullTexture;

public class GalacticLib extends MassivePlugin {

    private static GalacticLib i;
    public static GalacticLib get() { return i; }

    public GalacticLib() {
        i = this;
    }

    private final IDataHandler fallbackDataHandler = new FallbackDataHandler();
    private final IShopHandler fallbackShopHandler = new FallbackShopHandler();

    private IDataHandler dataHandler;
    private IShopHandler shopHandler;

    @Override
    public void onEnableInner() {
        this.activate(
                ConfColl.class,

                EngineGui.class,

                IntegrationFastAsyncWorldEdit.class,
                IntegrationLuckPerms.class,
                IntegrationPlotSquared.class,
                IntegrationSilkSpawners.class,
                IntegrationWorldEdit.class,

                NmsArmorStandPacket.class,
                NmsPacket.class,
                NmsSkullTexture.class
        );
    }

    public IShopHandler getShopHandler() {
        return this.shopHandler == null? this.fallbackShopHandler : this.shopHandler;
    }
    public void registerShopHandler(IShopHandler shopHandler) throws HandlerAlreadyRegisteredException {
        if(this.shopHandler != null) {
            throw new HandlerAlreadyRegisteredException("shop");
        }

        GalacticLib.get().log("Shop handler has now been set to " + shopHandler.getClass().getSimpleName() + ".");
        this.shopHandler = shopHandler;
    }

    public IDataHandler getDataHandler() {
        return this.dataHandler == null? this.fallbackDataHandler : this.dataHandler;
    }
    public void registerDataHandler(IDataHandler dataHandler) throws HandlerAlreadyRegisteredException {
        if(this.dataHandler != null) {
            throw new HandlerAlreadyRegisteredException("data");
        }

        GalacticLib.get().log("Data handler has now been set to " + shopHandler.getClass().getSimpleName() + ".");
        this.dataHandler = dataHandler;
    }
}
