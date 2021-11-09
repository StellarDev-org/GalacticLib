package org.stellardev.galacticlib.integration.fastasyncworldedit;

import com.fastasyncworldedit.core.FaweAPI;
import com.massivecraft.massivecore.Engine;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.function.pattern.RandomPattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BaseBlock;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Map;

public class EngineFastAsyncWorldEdit extends Engine {

    private static final EngineFastAsyncWorldEdit i = new EngineFastAsyncWorldEdit();
    public static EngineFastAsyncWorldEdit get() { return i; }

    public int pasteBlocks(Location min, Location max, Map<Material, Double> weightMap) {
        EditSession editSession = getEditSession(min);
        Region region = getRegion(min, max);
        RandomPattern pattern = getPattern(weightMap);

        int amount = editSession.setBlocks(region, pattern);
        editSession.flushQueue();

        return amount;
    }

    public int pasteWalls(Location min, Location max, Map<Material, Double> weightMap) {
        EditSession editSession = getEditSession(min);
        Region region = getRegion(min, max);
        RandomPattern pattern = getPattern(weightMap);

        int amount = editSession.makeCuboidWalls(region, pattern);
        editSession.flushQueue();

        return amount;
    }

    private RandomPattern getPattern(Map<Material, Double> weightMap) {
        RandomPattern pattern = new RandomPattern();

        weightMap.forEach((material, chance) -> pattern.add(new BaseBlock(material.getId(), 0), chance));

        return pattern;
    }

    private Region getRegion(Location min, Location max) {
        BlockVector3 minVector = BlockVector3.at(min.getX(), min.getY(), min.getZ());
        BlockVector3 maxVector = BlockVector3.at(max.getX(), max.getY(), max.getZ());
        World world = FaweAPI.getWorld(min.getWorld().getName());

        return new CuboidRegion(world, minVector, maxVector);
    }

    private EditSession getEditSession(Location min) {
        return FaweAPI.getEditSessionBuilder(FaweAPI.getWorld(min.getWorld().getName()))
                .autoQueue(true)
                .checkMemory(false)
                .allowedRegionsEverywhere()
                .limitUnlimited()
                .changeSetNull()
                .fastmode(true)
                .build();


    }

}
