package org.stellardev.galacticlib.handler;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IPermissionHandler {

    int getPermissionPoolTop(UUID uuid, String permissionBase, int cap);

    boolean hasPermission(OfflinePlayer offlinePlayer, String permission);

    void givePermission(OfflinePlayer offlinePlayer, String permission);
    
    void givePermission(OfflinePlayer offlinePlayer, String permission, Long durationMs);

    void removePermission(OfflinePlayer offlinePlayer, String permission);

    void removePermission(OfflinePlayer offlinePlayer, String permission, Long durationMs);

    String getRankPrefix(Player player);

    String getPrefix(Player player);

    String getSuffix(Player player);

}
