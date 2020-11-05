package org.stellardev.galacticlib;

import com.massivecraft.massivecore.MassivePlugin;
import org.stellardev.galacticlib.coll.ConfColl;
import org.stellardev.galacticlib.engine.EngineGui;
import org.stellardev.galacticlib.nms.NmsSkullTexture;

public class GalacticLib extends MassivePlugin {

    private static GalacticLib i;
    public static GalacticLib get() { return i; }

    public GalacticLib() {
        i = this;
    }

    @Override
    public void onEnableInner() {
        this.activate(
                ConfColl.class,

                EngineGui.class,

                NmsSkullTexture.class
        );
    }
}
