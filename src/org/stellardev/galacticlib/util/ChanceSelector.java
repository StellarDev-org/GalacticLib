package org.stellardev.galacticlib.util;

import com.google.common.util.concurrent.AtomicDouble;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToDoubleFunction;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

public class ChanceSelector<T> {

    private final Map<T, ToDoubleFunction<? super T>> objectMap = new HashMap<>();
    private final boolean outOfHundred;

    public ChanceSelector(boolean outOfHundred) {
        this.outOfHundred = outOfHundred;
    }

    public void add(final T element, final ToDoubleFunction<? super T> chance) {
        requireNonNull(element, "elements must not be null");
        requireNonNull(chance, "chance must not be null");

        double selectionChance = chance.applyAsDouble(element);

        checkArgument(selectionChance > 0d, "selectionChance returned a negative number");

        this.objectMap.put(element, chance);
    }

    public T getRandomElement() {
        return getRandomElement(0.0);
    }

    public T getRandomElement(double extraChance) {
        checkArgument(extraChance >= 0.0, "the extra chance cannot be less than 0.");

        NavigableMap<Double, T> navigableMap = new TreeMap<>();
        AtomicDouble tempTotal = new AtomicDouble(0.0);

        this.objectMap.forEach((element, chance) -> {
            double chanceTotal = chance.applyAsDouble(element) + extraChance;

            navigableMap.put(chanceTotal, element);
            tempTotal.getAndAdd(chanceTotal);
        });

        double totalChance = tempTotal.get();
        double randomValue = ThreadLocalRandom.current().nextDouble();
        double value = randomValue * (this.outOfHundred? 100 : totalChance);

        if(value > totalChance) return null;

        Map.Entry<Double, T> entry = navigableMap.ceilingEntry(value);

        return entry == null? null : entry.getValue();
    }

}
