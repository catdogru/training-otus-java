package ru.otus.hw06.exceptions.atm;

public class IncorrectMinNominalException extends AtmException {
    private static final String MESSAGE = "Запрошенная сумма не может быть выдана, милорд, миниальный доступный номинал: %s";

    public IncorrectMinNominalException(int minAvailableNominal) {
        super(String.format(MESSAGE, minAvailableNominal));
    }
}