package org.stellardev.galacticlib.gui.types;

import com.massivecraft.massivecore.MassiveCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.gui.BaseGui;
import org.stellardev.galacticlib.gui.configuration.GuiDesign;

public abstract class RefreshGui extends BaseGui {

    private int refreshRunnableId = -1;

    public RefreshGui(Player player, GuiDesign guiDesign) {
        super(player, guiDesign);

        addGuiCloseTask(event -> stopRefresh());
    }

    public abstract long getRefreshTicks();

    public abstract void refresh();

    @Override
    protected void loadGui() {
        long refreshTicks = getRefreshTicks();

        if(refreshTicks > 0) {
            this.refreshRunnableId = Bukkit.getScheduler().runTaskTimer(MassiveCore.get(), this::refresh, refreshTicks, refreshTicks).getTaskId();
        }

        super.loadGui();
    }

    public void stopRefresh() {
        if(this.refreshRunnableId != -1) {
            Bukkit.getServer().getScheduler().cancelTask(this.refreshRunnableId);
            this.refreshRunnableId = -1;
        }
    }
}
