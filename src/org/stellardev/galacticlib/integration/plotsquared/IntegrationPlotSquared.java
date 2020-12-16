package org.stellardev.galacticlib.integration.plotsquared;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import lombok.SneakyThrows;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.exception.HandlerAlreadyRegisteredException;
import org.stellardev.galacticlib.handler.IDataHandler;
import org.stellardev.galacticlib.provider.IDataHandlerProvider;

public class IntegrationPlotSquared extends Integration implements IDataHandlerProvider {

    private static final IntegrationPlotSquared i = new IntegrationPlotSquared();
    public static IntegrationPlotSquared get() { return i; }

    private IntegrationPlotSquared() {
        setPluginName("PlotSquared");
    }

    @Override
    public Engine getEngine() {
        return EnginePlotSquared.get();
    }

    @Override
    public IDataHandler getDataHandler() {
        return EnginePlotSquared.get();
    }

    @Override
    public void setIntegrationActiveInner(boolean active) {
        if(!active) return;

        try {
            GalacticLib.get().registerDataHandler(getDataHandler());
        } catch (HandlerAlreadyRegisteredException ignore) {
            GalacticLib.get().log("An issue occurred when registering the new data handler PlotSquared, as a data handler is already set.");
        }
    }
}
