package org.stellardev.galacticlib.handler.fallback;

import org.bukkit.entity.Player;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.ITokenHandler;

public class FallbackTokenHandler implements ITokenHandler {

    @Override
    public long getTokenBalance(Player player) {
        return 0;
    }

    @Override
    public void addTokens(Player player, long amount) {
        GalacticLib.get().log("Cannot add tokens as system is only using fallback.");
    }

    @Override
    public void removeTokens(Player player, long amount) {
        GalacticLib.get().log("Cannot remove tokens as system is only using fallback.");
    }

    @Override
    public boolean hasTokens(Player player, long amount) {
        return false;
    }
}
