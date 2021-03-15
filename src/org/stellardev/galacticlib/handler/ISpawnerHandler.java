package org.stellardev.galacticlib.handler;

import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public interface ISpawnerHandler {

    EntityType getEntityTypeFromItem(ItemStack itemStack);

    ItemStack getSpawnerItem(int amount, EntityType entityType);

    default EntityType getEntityTypeFromLocation(Location location) {
        return null;
    }

    default String getSpawnerTypeFromLocation(Location location) {
        return null;
    }

}
