package org.stellardev.galacticlib.integration.aquacore;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.IPermissionHandler;
import org.stellardev.galacticlib.provider.IPermissionHandlerProvider;

public class IntegrationAquaCore extends Integration implements IPermissionHandlerProvider {

    private static final IntegrationAquaCore instance = new IntegrationAquaCore();
    public static IntegrationAquaCore get() { return instance; }

    private IntegrationAquaCore() {
        this.setPluginName("AquaCore");
    }

    @Override
    public Engine getEngine() {
        return EngineAquaCore.get();
    }

    @Override
    public IPermissionHandler getPermissionHandler() {
        return EngineAquaCore.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        GalacticLib.get().registerPermissionHandler(getPermissionHandler());
    }
}
