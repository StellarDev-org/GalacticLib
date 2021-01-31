package org.stellardev.galacticlib.handler.fallback;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.handler.IDataHandler;

import java.util.ArrayList;
import java.util.List;

public class FallbackDataHandler implements IDataHandler {

    @Override
    public boolean isInValidWorld(Location location) {
        return true;
    }

    @Override
    public String getEntityHandler(Location location) {
        return location.getWorld().getName();
    }

    @Override
    public String getEntityHandler(String input) {
        return null;
    }

    @Override
    public boolean canAccess(Player player, Location location) {
        return true;
    }

    @Override
    public boolean isSameLand(Location location1, Location location2) {
        return location1.getWorld().equals(location2.getWorld());
    }

    @Override
    public List<String> getListOfEntityIds() {
        return new ArrayList<>();
    }
}
