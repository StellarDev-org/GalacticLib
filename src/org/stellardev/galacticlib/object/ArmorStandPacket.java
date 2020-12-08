package org.stellardev.galacticlib.object;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface ArmorStandPacket {

    void createStand(Location location);

    void spawnStand();

    ItemStack getItemInHand();

    void setItemInHand(ItemStack var1);

    ItemStack getBoots();

    void setBoots(ItemStack var1);

    ItemStack getLeggings();

    void setLeggings(ItemStack var1);

    ItemStack getChestplate();

    void setChestplate(ItemStack var1);

    ItemStack getHelmet();

    void setHelmet(ItemStack var1);

    boolean hasBasePlate();

    void setBasePlate(boolean basePlate);

    boolean hasGravity();

    void setGravity(boolean gravity);

    boolean isInvisible();

    void setInvisible(boolean visible);

    boolean hasArms();

    void setArms(boolean arms);

    boolean isSmall();

    void setSmall(boolean small);

    Location getLocation();

    void teleport(Location var1);

    void remove();

    boolean isDead();

    UUID getUniqueId();

    String getCustomName();

    void setCustomName(String input);

    void setCustomNameVisible(boolean customNameVisible);

    boolean isCustomNameVisible();

}
