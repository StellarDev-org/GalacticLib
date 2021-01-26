package org.stellardev.galacticlib.handler;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public interface ISpawnerHandler {

    EntityType getEntityTypeFromItem(ItemStack itemStack);

    ItemStack getSpawnerItem(int amount, EntityType entityType);

}
