package org.stellardev.galacticlib.handler;

import org.bukkit.Location;
import org.bukkit.World;

public interface IDataHandler {

    boolean isInValidWorld(World world);

    String getEntityHandler(Location location);

    boolean isValid(Location location);

}
