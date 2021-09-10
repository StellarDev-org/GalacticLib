package org.stellardev.galacticlib.handler.fallback;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.GalacticLib;
import org.stellardev.galacticlib.handler.IPermissionHandler;

import java.util.UUID;

public class FallbackPermissionHandler implements IPermissionHandler {

    @Override
    public int getPermissionPoolTop(UUID uuid, String permissionBase, int cap) {
        Player player = Bukkit.getPlayer(uuid);

        if(player == null) return -1;

        for(int i = cap; i >= 0; i--) {
            if(player.hasPermission(permissionBase + i)) return i;
        }

        return -1;
    }

    @Override
    public boolean hasPermission(OfflinePlayer offlinePlayer, String permission) {
        if(!offlinePlayer.isOnline()) return false;

        Player player = offlinePlayer.getPlayer();

        return player.hasPermission(permission);
    }

    @Override
    public void givePermission(OfflinePlayer offlinePlayer, String permission) {
        if(!offlinePlayer.isOnline()) return;

        Player player = offlinePlayer.getPlayer();

        player.addAttachment(GalacticLib.get(), permission, true);
    }

    @Override
    public void givePermission(OfflinePlayer offlinePlayer, String permission, Long durationMs) {
        if(!offlinePlayer.isOnline()) return;

        Player player = offlinePlayer.getPlayer();

        player.addAttachment(GalacticLib.get(), permission, true, (int) Math.min(durationMs / 20, Integer.MAX_VALUE));
    }

    @Override
    public void removePermission(OfflinePlayer offlinePlayer, String permission) {
        if(!offlinePlayer.isOnline()) return;

        Player player = offlinePlayer.getPlayer();

        player.addAttachment(GalacticLib.get(), permission, false);
    }

    @Override
    public void removePermission(OfflinePlayer offlinePlayer, String permission, Long durationMs) {
        if(!offlinePlayer.isOnline()) return;

        Player player = offlinePlayer.getPlayer();

        player.addAttachment(GalacticLib.get(), permission, false, (int) Math.min(durationMs / 20, Integer.MAX_VALUE));
    }

    @Override
    public String getRankPrefix(Player player) {
        return null;
    }

    @Override
    public String getPrefix(Player player) {
        return null;
    }

    @Override
    public String getSuffix(Player player) {
        return null;
    }

}
