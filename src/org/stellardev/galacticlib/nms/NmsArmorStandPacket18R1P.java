package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.nms.NmsBasics;
import com.massivecraft.massivecore.util.ReflectionUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.stellardev.galacticlib.object.ArmorStandPacket;

public class NmsArmorStandPacket18R1P extends NmsArmorStandPacket {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static final NmsArmorStandPacket18R1P i = new NmsArmorStandPacket18R1P();
    public static NmsArmorStandPacket18R1P get() { return i; }

    // -------------------------------------------- //
    // SETUP
    // -------------------------------------------- //

    @Override
    public void setup() throws Throwable {
        setupCommon();

        this.methodEntityArmorStandGetEquipment = ReflectionUtil.getMethod(this.classEntityArmorStand, "getEquipment", int.class);
        this.methodEntityArmorStandSetEquipment = ReflectionUtil.getMethod(this.classEntityArmorStand, "setEquipment", int.class, this.classItemStack);
        this.methodEntityArmorStandHasGravity = ReflectionUtil.getMethod(this.classEntityArmorStand, "hasGravity");
        this.methodEntityArmorStandSetGravity = ReflectionUtil.getMethod(this.classEntityArmorStand, "setGravity", boolean.class);

        this.constructorPacketPlayOutEntityEquipment = ReflectionUtil.getConstructor(this.classPacketPlayOutEntityEquipment, int.class, int.class, this.classItemStack);
    }

    @Override
    public ArmorStandPacket createArmorStand(Player player, Location location) {
        ArmorStandPacket armorStandPacket = new ArmorStandPacket18R1P(this, player);
        armorStandPacket.createStand(location);

        return armorStandPacket;
    }

    private class ArmorStandPacket18R1P extends ArmorStandPacketCommon {

        public ArmorStandPacket18R1P(NmsArmorStandPacket nmsArmorStandPacket, Player player) {
            super(nmsArmorStandPacket, player);
        }

        @Override
        public ItemStack getItemInHand() {
            return getEquipment(0);
        }

        @Override
        public void setItemInHand(ItemStack var1) {
            setEquipment(var1, 0);
        }

        @Override
        public ItemStack getBoots() {
            return getEquipment(1);
        }

        @Override
        public void setBoots(ItemStack var1) {
            setEquipment(var1, 1);
        }

        @Override
        public ItemStack getLeggings() {
            return getEquipment(2);
        }

        @Override
        public void setLeggings(ItemStack var1) {
            setEquipment(var1, 2);
        }

        @Override
        public ItemStack getChestplate() {
            return getEquipment(3);
        }

        @Override
        public void setChestplate(ItemStack var1) {
            setEquipment(var1, 3);
        }

        @Override
        public ItemStack getHelmet() {
            return getEquipment(4);
        }

        @Override
        public void setHelmet(ItemStack var1) {
            setEquipment(var1, 4);
        }

        private ItemStack getEquipment(int slot) {
            Object itemStack = ReflectionUtil.invokeMethod(methodEntityArmorStandGetEquipment, this.entityArmorStand, slot);

            return ReflectionUtil.invokeMethod(methodCraftItemStackAsBukkitCopy, null, itemStack);
        }

        private void setEquipment(ItemStack itemStack, int slot) {
            Object craftItemStack = ReflectionUtil.invokeMethod(methodCraftItemStackAsCraftCopy, null, itemStack);
            Object nmsItemStack = ReflectionUtil.invokeMethod(methodCraftItemStackAsNmsCopy, null, craftItemStack);

            ReflectionUtil.invokeMethod(methodEntityArmorStandSetEquipment, this.entityArmorStand, slot, nmsItemStack);

            Object packet = ReflectionUtil.invokeConstructor(constructorPacketPlayOutEntityEquipment, getId(), slot, nmsItemStack);
            NmsBasics.get().sendPacket(this.player, packet);
        }
    }
}
