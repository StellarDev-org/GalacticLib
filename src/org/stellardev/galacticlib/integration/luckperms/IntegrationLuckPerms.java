package org.stellardev.galacticlib.integration.luckperms;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.IPermissionHandler;
import org.stellardev.galacticlib.provider.IPermissionHandlerProvider;

public class IntegrationLuckPerms extends Integration implements IPermissionHandlerProvider {

    private static final IntegrationLuckPerms instance = new IntegrationLuckPerms();
    public static IntegrationLuckPerms get() { return instance; }

    private IntegrationLuckPerms() {
        this.setPluginName("LuckPerms");
    }

    @Override
    public Engine getEngine() {
        return EngineLuckPerms.get();
    }

    @Override
    public IPermissionHandler getPermissionHandler() {
        return EngineLuckPerms.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        GalacticLib.get().registerPermissionHandler(getPermissionHandler());
    }
}
