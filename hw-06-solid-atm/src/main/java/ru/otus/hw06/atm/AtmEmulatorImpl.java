package ru.otus.hw06.atm;

import ru.otus.hw06.cassete.Cassette;
import ru.otus.hw06.cassete.CassetteImpl;
import ru.otus.hw06.constants.Nominal;
import ru.otus.hw06.exceptions.*;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.min;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

public class AtmEmulatorImpl implements AtmEmulator {
    private Map<Nominal, Cassette> cassettes = new HashMap<>();
    private List<Nominal> cashOut = new ArrayList<>();

    public AtmEmulatorImpl() {
        initialize();
    }

    @Override
    public void put(List<Nominal> money) {
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
        tryToGetRequiredSum(requiredSum);

        if (cashOut.stream().mapToInt(Nominal::getIntValue).sum() < requiredSum) {
            rollback();
            throw new NotEnoughBanknoteException();
        }

        return cashOut;
    }

    private void tryToGetRequiredSum(int requiredSum) {
        /* start from larger nominal values */
        for (Nominal nominal : cassettes.keySet()
                .stream()
                .sorted(reverseOrder())
                .collect(toList())) {

            if (nominal.getIntValue() > requiredSum) {
                continue;
            }

            if (nominal.getIntValue() == requiredSum) {
                if (cassettes.get(nominal).getBanknote()) {
                    cashOut.add(nominal);
                    break;
                }
            }

            if (cassettes.get(nominal).getBanknote()) {
                cashOut.add(nominal);
                int delta = requiredSum - nominal.getIntValue();
                tryToGetRequiredSum(delta);
                break;
            }
        }
    }

    private void checkRequiredSum(int requiredSum) throws AtmException {
        if (requiredSum == 0) {
            throw new EmptyRequiredSumException();
        }

        int currentBalance = getBalance();
        if (requiredSum > currentBalance) {
            throw new NotEnoughMoneyException(currentBalance);
        }

        int minNominal = min(cassettes.keySet()).getIntValue();
        if (requiredSum % minNominal != 0) {
            throw new IncorrectMinNominalException(minNominal);
        }
    }

    private void rollback() {
        cashOut.forEach(nominal -> cassettes.get(nominal).addBanknote());
        cashOut.clear();
    }

    private void initialize() {
        Stream.of(Nominal.values())
                .forEach(nominal -> cassettes.put(nominal, new CassetteImpl(nominal)));
    }
}