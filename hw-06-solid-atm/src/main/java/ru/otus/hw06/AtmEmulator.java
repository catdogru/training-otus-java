package ru.otus.hw06;

import ru.otus.hw06.constants.Nominal;
import ru.otus.hw06.exceptions.*;

import static java.util.Collections.min;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

public class AtmEmulator {
    private CassetteContainer innerContainer = new CassetteContainer();
    private CassetteContainer cashOutContainer = new CassetteContainer();

    public int getBalance() {
        return innerContainer.getTotalSum();
    }

    public void put(Nominal nominal, int count) {
        innerContainer.add(nominal, count);
    }

    public CassetteContainer getMoney(int requiredSum) throws AtmException {
        checkSum(requiredSum);
        tryToGetRequiredSum(requiredSum);
        if (cashOutContainer.getTotalSum() < requiredSum) {
            rollback();
            throw new NotEnoughBanknoteException();
        }
        return cashOutContainer;
    }

    private void tryToGetRequiredSum(int reqSum) {
        /* start from larger nominal values */
        for (Nominal nominal : innerContainer.getAvailableNominals()
                .stream()
                .sorted(reverseOrder())
                .collect(toList())) {

            if (nominal.getIntValue() > reqSum) {
                continue;
            }

            if (nominal.getIntValue() == reqSum) {
                innerContainer.remove(nominal);
                cashOutContainer.add(nominal);
                break;
            }

            innerContainer.remove(nominal);
            cashOutContainer.add(nominal);
            int delta = reqSum - nominal.getIntValue();
            tryToGetRequiredSum(delta);
            break;
        }
    }

    private void checkSum(int requiredSum) throws AtmException {
        if (requiredSum == 0) {
            throw new EmptyRequiredSumException();
        }

        int currentBalance = getBalance();
        if (requiredSum > currentBalance) {
            throw new NotEnoughMoneyException(currentBalance);
        }

        int minNominal = min(innerContainer.getAvailableNominals()).getIntValue();
        if (requiredSum % minNominal != 0) {
            throw new IncorrectMinNominalException(minNominal);
        }
    }

    private void rollback() {
        innerContainer.addAll(cashOutContainer);
        cashOutContainer.clear();
    }
}
