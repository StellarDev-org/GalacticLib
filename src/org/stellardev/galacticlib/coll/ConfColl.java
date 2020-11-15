package org.stellardev.galacticlib.coll;

import org.stellardev.galacticlib.entity.Conf;

public class ConfColl extends ConfigurationColl<Conf> {

    private static final ConfColl i = new ConfColl();
    public static ConfColl get() { return i; }

    private ConfColl() {
        super("galacticlib_conf", Conf.class);
    }
}
