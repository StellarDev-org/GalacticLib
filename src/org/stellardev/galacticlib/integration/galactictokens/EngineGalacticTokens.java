package org.stellardev.galacticlib.integration.galactictokens;

import com.massivecraft.massivecore.Engine;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.handler.ITokenHandler;
import org.stellardev.galactictokens.api.TokenAPI;
import org.stellardev.galactictokens.object.ITPlayer;

public class EngineGalacticTokens extends Engine implements ITokenHandler {

    private static final EngineGalacticTokens i = new EngineGalacticTokens();
    public static EngineGalacticTokens get() { return i; }

    @Override
    public long getTokenBalance(Player player) {
        return getPlayer(player).getTokenAmount();
    }

    @Override
    public void addTokens(Player player, long amount) {
        getPlayer(player).addTokenAmount(amount);
    }

    @Override
    public void removeTokens(Player player, long amount) {
        getPlayer(player).takeTokenAmount(amount);
    }

    @Override
    public boolean hasTokens(Player player, long amount) {
        return getPlayer(player).hasTokenAmount(amount);
    }

    private ITPlayer getPlayer(Player player) {
        return TokenAPI.getPlayer(player);
    }
}
