package org.stellardev.galacticlib.nms;

import com.massivecraft.massivecore.mixin.Mixin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NmsWorldBorder extends Mixin {

    // -------------------------------------------- //
    // DEFAULT
    // -------------------------------------------- //

    private static final NmsWorldBorder d = new NmsWorldBorder().setAlternatives(
            NmsWorldBorder18R1P.class
    );

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static NmsWorldBorder i = d;
    public static NmsWorldBorder get() { return i; }

    // -------------------------------------------- //
    // RAW
    // -------------------------------------------- //

    public void sendBorder(Player player, Location center, double radius) {
        throw notImplemented();
    }
}
