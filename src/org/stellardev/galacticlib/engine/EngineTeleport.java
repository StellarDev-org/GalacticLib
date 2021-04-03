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

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onTeleport(PlayerTeleportEvent event) {
        if(MUtil.isntPlayer(event.getPlayer())) return;
        if(this.teleportBlacklistUuid.contains(event.getPlayer().getUniqueId())) return;

        cancelTeleport(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onMove(PlayerMoveEvent event) {
        if(MUtil.isSameBlock(event)) return;

        cancelTeleport(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onDamage(EntityDamageEvent event) {
        if(MUtil.isntPlayer(event.getEntity())) return;

        cancelTeleport((Player) event.getEntity());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onDeath(PlayerDeathEvent event) {
        if(MUtil.isntPlayer(event.getEntity())) return;

        cancelTeleport(event.getEntity());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onLeave(PlayerQuitEvent event) {
        if(MUtil.isntPlayer(event.getPlayer())) return;

        cancelTeleport(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onInventoryOpen(InventoryOpenEvent event) {
        if(MUtil.isntPlayer(event.getPlayer())) return;

        cancelTeleport((Player) event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    protected void onInventoryClick(InventoryClickEvent event) {
        if(MUtil.isntPlayer(event.getWhoClicked())) return;

        cancelTeleport((Player) event.getWhoClicked());
    }

    private void cancelTeleport(Player player) {
        TaskTeleportTimer.get().cancelTeleport(player.getUniqueId());
    }

}
