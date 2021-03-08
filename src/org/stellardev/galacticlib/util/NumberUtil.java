package org.stellardev.galacticlib.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

@UtilityClass
public class NumberUtil {

    private final DecimalFormat CHANCE_FORMAT = new DecimalFormat("#,##0.00");
    private final DecimalFormat SINGLE_DECIMAL = new DecimalFormat("#,##0.0");
    private final NavigableMap<Long, String> SUFFIXES = new TreeMap<>();

    static {

        SUFFIXES.put(1_000L, "k");
        SUFFIXES.put(1_000_000L, "M");
        SUFFIXES.put(1_000_000_000L, "B");
        SUFFIXES.put(1_000_000_000_000L, "T");
        SUFFIXES.put(1_000_000_000_000_000L, "Q");
    }

    public String format(double number) {
        return NumberFormat.getInstance(Locale.US).format(number);
    }

    public String format(BigDecimal bigDecimal) {
        return NumberFormat.getInstance().format(bigDecimal);
    }

    public String formatDoubleDigit(double number) {
        return CHANCE_FORMAT.format(number);
    }

    public String formatSingleDigit(double number) {
        return SINGLE_DECIMAL.format(number);
    }

    public String formatToSuffix(long amount) {
        if(amount < 0) return "-" + formatToSuffix(-amount);
        if(amount < 1000) return Long.toString(amount);

        Map.Entry<Long, String> entry = SUFFIXES.floorEntry(amount);
        Long divideBy = entry.getKey();
        String suffix = entry.getValue();

        return SINGLE_DECIMAL.format(((double) amount / (double) divideBy)) + suffix;
    }

    public String getProgressBar(int current, int max, int totalBars, String symbol, ChatColor completeColor, ChatColor notCompleteColor) {
        return getProgressBar(current, (long) max, totalBars, symbol, completeColor, notCompleteColor);
    }

    public String getProgressBar(long current, long max, int totalBars, String symbol, ChatColor completeColor, ChatColor notCompleteColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);
        int leftOver = (totalBars - progressBars);

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(completeColor.toString());

        for(int i = 0; i < progressBars; i++) {
            stringBuilder.append(symbol);
        }

        stringBuilder.append(notCompleteColor.toString());

        for(int i = 0; i < leftOver; i++) {
            stringBuilder.append(symbol);
        }

        return stringBuilder.toString();
    }

    public int getPercent(long current, long max) {
        double onePercent = max * 0.01;

        return (int) (current / onePercent);
    }

    public int getPercent(int current, int max) {
        return getPercent(current, (long) max);
    }

    public String shortenChance(double chance) {
        return CHANCE_FORMAT.format(chance);
    }

}
