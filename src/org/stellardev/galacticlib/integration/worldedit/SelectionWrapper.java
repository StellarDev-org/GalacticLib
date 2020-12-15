package org.stellardev.galacticlib.integration.worldedit;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SelectionWrapper {

    private final Location minLocation, maxLocation;
    private final Player player;

    public SelectionWrapper(Player player, Location minLocation, Location maxLocation) {
        this.player = player;
        this.minLocation = minLocation;
        this.maxLocation = maxLocation;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Location getMinLocation() {
        return this.minLocation;
    }

    public Location getMaxLocation() {
        return this.maxLocation;
    }
}
