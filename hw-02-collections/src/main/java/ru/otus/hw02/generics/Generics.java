package ru.otus.hw02.generics;

import ru.otus.hw02.generics.model.Animal;
import ru.otus.hw02.generics.model.Cat;
import ru.otus.hw02.generics.model.HomeCat;

import java.util.List;

public class Generics {
    public static void main(String[] args) {

    }

    public void doSomething1(List<? extends Cat> list) {
        //list.add(new Animal());
    }

    public void doSomething2(List<? super Cat> list) {
        list.add(new Cat());
        //list.add(new Animal());
        list.add(new HomeCat());
    }
}
