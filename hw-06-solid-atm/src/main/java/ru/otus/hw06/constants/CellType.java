package ru.otus.hw06.constants;

public enum CellType {
    TYPE_5000(5000),
    TYPE_2000(2000),
    TYPE_1000(1000),
    TYPE_500(500),
    TYPE_200(200),
    TYPE_100(100);

    private final int intValue;

    CellType(int value) {
        intValue = value;
    }

    public int getIntValue() {
        return intValue;
    }
}