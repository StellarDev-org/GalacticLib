package org.stellardev.galacticlib.coll;

import com.massivecraft.massivecore.MassiveCore;
import com.massivecraft.massivecore.store.Coll;
import org.stellardev.galacticlib.entity.Conf;

public class ConfColl extends Coll<Conf> {

    private static final ConfColl i = new ConfColl();
    public static ConfColl get() { return i; }

    private ConfColl() {
        super("galacticlib_conf");
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);

        if(!active) return;

        Conf.set(this.get(MassiveCore.INSTANCE, true));
    }
}
