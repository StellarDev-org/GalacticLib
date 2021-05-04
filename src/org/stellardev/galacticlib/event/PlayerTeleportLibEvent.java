package org.stellardev.galacticlib.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerTeleportLibEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Location to, from;

    public PlayerTeleportLibEvent(Player who, Location to) {
        super(who);

        this.to = to.clone();
        this.from = who.getLocation().clone();
    }

    public PlayerTeleportLibEvent(Player who, Player target) {
        super(who);

        this.to = target.getLocation().clone();
        this.from = who.getLocation().clone();
    }

    public Location getFrom() {
        return this.from;
    }

    public Location getTo() {
        return this.to;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
