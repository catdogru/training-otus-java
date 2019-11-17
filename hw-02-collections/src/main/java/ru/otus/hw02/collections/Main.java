package ru.otus.hw02.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String... args) {
        CustomArrayList<Integer> sourceCustomArrayList = new CustomArrayList<>();
        CustomArrayList<Integer> copiedCustomArrayList = new CustomArrayList<>();
        CustomArrayList<Integer> sortedCustomArrayList = new CustomArrayList<>();
        CustomArrayList<Integer> withAddAllCustomArrayList = new CustomArrayList<>();

        fillListRandomly(sourceCustomArrayList);
        fillListRandomly(copiedCustomArrayList);
        fillListRandomly(sortedCustomArrayList);
        fillListRandomly(withAddAllCustomArrayList);

        Collections.copy(copiedCustomArrayList, sourceCustomArrayList);
        Collections.copy(sortedCustomArrayList, sourceCustomArrayList);
        Collections.copy(withAddAllCustomArrayList, sourceCustomArrayList);

        Collections.sort(sortedCustomArrayList);

        withAddAllCustomArrayList.addAll(asList(5, 6, 7, 100500));
        ArrayList<Integer> addedArrayList = new ArrayList<>();
        fillListRandomly(addedArrayList);
        Collections.addAll(withAddAllCustomArrayList, addedArrayList.toArray(new Integer[0]));

        System.out.println("Source array: " + sourceCustomArrayList);
        System.out.println("Copied array: " + copiedCustomArrayList);
        System.out.println("SortedArray:  " + sortedCustomArrayList);
        System.out.println("withAddAllCustomArrayList:  " + withAddAllCustomArrayList);
    }

    private static void fillListRandomly(List<Integer> list) {
        int ARRAY_LIST_SIZE = 5;

        Random random = new Random();
        for (int i = 0; i < ARRAY_LIST_SIZE; i++) {
            list.add(random.nextInt(500));
        }
    }
}
