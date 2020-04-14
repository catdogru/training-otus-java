package ru.otus.hw06.atm;

import ru.otus.hw06.cassette.Cassette;
import ru.otus.hw06.cassette.CassetteImpl;
import ru.otus.hw06.constants.Nominal;
import ru.otus.hw06.exceptions.atm.*;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

public class AtmEmulatorImpl implements AtmEmulator {
    private Map<Nominal, Cassette> cassettes = new HashMap<>();

    public AtmEmulatorImpl() {
        initialize();
    }

    @Override
    public void putMoney(List<Nominal> money) {
        money.forEach(nominal -> cassettes.get(nominal).addBanknote());
    }

    @Override
    public int getBalance() {
        return cassettes.keySet()
                .stream()
                .mapToInt(nominal -> cassettes.get(nominal).getCassetteSum())
                .sum();
    }

    @Override
    public List<Nominal> getMoney(int requiredSum) {
        checkRequiredSum(requiredSum);
        List<Nominal> banknoteCombination = calculateBanknoteCombination(requiredSum);
        getMoneyFromCassettes(banknoteCombination);
        return banknoteCombination;
    }

    private void getMoneyFromCassettes(List<Nominal> money) {
        money.forEach(nominal -> cassettes.get(nominal).getBanknote());
    }

    private List<Nominal> calculateBanknoteCombination(int requiredSum) {
        List<Nominal> banknoteCombination = new ArrayList<>();
        /* start from larger nominal values */
        for (Nominal nominal : cassettes.keySet()
                .stream()
                .sorted(reverseOrder())
                .collect(toList())) {
            int banknoteCount =  Math.min(cassettes.get(nominal).getBanknoteCount(), requiredSum / nominal.getIntValue());
            if (banknoteCount == 0) {
                continue;
            }
            requiredSum = requiredSum - (nominal.getIntValue() * banknoteCount);
            for (int i = 0; i < banknoteCount; i++) {
                banknoteCombination.add(nominal);
            }
        }
        if (requiredSum > 0) throw new NotEnoughBanknoteException();
        return banknoteCombination;
    }

    private void checkRequiredSum(int requiredSum) throws AtmException {
        if (requiredSum == 0) {
            throw new EmptyRequiredSumException();
        }

        int currentBalance = getBalance();
        if (requiredSum > currentBalance) {
            throw new NotEnoughMoneyException(currentBalance);
        }

        int minNominal = Collections.min(cassettes.keySet()).getIntValue();
        if (requiredSum % minNominal != 0) {
            throw new IncorrectMinNominalException(minNominal);
        }
    }

    private void initialize() {
        Stream.of(Nominal.values())
                .forEach(nominal -> cassettes.put(nominal, new CassetteImpl(nominal)));
    }
}