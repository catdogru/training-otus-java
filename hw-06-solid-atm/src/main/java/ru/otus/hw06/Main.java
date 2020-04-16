package ru.otus.hw06;

import ru.otus.hw06.atm.AtmEmulatorImpl;
import ru.otus.hw06.constants.Nominal;
import ru.otus.hw06.exceptions.atm.AtmException;

import java.util.List;

import static ru.otus.hw06.constants.Nominal.*;

public class Main {
    public static void main(String... args) {
        List<Nominal> money = List.of(RUB_500, RUB_500, RUB_2000, RUB_1000, RUB_1000, RUB_500, RUB_500, RUB_1000, RUB_1000, RUB_100, RUB_100, RUB_100);
        var emulator = new AtmEmulatorImpl();
        emulator.putMoney(money);
        System.out.println("Баланс до операции: " + emulator.getBalance());
        try {
            //System.out.println(emulator.getMoney(3400));
            System.out.println(emulator.getMoney(3300));
            //System.out.println(emulator.getMoney(0));
        } catch (AtmException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Баланс после операции: " + emulator.getBalance());
        }
    }
}