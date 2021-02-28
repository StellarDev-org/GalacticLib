package org.stellardev.galacticlib.integration.galacticskyblock;

import com.massivecraft.massivecore.Engine;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.handler.IDataHandler;
import org.stellardev.galacticskyblock.api.SkyBlockAPI;
import org.stellardev.galacticskyblock.coll.IslandColl;

import java.util.ArrayList;
import java.util.List;

public class EngineGalacticSkyBlock extends Engine implements IDataHandler {

    private static final EngineGalacticSkyBlock i = new EngineGalacticSkyBlock();
    public static EngineGalacticSkyBlock get() { return i; }

    @Override
    public boolean isInValidWorld(Location location) {
        return SkyBlockAPI.isInIslandWorld(location);
    }

    @Override
    public String getEntityHandler(Location location) {
        return SkyBlockAPI.getIslandId(location);
    }

    @Override
    public String getEntityHandler(String input) {
        return SkyBlockAPI.getIslandId(input);
    }

    @Override
    public boolean canAccess(Player player, Location location) {
        return SkyBlockAPI.canAccess(player, location);
    }

    @Override
    public boolean isSameLand(Location location1, Location location2) {
        return SkyBlockAPI.isSameLand(location1, location2);
    }

    @Override
    public List<String> getListOfEntityIds() {
        return new ArrayList<>(IslandColl.get().getIds());
    }

    @Override
    public List<Player> getListOfOnlinePlayers(String entityId) {
        return SkyBlockAPI.getListOfOnlinePlayers(entityId);
    }
}
