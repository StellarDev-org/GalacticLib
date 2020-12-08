package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.mixin.Mixin;
import org.bukkit.entity.Player;

public class NmsPacket extends Mixin {

    // -------------------------------------------- //
    // DEFAULT
    // -------------------------------------------- //

    private static final NmsPacket d = new NmsPacket().setAlternatives(
            NmsPacket18R1P.class
    );

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    public static final NmsPacket i = d;
    public static NmsPacket get() { return i; }

    // -------------------------------------------- //
    // RAW
    // -------------------------------------------- //

    public void sendPacket(Player player, Object packet) {
        throw notImplemented();
    }

}
