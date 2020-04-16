package ru.otus.hw06.exceptions.atm;

public class NotEnoughBanknoteException extends AtmException {
    private static final String MESSAGE = "Не хватает банкнот нужных номиналов, милорд.";

    public NotEnoughBanknoteException() {
        super(MESSAGE);
    }
}
