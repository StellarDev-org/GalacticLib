package org.stellardev.galacticlib.integration.factionsuuid;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.IDataHandler;
import org.stellardev.galacticlib.provider.IDataHandlerProvider;

public class IntegrationFactionsUUID extends Integration implements IDataHandlerProvider {

    private static final IntegrationFactionsUUID i = new IntegrationFactionsUUID();
    public static IntegrationFactionsUUID get() { return i; }

    private IntegrationFactionsUUID() {
        setPluginName("Factions");
    }

    @Override
    public Engine getEngine() {
        return EngineFactionsUUID.get();
    }

    @Override
    public IDataHandler getDataHandler() {
        return EngineFactionsUUID.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        JavaPlugin plugin = (JavaPlugin) Bukkit.getServer().getPluginManager().getPlugin("Factions");

        if(plugin.getDescription().getAuthors().contains("drtshock")) {
            GalacticLib.get().registerDataHandler(getDataHandler());
        }
    }
}
