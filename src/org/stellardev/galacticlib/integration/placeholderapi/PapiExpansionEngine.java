package org.stellardev.galacticlib.integration.placeholderapi;

import com.massivecraft.massivecore.Engine;

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

}
