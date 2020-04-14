package ru.otus.hw06.exceptions.atm;

public class EmptyRequiredSumException extends AtmException {
    private static final String MESSAGE = "Некорректный ввод суммы, милорд";

    public EmptyRequiredSumException() {
        super(MESSAGE);
    }
}
