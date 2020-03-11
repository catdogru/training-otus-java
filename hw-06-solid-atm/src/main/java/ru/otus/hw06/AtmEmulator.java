package ru.otus.hw06;

import ru.otus.hw06.constants.CellType;

public class AtmEmulator {
    private CellContainer innerStorage = new CellContainer();
    private CellContainer outCash = new CellContainer();

    public int getBalance() {
        return innerStorage.getAllSum();
    }

    public void put(CellType cellType, int count) {
        innerStorage.add(cellType, count);
    }

    public void tryToGetRequiredSum(int reqSum) {
        if (reqSum == 0) return;

        for (CellType cellType : innerStorage.getCellTypeSet()) {
            if (cellType.getIntValue() > reqSum) {
                continue;
            }

            if (cellType.getIntValue() == reqSum) {
                innerStorage.remove(cellType);
                outCash.add(cellType);
                break;
            }

            innerStorage.remove(cellType);
            outCash.add(cellType);
            int ostatok = reqSum - cellType.getIntValue();
            tryToGetRequiredSum(ostatok);
            break;
        }
    }

    public Object getMoney(int requiredSum) {
        int currentBalance = getBalance();
        if (requiredSum == 0) {
            throw new RuntimeException("Некорректный ввод суммы");
        }
        if (requiredSum > currentBalance) {
            throw new RuntimeException("Запрошенная сумма превышает остаток в банкомате, остаток: " + currentBalance);
        }

        tryToGetRequiredSum(requiredSum);

        if (outCash.getAllSum() < requiredSum) {
            rollback();
            throw new RuntimeException("Запрошенная сумма не может быть выдана, доступные номиналы: " + innerStorage.getCellTypeSet());
        }
        return outCash;
    }

    private void rollback() {
        innerStorage.addAll(outCash.getCellTypeSet());
        outCash.clear();
    }
}
