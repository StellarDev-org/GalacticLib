package org.stellardev.galacticlib.integration.worldedit;

import com.massivecraft.massivecore.Engine;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EngineWorldEdit extends Engine {

    private static final EngineWorldEdit i = new EngineWorldEdit();
    public static EngineWorldEdit get() { return i; }

    private WorldEditPlugin worldEditPlugin;

    public void load() {
        this.worldEditPlugin = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    }

    public SelectionWrapper getSelection(Player player) {
        Region selection = this.worldEditPlugin.getSession(player).getSelection();

        if(selection == null) return null;

        World world = selection.getWorld();

        if(world == null) return null;
        if(selection.getMinimumPoint() == null || selection.getMaximumPoint() == null) return null;

        org.bukkit.World bukkitWorld = this.worldEditPlugin.getServer().getWorld(world.getName());

        BlockVector3 minPoint = selection.getMinimumPoint();
        Location minimumPoint = new Location(bukkitWorld, minPoint.getBlockX(), minPoint.getBlockY(), minPoint.getBlockZ());

        BlockVector3 maxPoint = selection.getMaximumPoint();
        Location maximumPoint = new Location(bukkitWorld, maxPoint.getBlockX(), maxPoint.getBlockY(), maxPoint.getBlockZ());

        return new SelectionWrapper(player, minimumPoint, maximumPoint);
    }
}
