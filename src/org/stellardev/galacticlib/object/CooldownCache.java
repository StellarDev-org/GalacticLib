package org.stellardev.galacticlib.object;

import com.massivecraft.massivecore.mixin.MixinMessage;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.entity.Conf;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownCache {

    private final Map<UUID, Long> cooldownMap = new HashMap<>();

    private boolean sendMessage = true;

    public CooldownCache() {}

    public CooldownCache(boolean sendMessage) {
        this.sendMessage = sendMessage;
    }

    public String getRemainingTime(Player player) {
        UUID uuid = player.getUniqueId();

        if(!this.cooldownMap.containsKey(uuid)) return "0 seconds";

        long expireMs = this.cooldownMap.get(uuid);

        return Txt.getTimeDeltaDescriptionRelNow(expireMs);
    }

    public boolean isOnCooldown(Player player) {
        UUID uuid = player.getUniqueId();

        if(!this.cooldownMap.containsKey(uuid)) return false;

        long expireMs = this.cooldownMap.get(uuid);
        long currentMs = System.currentTimeMillis();

        if(currentMs > expireMs) {
            this.cooldownMap.remove(uuid);
            return true;
        } else {
            if(this.sendMessage) {
                MixinMessage.get().msgOne(player, getCooldownMessage(), Txt.getTimeDeltaDescriptionRelNow(expireMs));
            }

            return false;
        }
    }

    public void setCooldown(Player player, long delayMs) {
        if(delayMs <= 0) return;

        this.cooldownMap.put(player.getUniqueId(), (System.currentTimeMillis() + delayMs));
    }

    public void removeCooldown(Player player) {
        this.cooldownMap.remove(player.getUniqueId());
    }

    public String getCooldownMessage() {
        return Conf.get().msgCooldown;
    }
}
