package ru.otus.hw01.maven;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class Main {
    private static String SPACE = " ";

    public static void main(String... args) {
        runImmutableSetExample();
        //runBimapExample();
    }

    private static void runImmutableSetExample() {
        Set<String> someColors = new HashSet<>(asList("red", "blue", "white"));
        for (String color : someColors) {
            System.out.println(
                    color + SPACE
                            + "is rainbow color:" + SPACE
                            + isRainbowColor(color));
        }
    }

    private static boolean isRainbowColor(String color) {
        return getRainbowColors().contains(color);
    }

    private static Set<String> getRainbowColors() {
        return ImmutableSet.<String>builder()
                .add("red")
                .add("orange")
                .add("yellow")
                .add("green")
                .add("blue")
                .add("indigo")
                .add("violet")
                .build();
    }

    private static void runBimapExample() {
        BiMap<String, Integer> stringIntegerBiMap = HashBiMap.create();
        stringIntegerBiMap.put("one", 1);
        stringIntegerBiMap.put("two", 2);
        stringIntegerBiMap.put("one_hundred_five_hundred", 100500);

        for (String s : stringIntegerBiMap.keySet()) {
            System.out.println("key:" + s + SPACE +
                    "value:" + stringIntegerBiMap.get(s).toString());
        }

        BiMap<Integer, String> integerStringBiMap = stringIntegerBiMap.inverse();
        for (Integer i : integerStringBiMap.keySet()) {
            System.out.println("key:" + i + SPACE +
                    "value:" + integerStringBiMap.get(i));
        }
    }
}
