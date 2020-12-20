package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.particleeffect.ReflectionUtils;
import com.massivecraft.massivecore.util.ReflectionUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.stellardev.galacticlib.object.ArmorStandPacket;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

public class NmsArmorStandPacket18R1P extends NmsArmorStandPacket {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static final NmsArmorStandPacket18R1P i = new NmsArmorStandPacket18R1P();
    public static NmsArmorStandPacket18R1P get() { return i; }

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    // net.minecraft.server.World
    protected Class<?> classWorld;
    // ...#getWorld
    protected Method methodWorldGetWorld;

    // org.bukkit.craftbukkit.CraftWorld
    protected Class<?> classCraftWorld;
    // ...#getHandle
    protected Method methodCraftWorldGetHandle;

    // net.minecraft.server.WorldServer
    protected Class<?> classWorldServer;
    // net.minecraft.server.EntityLiving
    protected Class<?> classEntityLiving;
    // net.minecraft.server.Entity
    protected Class<?> classEntity;
    // net.minecraft.server.ItemStack
    protected Class<?> classItemStack;
    // net.minecraft.server.DataWatcher
    protected Class<?> classDataWatcher;

    // net.minecraft.server.EntityArmorStand
    protected Class<?> classEntityArmorStand;
    // ...#setLocation(double, double, double, float, float)
    protected Method methodEntityArmorStandSetLocation;
    // ...#getEquipment(int)
    protected Method methodEntityArmorStandGetEquipment;
    // ...#setEquipment(int, ItemStack)
    protected Method methodEntityArmorStandSetEquipment;
    // ...#getId
    protected Method methodEntityArmorStandGetId;
    // ...#hasBasePlate
    protected Method methodEntityArmorStandHasBasePlate;
    // ...#setBasePlate
    protected Method methodEntityArmorStandSetBasePlate;
    // ...#hasGravity
    protected Method methodEntityArmorStandHasGravity;
    // ...#setGravity
    protected Method methodEntityArmorStandSetGravity;
    // ...#isInvisible
    protected Method methodEntityArmorStandIsInvisible;
    // ...#setInvisible
    protected Method methodEntityArmorStandSetInvisible;
    // ...#hasArms
    protected Method methodEntityArmorStandHasArms;
    // ...#setArms
    protected Method methodEntityArmorStandSetArms;
    // ...#isSmall
    protected Method methodEntityArmorStandIsSmall;
    // ...#setSmall
    protected Method methodEntityArmorStandSetSmall;
    // ...#getWorld
    protected Method methodEntityArmorStandGetWorld;
    // ...#locX
    protected Field fieldEntityArmorStandLocX;
    // ...#locY
    protected Field fieldEntityArmorStandLocY;
    // ...#locZ
    protected Field fieldEntityArmorStandLocZ;
    // ...#yaw
    protected Field fieldEntityArmorStandYaw;
    // ...#pitch
    protected Field fieldEntityArmorStandPitch;
    // ...#die
    protected Method methodEntityArmorStandDie;
    // ...#isAlive
    protected Method methodEntityArmorStandIsAlive;
    // ...#getUniqueID
    protected Method methodEntityArmorStandGetUniqueId;
    // ...#getCustomName
    protected Method methodEntityArmorStandGetCustomName;
    // ...#setCustomName
    protected Method methodEntityArmorStandSetCustomName;
    // ...#getCustomNameVisible
    protected Method methodEntityArmorStandGetCustomNameVisible;
    // ...#setCustomNameVisible
    protected Method methodEntityArmorStandSetCustomNameVisible;
    // ...#getDataWatcher
    protected Method methodEntityArmorStandGetDataWatcher;
    // ...(WorldServer)
    protected Constructor<?> constructorEntityArmorStand;

    // net.minecraft.server.PacketPlayOutSpawnEntityLiving
    protected Class<?> classPacketPlayOutSpawnEntityLiving;
    // ...(EntityLiving)
    protected Constructor<?> constructorPacketPlayOutSpawnEntityLiving;

    // org.bukkit.craftbukkit.inventory.CraftItemStack
    protected Class<?> classCraftItemStack;
    // ...asBukkitCopy(ItemStack)
    protected Method methodCraftItemStackAsBukkitCopy;
    // ...asNMSCopy(ItemStack)
    protected Method methodCraftItemStackAsNmsCopy;
    // ...asCraftCopy(ItemStack)
    protected Method methodCraftItemStackAsCraftCopy;

    // net.minecraft.server.PacketPlayOutEntityEquipment
    protected Class<?> classPacketPlayOutEntityEquipment;
    // ...(int, int, ItemStack)
    protected Constructor<?> constructorPacketPlayOutEntityEquipment;

