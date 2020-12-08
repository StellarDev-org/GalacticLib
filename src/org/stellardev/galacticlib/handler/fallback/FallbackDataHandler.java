package org.stellardev.galacticlib.handler.fallback;

import org.bukkit.Location;
import org.bukkit.World;
import org.stellardev.galacticlib.handler.IDataHandler;

public class FallbackDataHandler implements IDataHandler {

    @Override
    public boolean isInValidWorld(World world) {
        return true;
    }

    @Override
    public String getEntityHandler(Location location) {
        return location.getWorld().getName();
    }

    @Override
    public boolean isValid(Location location) {
        return true;
    }
}
