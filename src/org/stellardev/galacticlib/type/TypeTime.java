package org.stellardev.galacticlib.type;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.TypeAbstract;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.command.CommandSender;
import org.stellardev.galacticlib.entity.Conf;

import java.util.Collection;

public class TypeTime extends TypeAbstract<Long> {

    private static final TypeTime i = new TypeTime();
    public static TypeTime get() { return i; }

    private TypeTime() {
        super(Long.class);
    }

    @Override
    public Long read(String arg, CommandSender commandSender) throws MassiveException {
        int multiplier = 0;

        if(arg.contains("m")) {
            multiplier = 60;
        } else if(arg.contains("h")) {
            multiplier = 3600;
        }

        String pattern = "[^0-9]";
        String number = arg.replaceAll(pattern, "");

        try {
            int actualNumber = Integer.parseInt(number);

            return (long) multiplier > 0? (actualNumber * multiplier) : (long) actualNumber;
        } catch (Exception ignore) {
            throw new MassiveException().addMsg(Conf.get().msgInvalidTime);
        }
    }

    @Override
    public Collection<String> getTabList(CommandSender sender, String arg) {
        return MUtil.list(
                "1", "1s", "1m", "1h"
        );
    }
}
