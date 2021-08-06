package org.stellardev.galacticlib.integration.plotsquared;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotId;
import com.massivecraft.massivecore.Engine;
import com.plotsquared.bukkit.util.BukkitUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.handler.IDataHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EnginePlotSquared extends Engine implements IDataHandler {

    private static final EnginePlotSquared i = new EnginePlotSquared();
    public static EnginePlotSquared get() { return i; }

    private PlotAPI plotAPI;

    @Override
    public void setActiveInner(boolean active) {
        if(!active) return;

        this.plotAPI = new PlotAPI();
    }

    @Override
    public boolean isInValidWorld(Location location) {
        return getPlotLocation(location).getPlotArea() != null;
    }

    @Override
    public String getEntityHandler(Location location) {
        Plot plot = getPlotLocation(location).getPlot();

        if(plot == null) return null;

        return plot.getId().toString();
    }

    @Override
    public String getEntityHandler(String input) {
        return getListOfEntityIds().stream()
                .filter(id -> id.equalsIgnoreCase(input))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String getEntityHandler(Player player) {
        Plot plot = plotAPI.getPlot(player);

        if(plot == null) return null;

        return plot.getId().toString();
    }

    @Override
    public boolean canAccess(Player player, Location location) {
        Plot plot = getPlotLocation(location).getPlot();

        if(plot == null) return false;

        return plot.isAdded(player.getUniqueId());
    }

    @Override
    public boolean isSameLand(Location location1, Location location2) {
        Plot plot1 = getPlotLocation(location1).getPlot();
        Plot plot2 = getPlotLocation(location2).getPlot();

        if(plot1 == null || plot2 == null) return false;

        return plot1.getId().equals(plot2.getId());
    }

    @Override
    public List<String> getListOfEntityIds() {
        List<String> ids = new ArrayList<>();

        this.plotAPI.getAllPlots().stream().map(Plot::getId).forEach(id -> ids.add(id.toString()));

        return ids;
    }

    @Override
    public List<Player> getListOfOnlinePlayers(String entityId) {
        PlotId plotId = PlotId.fromString(entityId);

        Plot plot = this.plotAPI.getAllPlots().stream()
                .filter(pl -> pl.getId().equals(plotId))
                .findFirst()
                .orElse(null);

        if(plot == null) return new ArrayList<>();

        return plot.getMembers().stream()
                .map(Bukkit::getPlayer)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private com.intellectualcrafters.plot.object.Location getPlotLocation(Location location) {
        return BukkitUtil.getLocation(location);
    }
}
