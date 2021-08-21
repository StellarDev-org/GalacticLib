package org.stellardev.galacticlib.integration.factionsuuid;

import com.massivecraft.factions.*;
import com.massivecraft.massivecore.Engine;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.handler.IDataHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EngineFactionsUUID extends Engine implements IDataHandler {

    private static final EngineFactionsUUID i = new EngineFactionsUUID();
    public static EngineFactionsUUID get() { return i; }

    @Override
    public boolean isInValidWorld(Location location) {
        return true;
    }

    @Override
    public String getEntityHandler(Location location) {
        return Board.getInstance().getIdAt(new FLocation(location));
    }

    @Override
    public String getEntityHandler(String input) {
        Faction faction = Factions.getInstance().getFactionById(input);

        if(faction == null) return null;

        return faction.getId();
    }

    @Override
    public String getEntityHandler(Player player) {
        return FPlayers.getInstance().getByPlayer(player).getFactionId();
    }

    @Override
    public boolean canAccess(Player player, Location location) {
        Faction landFaction = Board.getInstance().getFactionAt(new FLocation(location));

        if(landFaction.isWilderness()) return true;

        Faction playerFaction = FPlayers.getInstance().getByPlayer(player).getFaction();

        return playerFaction.equals(landFaction);
    }

    @Override
    public boolean isSameLand(Location location1, Location location2) {
        Faction faction1 = Board.getInstance().getFactionAt(new FLocation(location1));
        Faction faction2 = Board.getInstance().getFactionAt(new FLocation(location2));
        return faction1.equals(faction2);
    }

    @Override
    public List<String> getListOfEntityIds() {
        return Factions.getInstance().getAllFactions().stream()
                .map(Faction::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getListOfOnlinePlayers(String entityId) {
        Faction faction = Factions.getInstance().getFactionById(entityId);
    
        if(faction == null) return Collections.emptyList();
    
        return faction.getOnlinePlayers();
    }
}
