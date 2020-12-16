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

    private com.intellectualcrafters.plot.object.Location getPlotLocation(Location location) {
        return BukkitUtil.getLocation(location);
    }
}
