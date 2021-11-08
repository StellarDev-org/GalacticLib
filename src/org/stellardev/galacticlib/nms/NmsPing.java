package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.mixin.Mixin;
import org.bukkit.entity.Player;

public class NmsPing extends Mixin {

    // -------------------------------------------- //
    // DEFAULT
    // -------------------------------------------- //

    private static final NmsPing d = new NmsPing().setAlternatives(
            NmsPing18R1P.class
    );

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static NmsPing i = d;
    public static NmsPing get() { return i; }

    // -------------------------------------------- //
    // RAW
    // -------------------------------------------- //

    public int getPing(Player player) {
        throw notImplemented();
    }

}
