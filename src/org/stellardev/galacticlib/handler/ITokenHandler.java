package org.stellardev.galacticlib.handler;

import org.bukkit.entity.Player;

public interface ITokenHandler {

    long getTokenBalance(Player player);

    void addTokens(Player player, long amount);

    void removeTokens(Player player, long amount);

    boolean hasTokens(Player player, long amount);

}
