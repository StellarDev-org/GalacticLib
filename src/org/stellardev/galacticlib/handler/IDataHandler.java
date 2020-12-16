package org.stellardev.galacticlib.handler;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IDataHandler {

    boolean isInValidWorld(Location location);

    String getEntityHandler(Location location);

    boolean canAccess(Player player, Location location);

    boolean isSameLand(Location location1, Location location2);

}
