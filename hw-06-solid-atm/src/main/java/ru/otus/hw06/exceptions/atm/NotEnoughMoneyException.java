package ru.otus.hw06.exceptions.atm;

public class NotEnoughMoneyException extends AtmException {
    private static final String MESSAGE = "Не хватает золота, милорд. Остаток: %s";

    public NotEnoughMoneyException(int balance) {
        super(String.format(MESSAGE, balance));
    }
}
