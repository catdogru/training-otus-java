package ru.otus.hw06.atm;

import ru.otus.hw06.constants.Nominal;

import java.util.List;

public interface AtmEmulator {
    int getBalance();

    void putMoney(List<Nominal> money);

    List<Nominal> getMoney(int requiredSum);
}
