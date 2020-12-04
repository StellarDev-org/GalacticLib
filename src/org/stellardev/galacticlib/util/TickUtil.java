package org.stellardev.galacticlib.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TickUtil {

    public long getTicksToMs(long ticks) {
        return (long) (ticks * 0.05 / 0.001);
    }

    public long getMsToTicks(long ms) {
        return (long) (ms * 0.001 / 0.05);
    }

}
