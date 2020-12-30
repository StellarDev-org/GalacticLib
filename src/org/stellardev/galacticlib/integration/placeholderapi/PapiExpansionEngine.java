package org.stellardev.galacticlib.integration.placeholderapi;

import com.massivecraft.massivecore.Engine;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public abstract class PapiExpansionEngine extends Engine {

    private GalacticExpansion galacticExpansion;

    public abstract GalacticExpansion getInstance();

    public void register() {
        this.galacticExpansion = getInstance();
        this.galacticExpansion.register();
    }

    public void unregister() {
        if(this.galacticExpansion != null) {
            this.galacticExpansion.unregister();
        }
    }

    public String setPlaceholders(Player player, String line) {
        return PlaceholderAPI.setPlaceholders(player, line);
    }

}