    // net.minecraft.server.PacketPlayOutEntityTeleport
    protected Class<?> classPacketPlayOutEntityTeleport;
    // ...(Entity)
    protected Constructor<?> constructorPacketPlayOutEntityTeleport;

    // net.minecraft.server.PacketPlayOutEntityDestroy
    protected Class<?> classPacketPlayOutEntityDestroy;
    // ...(int)
    protected Constructor<?> constructorPacketPlayOutEntityDestroy;

    // net.minecraft.server.PacketPlayOutEntityMetadata
    protected Class<?> classPacketPlayOutEntityMetadata;
    // ...(int, DataWatcher, boolean)
    protected Constructor<?> constructorPacketPlayOutEntityMetadata;

    // -------------------------------------------- //
    // SETUP
    // -------------------------------------------- //

    @Override
    public void setup() throws Throwable {
        this.classWorld = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("World");
        this.methodWorldGetWorld = ReflectionUtil.getMethod(this.classWorld, "getWorld");

        this.classCraftWorld = ReflectionUtils.PackageType.CRAFTBUKKIT.getClass("CraftWorld");
        this.methodCraftWorldGetHandle = ReflectionUtil.getMethod(this.classCraftWorld, "getHandle");

        this.classWorldServer = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("WorldServer");
        this.classItemStack = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("ItemStack");
        this.classEntityLiving = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EntityLiving");
        this.classEntity = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Entity");
        this.classDataWatcher = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("DataWatcher");

        this.classEntityArmorStand = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EntityArmorStand");
        this.methodEntityArmorStandSetLocation = ReflectionUtil.getMethod(this.classEntity, "setLocation", double.class, double.class, double.class, float.class, float.class);
        this.methodEntityArmorStandGetEquipment = ReflectionUtil.getMethod(this.classEntityArmorStand, "getEquipment", int.class);
        this.methodEntityArmorStandSetEquipment = ReflectionUtil.getMethod(this.classEntityArmorStand, "setEquipment", int.class, this.classItemStack);
        this.methodEntityArmorStandGetId = ReflectionUtil.getMethod(this.classEntity, "getId");
        this.methodEntityArmorStandHasBasePlate = ReflectionUtil.getMethod(this.classEntityArmorStand, "hasBasePlate");
        this.methodEntityArmorStandSetBasePlate = ReflectionUtil.getMethod(this.classEntityArmorStand, "setBasePlate", boolean.class);
        this.methodEntityArmorStandHasGravity = ReflectionUtil.getMethod(this.classEntityArmorStand, "hasGravity");
        this.methodEntityArmorStandSetGravity = ReflectionUtil.getMethod(this.classEntityArmorStand, "setGravity", boolean.class);
        this.methodEntityArmorStandIsInvisible = ReflectionUtil.getMethod(this.classEntity, "isInvisible");
        this.methodEntityArmorStandSetInvisible = ReflectionUtil.getMethod(this.classEntity, "setInvisible", boolean.class);
        this.methodEntityArmorStandHasArms = ReflectionUtil.getMethod(this.classEntityArmorStand, "hasArms");
        this.methodEntityArmorStandSetArms = ReflectionUtil.getMethod(this.classEntityArmorStand, "setArms", boolean.class);
        this.methodEntityArmorStandIsSmall = ReflectionUtil.getMethod(this.classEntityArmorStand, "isSmall");
        this.methodEntityArmorStandSetSmall = ReflectionUtil.getMethod(this.classEntityArmorStand, "setSmall", boolean.class);
        this.methodEntityArmorStandGetWorld = ReflectionUtil.getMethod(this.classEntity, "getWorld");
        this.fieldEntityArmorStandLocX = ReflectionUtil.getField(this.classEntity, "locX");
        this.fieldEntityArmorStandLocY = ReflectionUtil.getField(this.classEntity, "locY");
        this.fieldEntityArmorStandLocZ = ReflectionUtil.getField(this.classEntity, "locZ");
        this.fieldEntityArmorStandYaw = ReflectionUtil.getField(this.classEntity, "yaw");
        this.fieldEntityArmorStandPitch = ReflectionUtil.getField(this.classEntity, "pitch");
        this.methodEntityArmorStandDie = ReflectionUtil.getMethod(this.classEntity, "die");
        this.methodEntityArmorStandIsAlive = ReflectionUtil.getMethod(this.classEntityLiving, "isAlive");
        this.methodEntityArmorStandGetUniqueId = ReflectionUtil.getMethod(this.classEntity, "getUniqueID");
        this.methodEntityArmorStandGetCustomName = ReflectionUtil.getMethod(this.classEntity, "getCustomName");
        this.methodEntityArmorStandSetCustomName = ReflectionUtil.getMethod(this.classEntity, "setCustomName", String.class);
        this.methodEntityArmorStandGetCustomNameVisible = ReflectionUtil.getMethod(this.classEntity, "getCustomNameVisible");
        this.methodEntityArmorStandSetCustomNameVisible = ReflectionUtil.getMethod(this.classEntity, "setCustomNameVisible", boolean.class);
        this.methodEntityArmorStandGetDataWatcher = ReflectionUtil.getMethod(this.classEntity, "getDataWatcher");
        this.constructorEntityArmorStand = ReflectionUtil.getConstructor(this.classEntityArmorStand, this.classWorld);

        this.classPacketPlayOutSpawnEntityLiving = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutSpawnEntityLiving");
        this.constructorPacketPlayOutSpawnEntityLiving = ReflectionUtil.getConstructor(this.classPacketPlayOutSpawnEntityLiving, this.classEntityLiving);

        this.classCraftItemStack = ReflectionUtils.PackageType.CRAFTBUKKIT_INVENTORY.getClass("CraftItemStack");
        this.methodCraftItemStackAsBukkitCopy = ReflectionUtil.getMethod(this.classCraftItemStack, "asBukkitCopy", this.classItemStack);
        this.methodCraftItemStackAsNmsCopy = ReflectionUtil.getMethod(this.classCraftItemStack, "asNMSCopy", ItemStack.class);
        this.methodCraftItemStackAsCraftCopy = ReflectionUtil.getMethod(this.classCraftItemStack, "asCraftCopy", ItemStack.class);

        this.classPacketPlayOutEntityEquipment = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutEntityEquipment");
        this.constructorPacketPlayOutEntityEquipment = ReflectionUtil.getConstructor(this.classPacketPlayOutEntityEquipment, int.class, int.class, this.classItemStack);

        this.classPacketPlayOutEntityTeleport = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutEntityTeleport");
        this.constructorPacketPlayOutEntityTeleport = ReflectionUtil.getConstructor(this.classPacketPlayOutEntityTeleport, this.classEntity);

        this.classPacketPlayOutEntityDestroy = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutEntityDestroy");
        this.constructorPacketPlayOutEntityDestroy = ReflectionUtil.getConstructor(this.classPacketPlayOutEntityDestroy, int[].class);

        this.classPacketPlayOutEntityMetadata = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutEntityMetadata");
        this.constructorPacketPlayOutEntityMetadata = ReflectionUtil.getConstructor(this.classPacketPlayOutEntityMetadata, int.class, this.classDataWatcher, boolean.class);
    }

