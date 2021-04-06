package org.stellardev.galacticlib.integration.luckperms;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.Integration;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class IntegrationLuckPerms extends Integration {

    private static final IntegrationLuckPerms instance = new IntegrationLuckPerms();
    public static IntegrationLuckPerms get() { return instance; }

    private IntegrationLuckPerms() {
        this.setPluginName("LuckPerms");
    }

    @Override
    public Engine getEngine() {
        return EngineLuckPerms.get();
    }

    public int getPermissionPoolTop(UUID uuid, String permissionBase, int cap) {
        return EngineLuckPerms.get().getPermissionPoolTop(uuid, permissionBase, cap);
    }

    public boolean hasPermission(OfflinePlayer offlinePlayer, String permission) {
        return EngineLuckPerms.get().hasPermission(offlinePlayer, permission);
    }

    public void givePermission(OfflinePlayer offlinePlayer, String permission) {
        EngineLuckPerms.get().givePermission(offlinePlayer, permission, null);
    }

    public void givePermission(OfflinePlayer offlinePlayer, String permission, Long durationMs) {
        EngineLuckPerms.get().givePermission(offlinePlayer, permission, durationMs);
    }

    public void removePermission(OfflinePlayer offlinePlayer, String permission) {
        EngineLuckPerms.get().removePermission(offlinePlayer, permission, null);
    }

    public void removePermission(OfflinePlayer offlinePlayer, String permission, Long durationMs) {
        EngineLuckPerms.get().removePermission(offlinePlayer, permission, durationMs);
    }

    public String getRankPrefix(Player player) {
        return EngineLuckPerms.get().getRankPrefix(player);
    }

    public String getPrefix(Player player) {
        return EngineLuckPerms.get().getPrefix(player);
    }

    public String getSuffix(Player player) {
        return EngineLuckPerms.get().getSuffix(player);
    }
}
