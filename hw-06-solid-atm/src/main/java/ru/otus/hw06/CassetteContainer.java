package ru.otus.hw06;

import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import ru.otus.hw06.constants.Nominal;

import java.util.Set;

public class CassetteContainer {
    /* cassette: nominal + banknoteCount */
    private Multiset<Nominal> cassetteMultiset = TreeMultiset.create();

    public void add(Nominal nominal) {
        cassetteMultiset.add(nominal);
    }

    public void add(Nominal nominal, int count) {
        cassetteMultiset.add(nominal, count);
    }

    public void remove(Nominal nominal) {
        cassetteMultiset.remove(nominal);
    }

    public void remove(Nominal nominal, int count) {
        cassetteMultiset.remove(nominal, count);
    }

    public int getCassetteSum(Nominal nominal) {
        return cassetteMultiset.count(nominal) * nominal.getIntValue();
    }

    public Set<Nominal> getAvailableNominals() {
        return cassetteMultiset.elementSet();
    }

    public void addAll(CassetteContainer addedCassetteContainer) {
        cassetteMultiset.addAll(addedCassetteContainer.cassetteMultiset);
    }

    public void clear() {
        cassetteMultiset.clear();
    }

    public int getTotalSum() {
        return cassetteMultiset.elementSet().stream().mapToInt(this::getCassetteSum).sum();
    }

    @Override
    public String toString() {
        return "Выдано: " + cassetteMultiset.toString();
    }
}