package ru.otus.hw06;

import ru.otus.hw06.constants.Nominal;
import ru.otus.hw06.exceptions.AtmException;

public class Main {
    public static void main(String... args) {
        var emulator = new AtmEmulator();
        emulator.put(Nominal.RUB_500, 2);
        emulator.put(Nominal.RUB_1000, 2);
        emulator.put(Nominal.RUB_500, 2);
        emulator.put(Nominal.RUB_1000, 2);
        emulator.put(Nominal.RUB_100, 3);
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