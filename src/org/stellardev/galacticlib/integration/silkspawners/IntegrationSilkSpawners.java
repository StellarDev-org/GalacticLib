package org.stellardev.galacticlib.integration.silkspawners;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class IntegrationSilkSpawners extends Integration {

    private static final IntegrationSilkSpawners instance = new IntegrationSilkSpawners();
    public static IntegrationSilkSpawners get() { return instance; }

    private IntegrationSilkSpawners() {
        this.setPluginName("SilkSpawners");
    }

    @Override
    public Engine getEngine() {
        return EngineSilkSpawners.get();
    }

    public EntityType getEntityTypeFromItemStack(ItemStack itemStack) {
        if(!isIntegrationActive()) {
            BlockStateMeta blockStateMeta = (BlockStateMeta) itemStack.getItemMeta();
            CreatureSpawner creatureSpawner = (CreatureSpawner) blockStateMeta.getBlockState();

            return creatureSpawner.getSpawnedType();
        }

        return EngineSilkSpawners.get().getEntityTypeFromItemStack(itemStack);
    }

    public ItemStack getSpawnerItem(int amount, EntityType entityType) {
        if(!isIntegrationActive()) {
            ItemStack itemStack = new ItemStack(Material.MOB_SPAWNER, amount);
            BlockStateMeta blockStateMeta = (BlockStateMeta) itemStack.getItemMeta();
            BlockState blockState = blockStateMeta.getBlockState();

            ((CreatureSpawner) blockState).setSpawnedType(entityType);

            blockStateMeta.setBlockState(blockState);
            itemStack.setItemMeta(blockStateMeta);

            return itemStack;
        }

        return EngineSilkSpawners.get().getSpawnerItem(amount, entityType);
    }
}
