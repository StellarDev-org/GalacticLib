package org.stellardev.galacticlib.integration.galacticskyblock;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.IDataHandler;
import org.stellardev.galacticlib.provider.IDataHandlerProvider;

public class IntegrationGalacticSkyBlock extends Integration implements IDataHandlerProvider {

    private static final IntegrationGalacticSkyBlock i = new IntegrationGalacticSkyBlock();
    public static IntegrationGalacticSkyBlock get() { return i; }

    private IntegrationGalacticSkyBlock() {
        setPluginName("GalacticSkyBlock");
    }

    @Override
    public Engine getEngine() {
        return EngineGalacticSkyBlock.get();
    }

    @Override
    public IDataHandler getDataHandler() {
        return EngineGalacticSkyBlock.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        GalacticLib.get().registerDataHandler(getDataHandler());
    }
}
