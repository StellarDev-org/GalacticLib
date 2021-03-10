package org.stellardev.galacticlib.integration.superiorskyblock2;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.IDataHandler;
import org.stellardev.galacticlib.provider.IDataHandlerProvider;

public class IntegrationSuperiorSkyblock2 extends Integration implements IDataHandlerProvider {

    private static final IntegrationSuperiorSkyblock2 i = new IntegrationSuperiorSkyblock2();
    public static IntegrationSuperiorSkyblock2 get() { return i; }

    private IntegrationSuperiorSkyblock2() {
        setPluginName("SuperiorSkyblock2");
    }

    @Override
    public Engine getEngine() {
        return EngineSuperiorSkyBlock2.get();
    }

    @Override
    public IDataHandler getDataHandler() {
        return EngineSuperiorSkyBlock2.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        GalacticLib.get().registerDataHandler(getDataHandler());
    }
}
