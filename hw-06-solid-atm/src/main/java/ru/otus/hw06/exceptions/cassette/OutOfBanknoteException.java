package ru.otus.hw06.exceptions.cassette;

public class OutOfBanknoteException extends CassetteException {
    private static final String MESSAGE = "В кассете закончились банкноты";

    public OutOfBanknoteException() {
        super(MESSAGE);
    }
}
