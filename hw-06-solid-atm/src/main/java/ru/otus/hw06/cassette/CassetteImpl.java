package ru.otus.hw06.cassette;

import ru.otus.hw06.constants.Nominal;
import ru.otus.hw06.exceptions.cassette.OutOfBanknoteException;

public class CassetteImpl implements Cassette {
    private Nominal nominal;
    private int count;

    public CassetteImpl(Nominal nominal) {
        this.nominal = nominal;
    }

    @Override
    public void addBanknote() {
        count++;
    }

    @Override
    public void getBanknote() {
        if (count == 0) {
            throw new OutOfBanknoteException();
        }
        count--;
    }

    @Override
    public int getCassetteSum() {
        return nominal.getIntValue() * count;
    }

    @Override
    public int getBanknoteCount() {
        return count;
    }
}
