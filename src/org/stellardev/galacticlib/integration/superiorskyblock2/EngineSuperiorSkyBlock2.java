package org.stellardev.galacticlib.integration.superiorskyblock2;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.island.IslandPrivilege;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.massivecraft.massivecore.Engine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.handler.IDataHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class EngineSuperiorSkyBlock2 extends Engine implements IDataHandler {

    private static final EngineSuperiorSkyBlock2 i = new EngineSuperiorSkyBlock2();
    public static EngineSuperiorSkyBlock2 get() { return i; }

    @Override
    public boolean isInValidWorld(Location location) {
        Island island = SuperiorSkyblockAPI.getIslandAt(location);

        return island != null;
    }

    @Override
    public String getEntityHandler(Location location) {
        Island island = SuperiorSkyblockAPI.getIslandAt(location);

        return island != null? island.getOwner().getUniqueId().toString() : null;
    }

    @Override
    public String getEntityHandler(String input) {
        Island island = SuperiorSkyblockAPI.getGrid().getIslands().stream()
                .filter(Objects::nonNull)
                .filter(is -> Bukkit.getOfflinePlayer(is.getOwner().getUniqueId()).getName().equalsIgnoreCase(input))
                .findFirst()
                .orElse(null);

        return island == null? null : island.getOwner().getUniqueId().toString();
    }

    @Override
    public boolean canAccess(Player player, Location location) {
        Island island = SuperiorSkyblockAPI.getIslandAt(location);

        if (island == null) return true;

        UUID owner = island.getOwner().getUniqueId();
        UUID playerUUID = player.getUniqueId();

        if (owner == null || owner.equals(playerUUID)) return true;

        SuperiorPlayer superiorPlayer = SuperiorSkyblockAPI.getPlayer(playerUUID);

        return island.hasPermission(superiorPlayer, IslandPrivilege.getByName("BUILD"));
    }

    @Override
    public boolean isSameLand(Location location1, Location location2) {
        Island island1 = SuperiorSkyblockAPI.getIslandAt(location1);
        Island island2 = SuperiorSkyblockAPI.getIslandAt(location2);

        return island1 != null && island1.equals(island2);
    }

    @Override
    public List<String> getListOfEntityIds() {
        return SuperiorSkyblockAPI.getGrid().getIslands().stream()
                .filter(Objects::nonNull)
                .map(island -> Bukkit.getOfflinePlayer(island.getOwner().getUniqueId()).getName())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getListOfOnlinePlayers(String entityId) {
        Island island = SuperiorSkyblockAPI.getIsland(entityId);

        if(island == null) return new ArrayList<>();

        return island.getIslandMembers(true).stream()
                .map(superiorPlayer -> Bukkit.getPlayer(superiorPlayer.getUniqueId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
