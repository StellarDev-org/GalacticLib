package org.stellardev.galacticlib.integration.worldedit;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.bukkit.entity.Player;

public class IntegrationWorldEdit extends Integration {

    private static final IntegrationWorldEdit i = new IntegrationWorldEdit();
    public static IntegrationWorldEdit get() { return i; }

    private IntegrationWorldEdit() {
        setPluginName("WorldEdit");
    }

    @Override
    public Engine getEngine() {
        return EngineWorldEdit.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        EngineWorldEdit.get().load();
    }

    public SelectionWrapper getSelection(Player player) {
        if(!isIntegrationActive()) return null;

        return EngineWorldEdit.get().getSelection(player);
    }
}
