package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.nms.NmsBasics;
import com.massivecraft.massivecore.particleeffect.ReflectionUtils;
import com.massivecraft.massivecore.util.ReflectionUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.stellardev.galacticlib.object.ArmorStandPacket;

import java.lang.reflect.Method;

public class NmsArmorStandPacket112R1P extends NmsArmorStandPacket {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static final NmsArmorStandPacket112R1P i = new NmsArmorStandPacket112R1P();
    public static NmsArmorStandPacket112R1P get() { return i; }

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    // net.minecraft.server.EnumItemSlot
    protected Class<?> classEnumItemSlot;
    // ...#a(String)
    protected Method methodEnumItemSlotFromName;

    // -------------------------------------------- //
    // SETUP
    // -------------------------------------------- //

    @Override
    public void setup() throws Throwable {
        setupCommon();

        this.classEnumItemSlot = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EnumItemSlot");
        this.methodEnumItemSlotFromName = ReflectionUtils.getMethod(this.classEnumItemSlot, "a", String.class);

        this.methodEntityArmorStandGetEquipment = ReflectionUtil.getMethod(this.classEntityArmorStand, "getEquipment", this.classEnumItemSlot);
        this.methodEntityArmorStandSetEquipment = ReflectionUtil.getMethod(this.classEntityArmorStand, "setSlot", this.classEnumItemSlot, this.classItemStack);
        this.methodEntityArmorStandHasGravity = ReflectionUtil.getMethod(this.classEntity, "isNoGravity");
        this.methodEntityArmorStandSetGravity = ReflectionUtil.getMethod(this.classEntity, "setNoGravity", boolean.class);

        this.constructorPacketPlayOutEntityEquipment = ReflectionUtil.getConstructor(this.classPacketPlayOutEntityEquipment, int.class, classEnumItemSlot, this.classItemStack);
    }

    @Override
    public ArmorStandPacket createArmorStand(Player player, Location location) {
        ArmorStandPacket armorStandPacket = new ArmorStandPacket112R1P(this, player);
        armorStandPacket.createStand(location);

        return armorStandPacket;
    }

    private class ArmorStandPacket112R1P extends ArmorStandPacketCommon {

        public ArmorStandPacket112R1P(NmsArmorStandPacket nmsArmorStandPacket, Player player) {
            super(nmsArmorStandPacket, player);
        }

        @Override
        public ItemStack getItemInHand() {
            return getEquipment("mainhand");
        }

        @Override
        public void setItemInHand(ItemStack var1) {
            setEquipment(var1, "mainhand");
        }

        @Override
        public ItemStack getBoots() {
            return getEquipment("feet");
        }

        @Override
        public void setBoots(ItemStack var1) {
            setEquipment(var1, "feet");
        }

        @Override
        public ItemStack getLeggings() {
            return getEquipment("legs");
        }

        @Override
        public void setLeggings(ItemStack var1) {
            setEquipment(var1, "legs");
        }

        @Override
        public ItemStack getChestplate() {
            return getEquipment("chest");
        }

        @Override
        public void setChestplate(ItemStack var1) {
            setEquipment(var1, "chest");
        }

        @Override
        public ItemStack getHelmet() {
            return getEquipment("head");
        }

        @Override
        public void setHelmet(ItemStack var1) {
            setEquipment(var1, "head");
        }

        private ItemStack getEquipment(String slotType) {
            Object slot = ReflectionUtil.invokeMethod(methodEnumItemSlotFromName, null, slotType);
            Object itemStack = ReflectionUtil.invokeMethod(methodEntityArmorStandGetEquipment, this.entityArmorStand, slot);

            return ReflectionUtil.invokeMethod(methodCraftItemStackAsBukkitCopy, null, itemStack);
        }

        private void setEquipment(ItemStack itemStack, String slotType) {
            Object craftItemStack = ReflectionUtil.invokeMethod(methodCraftItemStackAsCraftCopy, null, itemStack);
            Object nmsItemStack = ReflectionUtil.invokeMethod(methodCraftItemStackAsNmsCopy, null, craftItemStack);
    
            Object slot = ReflectionUtil.invokeMethod(methodEnumItemSlotFromName, null, slotType);
            ReflectionUtil.invokeMethod(methodEntityArmorStandSetEquipment, this.entityArmorStand, slot, nmsItemStack);

            Object packet = ReflectionUtil.invokeConstructor(constructorPacketPlayOutEntityEquipment, getId(), slot, nmsItemStack);
            NmsBasics.get().sendPacket(this.player, packet);
        }
    }
}
