package ru.otus.hw02.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String... args) {
        CustomArrayList<Integer> sourceCustomArrayList = new CustomArrayList<>(new Integer[]{800, 500, 600, 700});
        Collections.addAll(sourceCustomArrayList, 100500, 200500, 300500);
        sourceCustomArrayList.addAll(getRandomlyFilledArrayList());

        CustomArrayList<Integer> copiedCustomArrayList = new CustomArrayList<>(sourceCustomArrayList.size());
        CustomArrayList<Integer> sortedCustomArrayList = new CustomArrayList<>(sourceCustomArrayList.size());
        Collections.copy(copiedCustomArrayList, sourceCustomArrayList);
        Collections.copy(sortedCustomArrayList, sourceCustomArrayList);

        Collections.sort(sortedCustomArrayList);

        System.out.println("Source array: " + sourceCustomArrayList);
        System.out.println("Copied array: " + copiedCustomArrayList);
        System.out.println("SortedArray:  " + sortedCustomArrayList);
    }

    private static List<Integer> getRandomlyFilledArrayList() {
        int ARRAY_LIST_SIZE = 25;

        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < ARRAY_LIST_SIZE; i++) {
            arrayList.add(random.nextInt(500));
        }
        return arrayList;
    }
}
