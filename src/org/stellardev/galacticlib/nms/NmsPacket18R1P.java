package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.particleeffect.ReflectionUtils;
import com.massivecraft.massivecore.util.ReflectionUtil;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NmsPacket18R1P extends NmsPacket {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static final NmsPacket18R1P i = new NmsPacket18R1P();
    public static NmsPacket18R1P get() { return i; }

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    // net.minecraft.server.Packet
    protected Class<?> classPacket;

    // org.bukkit.craftbukkit.entity.CraftPlayer
    protected Class<?> classCraftPlayer;
    // ...getHandle
    protected Method methodCraftPlayerGetHandle;

    // net.minecraft.server.EntityPlayer
    protected Class<?> classEntityPlayer;
    // ...playerConnection
    protected Field fieldEntityPlayerPlayerConnection;

    // net.minecraft.server.PlayerConnection
    protected Class<?> classPlayerConnection;
    // ...sendPacket(Packet)
    protected Method methodPlayerConnectionSendPacket;

    // -------------------------------------------- //
    // SETUP
    // -------------------------------------------- //

    @Override
    public void setup() throws Throwable {
        this.classPacket = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Packet");

        this.classCraftPlayer = ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY.getClass("CraftPlayer");
        this.methodCraftPlayerGetHandle = ReflectionUtil.getMethod(this.classCraftPlayer, "getHandle");

        this.classEntityPlayer = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EntityPlayer");
        this.fieldEntityPlayerPlayerConnection = ReflectionUtil.getField(this.classEntityPlayer, "playerConnection");

        this.classPlayerConnection = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("PlayerConnection");
        this.methodPlayerConnectionSendPacket = ReflectionUtil.getMethod(this.classPlayerConnection, "sendPacket", this.classPacket);
    }

    // -------------------------------------------- //
    // RAW
    // -------------------------------------------- //

    @Override
    public void sendPacket(Player player, Object packet) {
        if(player == null || !player.isOnline()) return;

        Object craftPlayer = this.classCraftPlayer.cast(player);
        Object entityPlayer = ReflectionUtil.invokeMethod(this.methodCraftPlayerGetHandle, craftPlayer);
        Object playerConnection = ReflectionUtil.getField(this.fieldEntityPlayerPlayerConnection, entityPlayer);

        ReflectionUtil.invokeMethod(this.methodPlayerConnectionSendPacket, playerConnection, packet);
    }
}
