package ru.otus.hw06.constants;

public enum Nominal {
    RUB_100(100),
    RUB_200(200),
    RUB_500(500),
    RUB_1000(1000),
    RUB_2000(2000),
    RUB_5000(5000);

    private final int intValue;

    Nominal(int value) {
        intValue = value;
    }

    public int getIntValue() {
        return intValue;
    }
}