    // -------------------------------------------- //
    // RAW
    // -------------------------------------------- //

    protected Object getWorldServer(World world) {
        Object craftWorld = classCraftWorld.cast(world);

        return ReflectionUtil.invokeMethod(this.methodCraftWorldGetHandle, craftWorld);
    }

    @Override
    public ArmorStandPacket createArmorStand(Player player, Location location) {
        ArmorStandPacket armorStandPacket = new ArmorStandPacket18R1P(player);
        armorStandPacket.createStand(location);

        return armorStandPacket;
    }

    private class ArmorStandPacket18R1P implements ArmorStandPacket {

        private Object entityArmorStand;
        private final Player player;

        public ArmorStandPacket18R1P(Player player) {
            this.player = player;
        }

        @Override
        public void createStand(Location location) {
            Object worldServer = getWorldServer(location.getWorld());

            this.entityArmorStand = ReflectionUtil.invokeConstructor(constructorEntityArmorStand, worldServer);

            ReflectionUtil.invokeMethod(methodEntityArmorStandSetLocation, this.entityArmorStand, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }

        @Override
        public void spawnStand() {
            Object packetPlayOutSpawnEntityLiving = ReflectionUtil.invokeConstructor(constructorPacketPlayOutSpawnEntityLiving, this.entityArmorStand);
            NmsPacket.get().sendPacket(this.player, packetPlayOutSpawnEntityLiving);
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

        @Override
        public boolean hasBasePlate() {
            return ReflectionUtil.invokeMethod(methodEntityArmorStandHasBasePlate, this.entityArmorStand);
        }

        @Override
        public void setBasePlate(boolean basePlate) {
            ReflectionUtil.invokeMethod(methodEntityArmorStandSetBasePlate, this.entityArmorStand, basePlate);
        }

        @Override
        public boolean hasGravity() {
            return ReflectionUtil.invokeMethod(methodEntityArmorStandHasGravity, this.entityArmorStand);
        }

        @Override
        public void setGravity(boolean gravity) {
            ReflectionUtil.invokeMethod(methodEntityArmorStandSetGravity, this.entityArmorStand, gravity);
        }

        @Override
        public boolean isInvisible() {
            return ReflectionUtil.invokeMethod(methodEntityArmorStandIsInvisible, this.entityArmorStand);
        }

        @Override
        public void setInvisible(boolean visible) {
            ReflectionUtil.invokeMethod(methodEntityArmorStandSetInvisible, this.entityArmorStand, visible);
        }

        @Override
        public boolean hasArms() {
            return ReflectionUtil.invokeMethod(methodEntityArmorStandHasArms, this.entityArmorStand);
        }

        @Override
        public void setArms(boolean arms) {
            ReflectionUtil.invokeMethod(methodEntityArmorStandSetArms, this.entityArmorStand, arms);
        }

        @Override
        public boolean isSmall() {
            return ReflectionUtil.invokeMethod(methodEntityArmorStandIsSmall, this.entityArmorStand);
        }

        @Override
        public void setSmall(boolean small) {
            ReflectionUtil.invokeMethod(methodEntityArmorStandSetSmall, this.entityArmorStand, small);
        }

        @Override
        public Location getLocation() {
            Object world = ReflectionUtil.invokeMethod(methodEntityArmorStandGetWorld, this.entityArmorStand);
            Object craftWorld = ReflectionUtil.invokeMethod(methodWorldGetWorld, world);
            double x = ReflectionUtil.getField(fieldEntityArmorStandLocX, this.entityArmorStand);
            double y = ReflectionUtil.getField(fieldEntityArmorStandLocY, this.entityArmorStand);
            double z = ReflectionUtil.getField(fieldEntityArmorStandLocZ, this.entityArmorStand);
            float yaw = ReflectionUtil.getField(fieldEntityArmorStandYaw, this.entityArmorStand);
            float pitch = ReflectionUtil.getField(fieldEntityArmorStandPitch, this.entityArmorStand);

            return new Location((World) craftWorld, x, y, z, yaw, pitch);
        }

        @Override
        public void teleport(Location location) {
            ReflectionUtil.invokeMethod(methodEntityArmorStandSetLocation, this.entityArmorStand, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

            Object packet = ReflectionUtil.invokeConstructor(constructorPacketPlayOutEntityTeleport, this.entityArmorStand);
            NmsPacket.get().sendPacket(this.player, packet);
        }

        @Override
        public void remove() {
            Object packet = ReflectionUtil.invokeConstructor(constructorPacketPlayOutEntityDestroy, getId());
            NmsPacket.get().sendPacket(this.player, packet);

            ReflectionUtil.invokeMethod(methodEntityArmorStandDie, this.entityArmorStand);
        }

        @Override
        public boolean isDead() {
            return !(boolean)ReflectionUtil.invokeMethod(methodEntityArmorStandIsAlive, this.entityArmorStand);
        }

        @Override
        public UUID getUniqueId() {
            return ReflectionUtil.invokeMethod(methodEntityArmorStandGetUniqueId, this.entityArmorStand);
        }

        @Override
        public String getCustomName() {
            return ReflectionUtil.invokeMethod(methodEntityArmorStandGetCustomName, this.entityArmorStand);
        }

        @Override
        public void setCustomName(String input) {
            ReflectionUtil.invokeMethod(methodEntityArmorStandSetCustomName, this.entityArmorStand, input);

            Object dataWatcher = ReflectionUtil.invokeMethod(methodEntityArmorStandGetDataWatcher, this.entityArmorStand);
            Object packet = ReflectionUtil.invokeConstructor(constructorPacketPlayOutEntityMetadata, getId(), dataWatcher, false);

            NmsPacket.get().sendPacket(this.player, packet);
        }

        @Override
        public void setCustomNameVisible(boolean customNameVisible) {
            ReflectionUtil.invokeMethod(methodEntityArmorStandSetCustomNameVisible, this.entityArmorStand, customNameVisible);

            Object dataWatcher = ReflectionUtil.invokeMethod(methodEntityArmorStandGetDataWatcher, this.entityArmorStand);
            Object packet = ReflectionUtil.invokeConstructor(constructorPacketPlayOutEntityMetadata, getId(), dataWatcher, false);

            NmsPacket.get().sendPacket(this.player, packet);
        }

        @Override
        public boolean isCustomNameVisible() {
            return ReflectionUtil.invokeMethod(methodEntityArmorStandGetCustomNameVisible, this.entityArmorStand);
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
            NmsPacket.get().sendPacket(this.player, packet);
        }

        private int getId() {
            return ReflectionUtil.invokeMethod(methodEntityArmorStandGetId, this.entityArmorStand);
        }
    }
}
