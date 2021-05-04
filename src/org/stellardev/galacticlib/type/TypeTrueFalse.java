package org.stellardev.galacticlib.type;

import com.massivecraft.massivecore.util.Txt;
import org.stellardev.galacticlib.entity.Conf;

public class TypeTrueFalse {

    public static String get(boolean bool) {
        return Txt.parse(bool? Conf.get().msgTrueDisplay : Conf.get().msgFalseDisplay);
    }
}
