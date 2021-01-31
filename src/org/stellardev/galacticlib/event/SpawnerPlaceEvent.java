package org.stellardev.galacticlib.event;

import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SpawnerPlaceEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final CreatureSpawner creatureSpawner;
    private final Block block, clickedBlock;
    private final Player player;

    private EntityType spawnEntityType;
    private boolean cancelled = false;

    public SpawnerPlaceEvent(Block block, Block clickedBlock, Player player, CreatureSpawner creatureSpawner, EntityType entityType) {
        this.clickedBlock = clickedBlock;
        this.block = block;
        this.player = player;
        this.creatureSpawner = creatureSpawner;
        this.spawnEntityType = entityType;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public Block getBlock() {
        return this.block;
    }

    public Block getClickedBlock() {
        return this.clickedBlock;
    }

    public CreatureSpawner getCreatureSpawner() {
        return this.creatureSpawner;
    }

    public EntityType getSpawnEntityType() {
        return this.spawnEntityType;
    }

    public void setSpawnEntityType(EntityType spawnEntityType) {
        this.spawnEntityType = spawnEntityType;
    }

    public Player getPlayer() {
        return this.player;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
