package org.stellardev.galacticlib.handler.fallback;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.stellardev.galacticlib.handler.ISpawnerHandler;

public class FallbackSpawnerHandler implements ISpawnerHandler {

    @Override
    public EntityType getEntityTypeFromItem(ItemStack itemStack) {
        BlockStateMeta blockStateMeta = (BlockStateMeta) itemStack.getItemMeta();
        CreatureSpawner creatureSpawner = (CreatureSpawner) blockStateMeta.getBlockState();

        return creatureSpawner.getSpawnedType();
    }

    @Override
    public ItemStack getSpawnerItem(int amount, EntityType entityType) {
        ItemStack itemStack = new ItemStack(Material.SPAWNER, amount);
        BlockStateMeta blockStateMeta = (BlockStateMeta) itemStack.getItemMeta();
        BlockState blockState = blockStateMeta.getBlockState();

        ((CreatureSpawner) blockState).setSpawnedType(entityType);

        blockStateMeta.setBlockState(blockState);
        itemStack.setItemMeta(blockStateMeta);

        return itemStack;
    }
}
