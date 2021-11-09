package org.stellardev.galacticlib.integration.silkspawners;

import com.massivecraft.massivecore.Engine;
import de.dustplanet.util.SilkUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.stellardev.galacticlib.handler.ISpawnerHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EngineSilkSpawners extends Engine implements ISpawnerHandler {

    private static final EngineSilkSpawners i = new EngineSilkSpawners();
    public static EngineSilkSpawners get() { return i; }

    private final Map<String, EntityType> entityMap = new HashMap<>();
    private SilkUtil silkUtil;

    private EngineSilkSpawners() {
        for(EntityType entityType : EntityType.values()) {
            this.entityMap.put(entityType.name().replace("_", ""), entityType);

            String name = entityType.getName();

            if(name != null) {
                this.entityMap.put(name, entityType);
                this.entityMap.put(name.replace("_", ""), entityType);
            }
        }
    }

    @Override
    public EntityType getEntityTypeFromItem(ItemStack itemStack) {
        try {
            BlockStateMeta blockStateMeta = ((BlockStateMeta) itemStack.getItemMeta());
            CreatureSpawner creatureSpawner = (CreatureSpawner) blockStateMeta.getBlockState();
            EntityType entityType = creatureSpawner.getSpawnedType();

            if(entityType != EntityType.PIG) {
                return entityType;
            }
        } catch (Exception ignore) {}

        String spawnerId = getSilkUtil().getStoredSpawnerItemEntityID(itemStack);

        if(spawnerId == null) {
            return null;
        }

        try {
            return EntityType.valueOf(spawnerId.toUpperCase());
        } catch (Exception ex) {
            Map.Entry<String, EntityType> entry = this.entityMap.entrySet().stream()
                    .filter(ent -> ent.getKey().equalsIgnoreCase(spawnerId))
                    .findFirst()
                    .orElse(null);

            if(entry != null) return entry.getValue();

            SpawnerIdToEntityType spawnerIdToEntityType = SpawnerIdToEntityType.getById(spawnerId);

            if(spawnerIdToEntityType == null) {
                return null;
            }

            return spawnerIdToEntityType.getEntityType();
        }
    }

    @Override
    public ItemStack getSpawnerItem(int amount, EntityType entityType) {
        if(entityType == null) return new ItemStack(Material.SPAWNER, amount);

        String entityName = entityType.name().toLowerCase();

        return getSilkUtil().newSpawnerItem(entityName, getSilkUtil().getCustomSpawnerName(entityName), amount, false);
    }

    private SilkUtil getSilkUtil() {
        return this.silkUtil == null? (this.silkUtil = SilkUtil.hookIntoSilkSpanwers()) : this.silkUtil;
    }

    public enum SpawnerIdToEntityType {

        mooshroom(EntityType.MUSHROOM_COW, "mooshroom", "mushroomcow"),
//        pigzombie(EntityType.PIG_ZOMBIE, "zombie_pigman", "pigzombie"),
        snowgolem(EntityType.SNOWMAN, "snow_golem");

        private final EntityType entityType;
        private final List<String> spawnerId;

        SpawnerIdToEntityType(EntityType entityType, String... spawnerId) {
            this.spawnerId = Arrays.asList(spawnerId);
            this.entityType = entityType;
        }

        public EntityType getEntityType() {
            return this.entityType;
        }

        public List<String> getSpawnerId() {
            return this.spawnerId;
        }

        public static SpawnerIdToEntityType getById(String spawnerId) {
            return Arrays.stream(values())
                    .filter(spawnerIdToEntityType -> spawnerIdToEntityType.getSpawnerId().stream().anyMatch(spawnerId::equalsIgnoreCase))
                    .findFirst()
                    .orElse(null);
        }
    }

}
