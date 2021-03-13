package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.mixin.Mixin;
import org.bukkit.inventory.meta.SkullMeta;
import org.stellardev.galacticlib.item.ContainerGameProfileProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class NmsSkullTexture extends Mixin {

    // -------------------------------------------- //
    // DEFAULT
    // -------------------------------------------- //

    private static final NmsSkullTexture d = new NmsSkullTexture().setAlternatives(
            NmsSkullTexture18R1P.class
    );

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static final NmsSkullTexture i = d;
    public static NmsSkullTexture get() { return i; }

    // -------------------------------------------- //
    // RAW
    // -------------------------------------------- //

    public void set(SkullMeta skullMeta, String name, UUID id, String texture) {
        throw notImplemented();
    }

    public void set(SkullMeta skullMeta, String name, UUID id) {
        throw notImplemented();
    }

    public String getTexture(SkullMeta skullMeta) {
        throw notImplemented();
    }

    // -------------------------------------------- //
    // GAME PROFILE
    // -------------------------------------------- //

    public <T> T createGameProfile(UUID id, String name)
    {
        return null;
    }

    public <T> T getGameProfile(SkullMeta meta)
    {
        return null;
    }

    public void setGameProfile(SkullMeta meta, Object gameProfile) {

    }

    public <T> T getPropertyMap(Object profile)
    {
        return null;
    }

    public Collection<Map.Entry<String, ContainerGameProfileProperty>> getGameProfileProperties(Object propertyMap)
    {
        return Collections.emptyList();
    }

    public void setGameProfileProperties(Object propertyMap, Collection<Map.Entry<String, ContainerGameProfileProperty>> properties)
    {
        // No-op here
    }

    public void setPropertyMap(Object profile, Object propertyMap)
    {
        // No-op hee
    }

    public Object createPropertyMap()
    {
        return null;
    }

    public UUID getGameProfileId(Object gameprofile)
    {
        throw notImplemented();
    }

    public String getGameProfileName(Object gameProfile)
    {
        throw notImplemented();
    }

}
