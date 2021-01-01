package org.stellardev.galacticlib.integration.galactictokens;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.ITokenHandler;
import org.stellardev.galacticlib.provider.ITokenHandlerProvider;

public class IntegrationGalacticTokens extends Integration implements ITokenHandlerProvider {

    private static final IntegrationGalacticTokens i = new IntegrationGalacticTokens();
    public static IntegrationGalacticTokens get() { return i; }

    private IntegrationGalacticTokens() {
        setPluginName("GalacticTokens");
    }

    @Override
    public Engine getEngine() {
        return EngineGalacticTokens.get();
    }

    @Override
    public ITokenHandler getTokenHandler() {
        return EngineGalacticTokens.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        GalacticLib.get().registerTokenHandler(getTokenHandler());
    }
}
