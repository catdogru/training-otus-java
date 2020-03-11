package ru.otus.hw06;

import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import ru.otus.hw06.constants.CellType;

import java.util.Set;

public class CellContainer {
    private Multiset<CellType> entryCellSet = TreeMultiset.create();

    public void add(CellType type) {
        entryCellSet.add(type);
    }

    public void add(CellType type, int count) {
        entryCellSet.add(type, count);
    }

    public void remove(CellType type) {
        entryCellSet.remove(type);
    }

    public void remove(CellType type, int count) {
        entryCellSet.remove(type, count);
    }

    public int getCellSum(CellType type) {
        return entryCellSet.count(type) * type.getIntValue();
    }

    public Set<CellType> getCellTypeSet() {
        return entryCellSet.elementSet();
    }

    public void addAll(Set<CellType> cellTypeMultiset) {
        entryCellSet.addAll(cellTypeMultiset);
    }

    public void clear() {
        entryCellSet.clear();
    }

    public Multiset<CellType> getAllElement() {
        return entryCellSet;
    }

    public int getAllSum() {
        return entryCellSet.elementSet().stream().mapToInt(this::getCellSum).sum();
    }

    @Override
    public String toString() {
        return "CellContainer{" +
                "entryCellSet=" + entryCellSet +
                '}';
    }
}
