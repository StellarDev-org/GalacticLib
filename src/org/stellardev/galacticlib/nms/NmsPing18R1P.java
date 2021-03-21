package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.particleeffect.ReflectionUtils;
import com.massivecraft.massivecore.util.ReflectionUtil;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NmsPing18R1P extends NmsPing {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static final NmsPing18R1P i = new NmsPing18R1P();
    public static NmsPing18R1P get() { return i; }

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    // org.bukkit.craftbukkit.entity.CraftPlayer
    protected Class<?> classCraftPlayer;
    // ...#getHandle
    protected Method methodCraftPlayerGetHandle;

    // net.minecraft.server.EntityPlayer
    protected Class<?> classEntityPlayer;
    // ...#ping
    protected Field fieldEntityPlayerPing;

    // -------------------------------------------- //
    // SETUP
    // -------------------------------------------- //

    @Override
    public void setup() throws Throwable {
        this.classCraftPlayer = ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY.getClass("CraftPlayer");
        this.methodCraftPlayerGetHandle = ReflectionUtil.getMethod(this.classCraftPlayer, "getHandle");

        this.classEntityPlayer = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EntityPlayer");
        this.fieldEntityPlayerPing = ReflectionUtil.getField(this.classEntityPlayer, "ping");
    }

    // -------------------------------------------- //
    // RAW
    // -------------------------------------------- //

    @Override
    public int getPing(Player player) {
        Object craftPlayer = this.classCraftPlayer.cast(player);
        Object entityPlayer = ReflectionUtil.invokeMethod(this.methodCraftPlayerGetHandle, craftPlayer);

        return ReflectionUtil.getField(this.fieldEntityPlayerPing, entityPlayer);
    }
}
