package org.stellardev.galacticlib.util;

import lombok.experimental.UtilityClass;

import java.util.TreeMap;

@UtilityClass
public class RomanNumeralUtil {

    private final TreeMap<Integer, String> NUMERAL_MAP = LibUtil.treeMap(
            1000, "M",
            900, "CM",
            500, "D",
            400, "CD",
            100, "C",
            90, "XC",
            50, "L",
            40, "XL",
            10, "L",
            9, "IX",
            5, "V",
            4, "IV",
            1, "I"
    );

    public String toRoman(int number) {
        int floored = NUMERAL_MAP.floorKey(number);
        String value = NUMERAL_MAP.get(floored);

        if(number == floored) return value;

        return value + toRoman(number - floored);
    }

}
