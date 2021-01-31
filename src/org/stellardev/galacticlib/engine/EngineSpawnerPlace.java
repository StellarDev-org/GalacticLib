package org.stellardev.galacticlib.engine;

import com.massivecraft.massivecore.Engine;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.event.SpawnerPlaceEvent;
import org.stellardev.galacticlib.handler.ISpawnerHandler;
import org.stellardev.galacticlib.util.LibUtil;

public class EngineSpawnerPlace extends Engine {

    private static final EngineSpawnerPlace i = new EngineSpawnerPlace();
    public static EngineSpawnerPlace get() { return i; }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Block clickedBlock = event.getBlockAgainst();

        if(block.getType() != Material.MOB_SPAWNER) return;

        CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();
        EntityType entityType = creatureSpawner.getSpawnedType();

        if(entityType == EntityType.PIG) {
            ISpawnerHandler spawnerHandler = GalacticLib.get().getSpawnerHandler();
            EntityType typeFromItem = spawnerHandler.getEntityTypeFromItem(event.getItemInHand());

            if(typeFromItem == null) {
                event.setCancelled(true);
                return;
            }

            entityType = typeFromItem;
        }

        SpawnerPlaceEvent spawnerPlaceEvent = new SpawnerPlaceEvent(block, clickedBlock, player, creatureSpawner, entityType);
        LibUtil.callEvent(spawnerPlaceEvent);

        if(spawnerPlaceEvent.isCancelled()) {
            event.setCancelled(true);
            return;
        }

        creatureSpawner.setSpawnedType(spawnerPlaceEvent.getSpawnEntityType());
    }
}
