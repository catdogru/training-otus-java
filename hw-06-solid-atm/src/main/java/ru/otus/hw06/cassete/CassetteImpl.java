package ru.otus.hw06.cassete;

import ru.otus.hw06.constants.Nominal;

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
    public boolean getBanknote() {
        if (count == 0) {
            return false;
        }
        count--;
        return true;
    }

    @Override
    public int getCassetteSum() {
        return nominal.getIntValue() * count;
    }
}
