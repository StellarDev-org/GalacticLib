package org.stellardev.galacticlib;

import com.massivecraft.massivecore.MassivePlugin;
import org.stellardev.galacticlib.coll.*;
import org.stellardev.galacticlib.engine.*;
import org.stellardev.galacticlib.handler.*;
import org.stellardev.galacticlib.handler.fallback.*;
import org.stellardev.galacticlib.integration.fastasyncworldedit.*;
import org.stellardev.galacticlib.integration.galacticshop.IntegrationGalacticShop;
import org.stellardev.galacticlib.integration.galactictokens.IntegrationGalacticTokens;
import org.stellardev.galacticlib.integration.luckperms.*;
import org.stellardev.galacticlib.integration.plotsquared.*;
import org.stellardev.galacticlib.integration.silkspawners.*;
import org.stellardev.galacticlib.integration.worldedit.*;
import org.stellardev.galacticlib.mixin.MixinInventory;
import org.stellardev.galacticlib.mixin.MixinTeleport;
import org.stellardev.galacticlib.nms.*;
import org.stellardev.galacticlib.provider.IDataHandlerProvider;
import org.stellardev.galacticlib.provider.IShopHandlerProvider;
import org.stellardev.galacticlib.provider.ISpawnerHandlerProvider;
import org.stellardev.galacticlib.provider.ITokenHandlerProvider;

import java.util.logging.Level;

public class GalacticLib extends MassivePlugin implements IDataHandlerProvider, IShopHandlerProvider, ITokenHandlerProvider, ISpawnerHandlerProvider {

    private static GalacticLib i;
    public static GalacticLib get() { return i; }

    public GalacticLib() {
        i = this;
    }

    private final ISpawnerHandler fallbackSpawnerHandler = new FallbackSpawnerHandler();
    private final ITokenHandler fallbackTokenHandler = new FallbackTokenHandler();
    private final IDataHandler fallbackDataHandler = new FallbackDataHandler();
    private final IShopHandler fallbackShopHandler = new FallbackShopHandler();

    private ISpawnerHandler spawnerHandler;
    private ITokenHandler tokenHandler;
    private IDataHandler dataHandler;
    private IShopHandler shopHandler;

    @Override
    public void onEnableInner() {
        this.activate(
                ConfColl.class,

                EngineArmorEquip.class,
                EngineGui.class,
                EngineSpawnerPlace.class,

                IntegrationFastAsyncWorldEdit.class,
                IntegrationGalacticShop.class,
                IntegrationGalacticTokens.class,
                IntegrationLuckPerms.class,
                IntegrationPlotSquared.class,
                IntegrationSilkSpawners.class,
                IntegrationWorldEdit.class,

                MixinInventory.class,
                MixinTeleport.class,

                NmsArmorStandPacket.class,
                NmsChestPacket.class,
                NmsPacket.class,
                NmsPing.class,
                NmsSkullTexture.class,
                NmsTps.class,
                NmsWorldBorder.class
        );
    }

    @Override
    public IShopHandler getShopHandler() { return this.shopHandler == null? this.fallbackShopHandler : this.shopHandler; }
    @Override
    public IDataHandler getDataHandler() { return this.dataHandler == null? this.fallbackDataHandler : this.dataHandler; }
    @Override
    public ITokenHandler getTokenHandler() { return this.tokenHandler == null? this.fallbackTokenHandler : this.tokenHandler; }
    @Override
    public ISpawnerHandler getSpawnerHandler() { return this.spawnerHandler == null? this.fallbackSpawnerHandler : this.spawnerHandler; }

    public void registerShopHandler(IShopHandler shopHandler) {
        if(this.shopHandler != null) {
            GalacticLib.get().log(Level.SEVERE, "An issue occurred when registering the new shop handler '" + shopHandler.getClass().getSimpleName() + "', as a shop handler is already set.");
            return;
        }

        GalacticLib.get().log("Shop handler has now been set to " + shopHandler.getClass().getSimpleName() + ".");
        this.shopHandler = shopHandler;
    }
    public void registerDataHandler(IDataHandler dataHandler) {
        if(this.dataHandler != null) {
            GalacticLib.get().log(Level.SEVERE, "An issue occurred when registering the new data handler '" + dataHandler.getClass().getSimpleName() + "', as a data handler is already set.");
            return;
        }

        GalacticLib.get().log("Data handler has now been set to " + dataHandler.getClass().getSimpleName() + ".");
        this.dataHandler = dataHandler;
    }
    public void registerTokenHandler(ITokenHandler tokenHandler) {
        if(this.tokenHandler != null) {
            GalacticLib.get().log(Level.SEVERE, "An issue occurred when registering the new token handler '" + tokenHandler.getClass().getSimpleName() + "', as a token handler is already set.");
            return;
        }

        GalacticLib.get().log("Token handler has now been set to " + tokenHandler.getClass().getSimpleName() + ".");
        this.tokenHandler = tokenHandler;
    }
    public void registerSpawnerHandler(ISpawnerHandler spawnerHandler) {
        if(this.spawnerHandler != null) {
            GalacticLib.get().log(Level.SEVERE, "An issue occurred when registering the new spawner handler '" + spawnerHandler.getClass().getSimpleName() + "', as a token handler is already set.");
            return;
        }

        GalacticLib.get().log("Spawner handler has now been set to " + spawnerHandler.getClass().getSimpleName() + ".");
        this.spawnerHandler = spawnerHandler;
    }

}
