package ru.otus.hw06;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class AtmEmulator {
    private Multiset<BanknoteType> storage = HashMultiset.create();

    public void put(BanknoteType banknoteType) {
        storage.add(banknoteType);
    }

    public void put(BanknoteType banknoteType, int count) {
        storage.add(banknoteType, count);
    }

    public void getMoney() {

    }

    public int getBalance() {
        int balance = 0;
        
        for (BanknoteType banknoteType : storage) {
            balance += banknoteType.getIntValue() * storage.count(banknoteType);
        }
        return balance;
    }
}
