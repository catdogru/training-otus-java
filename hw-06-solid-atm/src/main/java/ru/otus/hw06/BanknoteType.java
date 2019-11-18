package ru.otus.hw06;

public enum BanknoteType {
    TYPE_100(100),
    TYPE_200(200),
    TYPE_500(500),
    TYPE_1000(1000),
    TYPE_2000(2000),
    TYPE_5000(5000);

    private final int intValue;

    BanknoteType(int value) {
        intValue = value;
    }

    public int getIntValue() {
        return intValue;
    }
}