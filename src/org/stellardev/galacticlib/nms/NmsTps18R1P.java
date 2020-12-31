package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.particleeffect.ReflectionUtils;
import com.massivecraft.massivecore.util.ReflectionUtil;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NmsTps18R1P extends NmsTps {

    private static final NmsTps18R1P i = new NmsTps18R1P();
    public static NmsTps18R1P get() { return i; }

    // org.bukkit.craftbukkit.CraftServer
    protected Class<?> classCraftServer;
    // ...#getServer
    protected Method methodCraftServerGetServer;

    // net.minecraft.server.MinecraftServer
    protected Class<?> classMinecraftServer;
    // ...#recentTps
    protected Field fieldRecentTps;

    @Override
    public void setup() throws Throwable {
        this.classCraftServer = ReflectionUtils.PackageType.CRAFTBUKKIT.getClass("CraftServer");
        this.methodCraftServerGetServer = ReflectionUtil.getMethod(this.classCraftServer, "getServer");

        this.classMinecraftServer = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("MinecraftServer");
        this.fieldRecentTps = ReflectionUtil.getField(this.classMinecraftServer, "recentTps");
    }

    @Override
    public double getTps() {
        Object craftServer = this.classCraftServer.cast(Bukkit.getServer());
        Object nmsServer = ReflectionUtil.invokeMethod(this.methodCraftServerGetServer, craftServer);
        double[] recentTps = ReflectionUtil.getField(this.fieldRecentTps, nmsServer);

        return (recentTps[0] + recentTps[1] + recentTps[2]) / 3;
    }
}
