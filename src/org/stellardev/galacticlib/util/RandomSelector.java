package org.stellardev.galacticlib.util;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SuppressWarnings("unchecked")
public final class RandomSelector<T> {

    private final ToIntFunction<Random> selection;
    private final T[] elements;

    RandomSelector(T[] elements, ToIntFunction<Random> selection) {
        this.elements = elements;
        this.selection = selection;
    }

    public T next(Random random) {
        return this.elements[this.selection.applyAsInt(random)];
    }

    public Stream<T> stream(Random random) {
        Objects.requireNonNull(random, "random must not be null");
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new RandomSelector.BaseIterator(random), 1040), false);
    }

    private class BaseIterator implements Iterator<T> {

        private final Random random;

        BaseIterator(Random random) {
            this.random = random;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        public T next() {
            return RandomSelector.this.next(this.random);
        }
    }

    private static class RandomWeightedSelection implements ToIntFunction<Random> {

        private final double[] probabilities;
        private final int[] alias;

        RandomWeightedSelection(double[] probabilities) {
            int length = probabilities.length;
            double d = 1.0 / length;
            int[] var5 = new int[length];
            int var6 = 0;
            int[] var7 = new int[length];
            int var8 = 0;

            for(int i = 0; i < length; i++) {
                if(probabilities[i] < d) {
                    var5[var6++] = i;
                } else {
                    var7[var8++] = i;
                }
            }

            double[] var13 = new double[length];
            int[] var10 = new int[length];

            this.probabilities = var13;
            this.alias = var10;

            while(var8 != 0 && var6 != 0) {
                --var6;
                int var11 = var5[var6];
                --var8;
                int var12 = var7[var8];
                var13[var11] = probabilities[var11] * (double)length;
                var10[var11] = var12;
                probabilities[var12] += probabilities[var11] - d;
                if (probabilities[var12] < d) {
                    var5[var6++] = var12;
                } else {
                    var7[var8++] = var12;
                }
            }

            while(var6 != 0) {
                --var6;
                var13[var5[var6]] = 1.0D;
            }

            while(var8 != 0) {
                --var8;
                var13[var7[var8]] = 1.0D;
            }
        }

        @Override
        public int applyAsInt(Random value) {
            int var2 = value.nextInt(this.probabilities.length);

            return value.nextDouble() < this.probabilities[var2]? var2 : this.alias[var2];
        }
    }

    public static <T> RandomSelector<T> uniform(Collection<T> elements) throws IllegalArgumentException {
        Objects.requireNonNull(elements, "collection must not be null");
        Preconditions.checkArgument(!elements.isEmpty(), "collection must not be empty");

        int size = elements.size();
        Object[] objects = elements.toArray(new Object[size]);

        return new RandomSelector<>((T[]) objects, (r) -> r.nextInt(size));
    }

    public static <T> RandomSelector<T> weighted(Collection<T> elements, ToDoubleFunction<? super T> weighter) throws IllegalArgumentException {
        Objects.requireNonNull(elements, "elements must not be null");
        Objects.requireNonNull(weighter, "weighter must not be null");
        Preconditions.checkArgument(!elements.isEmpty(), "elements must not be empty");

        int size = elements.size();
        Object[] objects = elements.toArray();
        double totalWeight = 0.0D;
        double[] weights = new double[size];

        for(int i = 0; i < size; i++) {
            double weight = weighter.applyAsDouble((T) objects[i]);

            Preconditions.checkArgument(weight >= 0.0D, "weighter returned a negative number");

            weights[i] = weight;
            totalWeight += weight;
        }

        for(int i = 0; i < size; i++) {
            weights[i] /= totalWeight;
        }

        return new RandomSelector<>((T[]) objects, new RandomSelector.RandomWeightedSelection(weights));
    }

}