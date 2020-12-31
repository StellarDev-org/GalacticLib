package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.mixin.Mixin;

public class NmsTps extends Mixin {

    // -------------------------------------------- //
    // DEFAULT
    // -------------------------------------------- //

    private static final NmsTps d = new NmsTps().setAlternatives(
            NmsTps18R1P.class
    );

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static final NmsTps i = d;
    public static NmsTps get() { return i; }

    // -------------------------------------------- //
    // RAW
    // -------------------------------------------- //

    public double getTps() {
        throw notImplemented();
    }
}
