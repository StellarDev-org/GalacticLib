package org.stellardev.galacticlib.integration.silkspawners;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.ISpawnerHandler;
import org.stellardev.galacticlib.provider.ISpawnerHandlerProvider;

public class IntegrationSilkSpawners extends Integration implements ISpawnerHandlerProvider {

    private static final IntegrationSilkSpawners i = new IntegrationSilkSpawners();
    public static IntegrationSilkSpawners get() { return i; }

    private IntegrationSilkSpawners() {
        this.setPluginName("SilkSpawners");
    }

    @Override
    public Engine getEngine() {
        return EngineSilkSpawners.get();
    }

    @Override
    public ISpawnerHandler getSpawnerHandler() {
        return EngineSilkSpawners.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        GalacticLib.get().registerSpawnerHandler(getSpawnerHandler());
    }
}
