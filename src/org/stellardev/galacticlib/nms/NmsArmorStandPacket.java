package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.mixin.Mixin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.stellardev.galacticlib.object.ArmorStandPacket;

public class NmsArmorStandPacket extends Mixin {

    // -------------------------------------------- //
    // DEFAULT
    // -------------------------------------------- //

    private static final NmsArmorStandPacket d = new NmsArmorStandPacket().setAlternatives(
            NmsArmorStandPacket18R1P.class
    );

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    public static final NmsArmorStandPacket i = d;
    public static NmsArmorStandPacket get() { return i; }

    // -------------------------------------------- //
    // RAW
    // -------------------------------------------- //

    public ArmorStandPacket createArmorStand(Player player, Location location) {
        throw notImplemented();
    }

}
