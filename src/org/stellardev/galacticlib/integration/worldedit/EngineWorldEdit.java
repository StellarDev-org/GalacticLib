package org.stellardev.galacticlib.integration.worldedit;

import com.massivecraft.massivecore.Engine;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class EngineWorldEdit extends Engine {

    private static final EngineWorldEdit i = new EngineWorldEdit();
    public static EngineWorldEdit get() { return i; }

    private WorldEditPlugin worldEditPlugin;

    public void load() {
        this.worldEditPlugin = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
    }

    public SelectionWrapper getSelection(Player player) {
        Selection selection = this.worldEditPlugin.getSelection(player);

        if(selection == null) return null;

        World world = selection.getWorld();

        if(world == null) return null;
        if(selection.getMinimumPoint() == null || selection.getMaximumPoint() == null) return null;

        return new SelectionWrapper(player, selection.getMinimumPoint(), selection.getMaximumPoint());
    }
}
