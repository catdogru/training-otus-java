package ru.otus.hw06;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Set;

public class MoneyStorage {
    private Multiset<BanknoteType> cellStorage = HashMultiset.create();

    public Set<BanknoteType> getFilledCellTypes() {
        return cellStorage.elementSet();
    }

    public void remove(Multiset<BanknoteType> cashOut) {
        for (BanknoteType banknoteType : cashOut.elementSet()) {
            cellStorage.remove(banknoteType, cashOut.count(banknoteType));
        }
    }
}
