package org.stellardev.galacticlib.integration.placeholderapi;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.bukkit.entity.Player;

public abstract class PapiExpansionIntegration extends Integration {

    public PapiExpansionIntegration() {
        setPluginName("PlaceholderAPI");
    }

    public abstract PapiExpansionEngine getEngineInstance();

    @Override
    public Engine getEngine() {
        return getEngineInstance();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(active) {
            getEngineInstance().register();
        } else {
            getEngineInstance().unregister();
        }
    }

    public String setPlaceholders(Player player, String input) {
        return getEngineInstance().setPlaceholders(player, input);
    }
}
