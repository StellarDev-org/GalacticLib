package org.stellardev.galacticlib.integration.plotsquared;

import com.intellectualcrafters.plot.object.Plot;
import com.massivecraft.massivecore.Engine;
import com.plotsquared.bukkit.util.BukkitUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.handler.IDataHandler;

public class EnginePlotSquared extends Engine implements IDataHandler {

    private static final EnginePlotSquared i = new EnginePlotSquared();
    public static EnginePlotSquared get() { return i; }

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

    private com.intellectualcrafters.plot.object.Location getPlotLocation(Location location) {
        return BukkitUtil.getLocation(location);
    }
}
