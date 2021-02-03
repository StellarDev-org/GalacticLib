package org.stellardev.galacticlib.type;

import com.massivecraft.massivecore.command.type.enumeration.TypeEnum;
import org.bukkit.Art;

public class TypeArt extends TypeEnum<Art> {

    private static final TypeArt i = new TypeArt();
    public static TypeArt get() { return i; }

    public TypeArt() {
        super(Art.class);
    }
}
