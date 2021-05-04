package org.stellardev.galacticlib.engine;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.stellardev.galacticlib.task.TaskTeleportTimer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EngineTeleport extends Engine {

    private static final EngineTeleport i = new EngineTeleport();
    public static EngineTeleport get() { return i; }

    private final Set<UUID> teleportBlacklistUuid = new HashSet<>();

    public void addUserToTeleportBlacklist(Player player) {
        this.teleportBlacklistUuid.add(player.getUniqueId());
    }

    public void removeUserFromTeleportBlacklist(Player player) {
        this.teleportBlacklistUuid.remove(player.getUniqueId());
    }

    public boolean isUserBlacklistedFromTeleport(Player player) {
        return this.teleportBlacklistUuid.contains(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();

        if(MUtil.isntPlayer(player)) return;
        if(isUserBlacklistedFromTeleport(player)) return;

        cancelTeleport(player);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onMove(PlayerMoveEvent event) {
        if(MUtil.isSameBlock(event)) return;

        Player player = event.getPlayer();

        cancelTeleport(player);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onDamage(EntityDamageEvent event) {
        if(MUtil.isntPlayer(event.getEntity())) return;

        Player player = (Player) event.getEntity();

        cancelTeleport(player);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if(MUtil.isntPlayer(player)) return;

        cancelTeleport(player);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(MUtil.isntPlayer(player)) return;

        cancelTeleport(player);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onInventoryOpen(InventoryOpenEvent event) {
        if(MUtil.isntPlayer(event.getPlayer())) return;

        Player player = (Player) event.getPlayer();

        cancelTeleport(player);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onInventoryClick(InventoryClickEvent event) {
        if(MUtil.isntPlayer(event.getWhoClicked())) return;

        Player player = (Player) event.getWhoClicked();

        cancelTeleport(player);
    }

    private void cancelTeleport(Player player) {
        TaskTeleportTimer.get().cancelTeleport(player.getUniqueId());
    }

}
