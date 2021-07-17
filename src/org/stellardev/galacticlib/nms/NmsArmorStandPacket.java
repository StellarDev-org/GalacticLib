package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.mixin.Mixin;
import com.massivecraft.massivecore.particleeffect.ReflectionUtils;
import com.massivecraft.massivecore.util.ReflectionUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.stellardev.galacticlib.object.ArmorStandPacket;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NmsArmorStandPacket extends Mixin {

    // -------------------------------------------- //
    // DEFAULT
    // -------------------------------------------- //

    private static final NmsArmorStandPacket d = new NmsArmorStandPacket().setAlternatives(
            NmsArmorStandPacket112R1P.class,
            NmsArmorStandPacket18R1P.class
    );

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    public static final NmsArmorStandPacket i = d;
    public static NmsArmorStandPacket get() { return i; }

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
    // SETUP COMMON
    // -------------------------------------------- //

    protected void setupCommon() throws Throwable {
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
        this.methodEntityArmorStandGetId = ReflectionUtil.getMethod(this.classEntity, "getId");
        this.methodEntityArmorStandHasBasePlate = ReflectionUtil.getMethod(this.classEntityArmorStand, "hasBasePlate");
        this.methodEntityArmorStandSetBasePlate = ReflectionUtil.getMethod(this.classEntityArmorStand, "setBasePlate", boolean.class);
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

    public ArmorStandPacket createArmorStand(Player player, Location location) {
        throw notImplemented();
    }

}
