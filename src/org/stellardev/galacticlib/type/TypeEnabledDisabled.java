package org.stellardev.galacticlib.type;

import com.massivecraft.massivecore.util.Txt;
import org.stellardev.galacticlib.entity.Conf;

public class TypeEnabledDisabled {

    public static String get(boolean bool) {
        return Txt.parse(bool? Conf.get().msgEnabledDisplay : Conf.get().msgDisabledDisplay);
    }

}
