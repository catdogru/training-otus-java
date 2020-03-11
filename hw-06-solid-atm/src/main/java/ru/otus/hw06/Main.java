package ru.otus.hw06;

import ru.otus.hw06.constants.CellType;

public class Main {
    public static void main(String... args) {
        var emulator = new AtmEmulator();
        emulator.put(CellType.TYPE_500, 2);
        emulator.put(CellType.TYPE_1000, 2);
        emulator.put(CellType.TYPE_500, 2);
        emulator.put(CellType.TYPE_1000, 2);
        emulator.put(CellType.TYPE_100, 3);

        emulator.getMoney(3400);
    }
}