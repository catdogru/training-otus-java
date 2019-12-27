package ru.otus.hw06;

public class Main {
    public static void main(String... args) {
        AtmEmulator atmEmulator = new AtmEmulator();
        atmEmulator.put(BanknoteType.TYPE_100, 10);
        atmEmulator.put(BanknoteType.TYPE_500, 3);
        System.out.println(atmEmulator.getBalance());
        System.out.println(atmEmulator.getMoney(500));
        System.out.println(atmEmulator.getBalance());
    }
}
