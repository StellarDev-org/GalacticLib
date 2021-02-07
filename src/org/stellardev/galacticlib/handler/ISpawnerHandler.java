package org.stellardev.galacticlib.handler;

import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public interface ISpawnerHandler {

    EntityType getEntityTypeFromItem(ItemStack itemStack);

    ItemStack getSpawnerItem(int amount, EntityType entityType);

    default EntityType getEntityTypeFromLocation(Location location) {
        if(!(location.getBlock().getState() instanceof CreatureSpawner)) return null;

        CreatureSpawner creatureSpawner = (CreatureSpawner) location.getBlock().getState();

        return creatureSpawner.getSpawnedType();
    }

    default String getSpawnerTypeFromLocation(Location location) {
        return null;
    }

}
