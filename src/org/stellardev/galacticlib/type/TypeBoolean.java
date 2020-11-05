package org.stellardev.galacticlib.type;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.type.TypeAbstract;
import com.massivecraft.massivecore.util.MUtil;
import org.bukkit.command.CommandSender;
import org.stellardev.galacticlib.entity.Conf;

import java.util.Collection;

public class TypeBoolean extends TypeAbstract<Boolean> {

    private static final TypeBoolean i = new TypeBoolean();
    public static TypeBoolean get() { return i; }

    private TypeBoolean() {
        super(Boolean.class);
    }

    @Override
    public Boolean read(String s, CommandSender commandSender) throws MassiveException {
        try {
            return Boolean.parseBoolean(s);
        } catch (Exception ex) {
            throw new MassiveException().addMsg(Conf.get().msgInvalidBoolean);
        }
    }

    @Override
    public Collection<String> getTabList(CommandSender commandSender, String s) {
        return MUtil.list("true", "false");
    }
}
