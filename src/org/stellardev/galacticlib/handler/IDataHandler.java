package org.stellardev.galacticlib.handler;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public interface IDataHandler {

    boolean isInValidWorld(Location location);

    String getEntityHandler(Location location);

    String getEntityHandler(String input);

    boolean canAccess(Player player, Location location);

    boolean isSameLand(Location location1, Location location2);

    List<String> getListOfEntityIds();

    List<Player> getListOfOnlinePlayers(String entityId);

}
