package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.particleeffect.ReflectionUtils;
import com.massivecraft.massivecore.util.ReflectionUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NmsWorldBorder18R1P extends NmsWorldBorder {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static final NmsWorldBorder18R1P i = new NmsWorldBorder18R1P();
    public static NmsWorldBorder18R1P get() { return i; }

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    // org.bukkit.craftbukkit.CraftWorld
    private Class<?> classCraftWorld;
    // ...#getHandle
    private Method methodCraftWorldGetHandle;

    // net.minecraft.server.WorldBorder
    private Class<?> classWorldBorder;
    // ...#setCenter
    private Method methodWorldBorderSetCenter;
    // ...#setSize
    private Method methodWorldBorderSetSize;
    // ...#setWarningDistance
    private Method methodWorldBorderSetWarningDistance;
    // ...#world
    private Field fieldWorldBorderWorld;
    // ...();
    private Constructor<?> constructorWorldBorder;

    // org.bukkit.craftbukkit.entity.CraftPlayer
    private Class<?> classCraftPlayer;
    // ...#getHandle
    private Method methodCraftPlayerGetHandle;

    // net.minecraft.server.EntityPlayer
    private Class<?> classEntityPlayer;
    // ...#playerConnection
    private Field fieldEntityPlayerPlayerConnection;

    // net.minecraft.server.Packet
    private Class<?> classPacket;

    // net.minecraft.server.PlayerConnection
    private Class<?> classPlayerConnection;
    // ...#sendPacket
    private Method methodPlayerConnectionSendPacket;

    // net.minecraft.server.PacketPlayOutWorldBorder
    private Class<?> classPacketPlayOutWorldBorder;
    // ...#(WorldBorder, EnumWorldBorderAction)
    private Constructor<?> constructorPacketPlayOutWorldBorder;

    // net.minecraft.server.PacketPlayOutWorldBorder.EnumWorldBorderAction
    private Class<?> classEnumWorldBorderAction;
    // ...#SET_SIZE
    private Field fieldEnumWorldBorderActionSetSize;
    // ...#SET_CENTER
    private Field fieldEnumWorldBorderActionSetCenter;
    // ...#SET_WARNING_BLOCKS
    private Field fieldEnumWorldBorderActionSetWarningBlocks;

    // -------------------------------------------- //
    // SETUP
    // -------------------------------------------- //

    @Override
    public void setup() throws Throwable {
        try {
            this.classCraftWorld = ReflectionUtils.PackageType.CRAFTBUKKIT.getClass("CraftWorld");
            this.methodCraftWorldGetHandle = ReflectionUtil.getMethod(this.classCraftWorld, "getHandle");

            this.classWorldBorder = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("WorldBorder");
            this.methodWorldBorderSetCenter = ReflectionUtil.getMethod(this.classWorldBorder, "setCenter", double.class, double.class);
            this.methodWorldBorderSetSize = ReflectionUtil.getMethod(this.classWorldBorder, "setSize", double.class);
            this.methodWorldBorderSetWarningDistance = ReflectionUtil.getMethod(this.classWorldBorder, "setWarningDistance", int.class);
            this.fieldWorldBorderWorld = ReflectionUtil.getField(this.classWorldBorder, "world");
            this.constructorWorldBorder = ReflectionUtil.getConstructor(this.classWorldBorder);

            this.classCraftPlayer = ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY.getClass("CraftPlayer");
            this.methodCraftPlayerGetHandle = ReflectionUtil.getMethod(this.classCraftPlayer, "getHandle");

            this.classEntityPlayer = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EntityPlayer");
            this.fieldEntityPlayerPlayerConnection = ReflectionUtil.getField(this.classEntityPlayer, "playerConnection");

            this.classPacket = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Packet");

            this.classPlayerConnection = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PlayerConnection");
            this.methodPlayerConnectionSendPacket = ReflectionUtil.getMethod(this.classPlayerConnection, "sendPacket", this.classPacket);

            this.classPacketPlayOutWorldBorder = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutWorldBorder");

            this.classEnumWorldBorderAction = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PacketPlayOutWorldBorder$EnumWorldBorderAction");

            this.fieldEnumWorldBorderActionSetSize = ReflectionUtil.getField(this.classEnumWorldBorderAction, "SET_SIZE");
            this.fieldEnumWorldBorderActionSetCenter = ReflectionUtil.getField(this.classEnumWorldBorderAction, "SET_CENTER");
            this.fieldEnumWorldBorderActionSetWarningBlocks = ReflectionUtil.getField(this.classEnumWorldBorderAction, "SET_WARNING_BLOCKS");

            this.constructorPacketPlayOutWorldBorder = ReflectionUtil.getConstructor(this.classPacketPlayOutWorldBorder, this.classWorldBorder, this.classEnumWorldBorderAction);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    // -------------------------------------------- //
    // RAW
    // -------------------------------------------- //

    @Override
    public void sendBorder(Player player, Location location, double radius) {
        Object worldBorder = ReflectionUtil.invokeConstructor(this.constructorWorldBorder);
        Object craftWorld = this.classCraftWorld.cast(location.getWorld());
        Object worldServer = ReflectionUtil.invokeMethod(this.methodCraftWorldGetHandle, craftWorld);

        try {
            this.fieldWorldBorderWorld.set(worldBorder, worldServer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ReflectionUtil.invokeMethod(this.methodWorldBorderSetCenter, worldBorder, location.getX(), location.getZ());
        ReflectionUtil.invokeMethod(this.methodWorldBorderSetSize, worldBorder, radius);
        ReflectionUtil.invokeMethod(this.methodWorldBorderSetWarningDistance, worldBorder, 0);

        Object craftPlayer = this.classCraftPlayer.cast(player);
        Object entityPlayer = ReflectionUtil.invokeMethod(this.methodCraftPlayerGetHandle, craftPlayer);
        Object playerConnection = ReflectionUtil.getField(this.fieldEntityPlayerPlayerConnection, entityPlayer);

        try {
            Object setSize = this.fieldEnumWorldBorderActionSetSize.get(null);
            Object setCenter = this.fieldEnumWorldBorderActionSetCenter.get(null);
            Object setWarningBlocks = this.fieldEnumWorldBorderActionSetWarningBlocks.get(null);

            Object borderPacketSetSize = ReflectionUtil.invokeConstructor(this.constructorPacketPlayOutWorldBorder, worldBorder, setSize);
            Object borderPacketSetCenter = ReflectionUtil.invokeConstructor(this.constructorPacketPlayOutWorldBorder, worldBorder, setCenter);
            Object borderPacketSetWarningBlocks = ReflectionUtil.invokeConstructor(this.constructorPacketPlayOutWorldBorder, worldBorder, setWarningBlocks);

            ReflectionUtil.invokeMethod(this.methodPlayerConnectionSendPacket, playerConnection, borderPacketSetSize);
            ReflectionUtil.invokeMethod(this.methodPlayerConnectionSendPacket, playerConnection, borderPacketSetCenter);
            ReflectionUtil.invokeMethod(this.methodPlayerConnectionSendPacket, playerConnection, borderPacketSetWarningBlocks);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
