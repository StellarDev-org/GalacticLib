package org.stellardev.galacticlib.util;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

public class ChanceSelector<T> {

    private final NavigableMap<Double, T> map = new TreeMap<>();
    private final boolean outOfHundred;

    private double total = 0;

    public ChanceSelector(boolean outOfHundred) {
        this.outOfHundred = outOfHundred;
    }

    public void add(final T element, final ToDoubleFunction<? super T> chance) {
        requireNonNull(element, "elements must not be null");
        requireNonNull(chance, "chance must not be null");

        double selectionChance = chance.applyAsDouble(element);

        checkArgument(selectionChance > 0d, "selectionChance returned a negative number");

        this.total += selectionChance;
        this.map.put(this.total, element);
    }

    public T getRandomElement() {
        checkArgument(this.total != 0.0, "there's no values currently added.");

        double value = ThreadLocalRandom.current().nextDouble() * (this.outOfHundred? 100 : this.total);

        if(value > this.total) return null;

        Map.Entry<Double, T> entry = this.map.ceilingEntry(value);

        return entry == null? null : entry.getValue();
    }

}
