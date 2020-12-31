package org.stellardev.galacticlib;

import com.massivecraft.massivecore.MassivePlugin;
import org.stellardev.galacticlib.coll.*;
import org.stellardev.galacticlib.engine.*;
import org.stellardev.galacticlib.handler.*;
import org.stellardev.galacticlib.handler.fallback.*;
import org.stellardev.galacticlib.integration.fastasyncworldedit.*;
import org.stellardev.galacticlib.integration.luckperms.*;
import org.stellardev.galacticlib.integration.plotsquared.*;
import org.stellardev.galacticlib.integration.silkspawners.*;
import org.stellardev.galacticlib.integration.worldedit.*;
import org.stellardev.galacticlib.nms.*;

import java.util.logging.Level;

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
                NmsChestPacket.class,
                NmsPacket.class,
                NmsPing.class,
                NmsSkullTexture.class,
                NmsTps.class
        );
    }

    public IShopHandler getShopHandler() {
        return this.shopHandler == null? this.fallbackShopHandler : this.shopHandler;
    }
    public void registerShopHandler(IShopHandler shopHandler) {
        if(this.shopHandler != null) {
            GalacticLib.get().log(Level.SEVERE, "An issue occurred when registering the new shop handler '" + shopHandler.getClass().getSimpleName() + "', as a shop handler is already set.");
            return;
        }

        GalacticLib.get().log("Shop handler has now been set to " + shopHandler.getClass().getSimpleName() + ".");
        this.shopHandler = shopHandler;
    }

    public IDataHandler getDataHandler() {
        return this.dataHandler == null? this.fallbackDataHandler : this.dataHandler;
    }
    public void registerDataHandler(IDataHandler dataHandler) {
        if(this.dataHandler != null) {
            GalacticLib.get().log(Level.SEVERE, "An issue occurred when registering the new data handler '" + dataHandler.getClass().getSimpleName() + "', as a data handler is already set.");
            return;
        }

        GalacticLib.get().log("Data handler has now been set to " + dataHandler.getClass().getSimpleName() + ".");
        this.dataHandler = dataHandler;
    }
}
