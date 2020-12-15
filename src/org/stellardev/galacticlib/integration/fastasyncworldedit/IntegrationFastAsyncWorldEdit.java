package org.stellardev.galacticlib.integration.fastasyncworldedit;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.stellardev.galacticlib.GalacticLib;

import java.util.Map;

public class IntegrationFastAsyncWorldEdit extends Integration {

    private static final IntegrationFastAsyncWorldEdit i = new IntegrationFastAsyncWorldEdit();
    public static IntegrationFastAsyncWorldEdit get() { return i; }

    private IntegrationFastAsyncWorldEdit() {
        setPluginName("FastAsyncWorldEdit");
    }

    @Override
    public Engine getEngine() {
        return EngineFastAsyncWorldEdit.get();
    }

    public void pasteBlocks(Location min, Location max, Map<Material, Double> weightMap) {
        if(!isIntegrationActive()) {
            GalacticLib.get().log("FastAsyncWorldEdit is not active, and therefore didn't paste blocks.");
            return;
        }

        EngineFastAsyncWorldEdit.get().pasteBlocks(min, max, weightMap);
    }
}
