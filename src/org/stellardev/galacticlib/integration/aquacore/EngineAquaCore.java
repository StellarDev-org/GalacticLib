package org.stellardev.galacticlib.integration.aquacore;

import com.massivecraft.massivecore.Engine;
import me.activated.core.api.player.GlobalPlayer;
import me.activated.core.api.player.PlayerData;
import me.activated.core.plugin.AquaCoreAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.handler.IPermissionHandler;

import java.util.UUID;

public class EngineAquaCore extends Engine implements IPermissionHandler {

    private static final EngineAquaCore instance = new EngineAquaCore();
    public static EngineAquaCore get() { return instance; }

    private final AquaCoreAPI aquaCoreApi;

    private EngineAquaCore() {
        this.aquaCoreApi = AquaCoreAPI.INSTANCE;
    }

    @Override
    public int getPermissionPoolTop(UUID uuid, String permissionBase, int cap) {
        GlobalPlayer globalPlayer = this.aquaCoreApi.getGlobalPlayer(uuid);

        if(globalPlayer == null) return -1;

        for(int i = cap; i >= 0; i--) {
            if(globalPlayer.hasPermission(permissionBase + i)) return i;
        }

        return -1;
    }

    @Override
    public boolean hasPermission(OfflinePlayer offlinePlayer, String permission) {
        GlobalPlayer globalPlayer = this.aquaCoreApi.getGlobalPlayer(offlinePlayer.getUniqueId());

        if(globalPlayer == null) return false;

        return globalPlayer.hasPermission(permission);
    }

    @Override
    public void givePermission(OfflinePlayer offlinePlayer, String permission) {
    }

    @Override
    public void givePermission(OfflinePlayer offlinePlayer, String permission, Long durationMs) {
    }

    @Override
    public void removePermission(OfflinePlayer offlinePlayer, String permission) {
    }

    @Override
    public void removePermission(OfflinePlayer offlinePlayer, String permission, Long durationMs) {
    }

    @Override
    public String getPrefix(Player player) {
        PlayerData playerData = this.aquaCoreApi.getPlayerData(player.getUniqueId());

        if(playerData == null) return null;

        return playerData.getHighestRank().getPrefix();
    }

    @Override
    public String getSuffix(Player player) {
        PlayerData playerData = this.aquaCoreApi.getPlayerData(player.getUniqueId());

        if(playerData == null) return null;

        return playerData.getHighestRank().getSuffix();
    }

    @Override
    public String getRankPrefix(Player player) {
        PlayerData playerData = this.aquaCoreApi.getPlayerData(player.getUniqueId());

        if(playerData == null) return null;

        return playerData.getHighestRank().getPrefix();
    }

}
