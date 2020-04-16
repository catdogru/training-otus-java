import org.junit.Before;
import org.junit.Test;
import ru.otus.hw06.atm.AtmEmulator;
import ru.otus.hw06.atm.AtmEmulatorImpl;
import ru.otus.hw06.constants.Nominal;
import ru.otus.hw06.exceptions.atm.EmptyRequiredSumException;
import ru.otus.hw06.exceptions.atm.NotEnoughBanknoteException;
import ru.otus.hw06.exceptions.atm.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static ru.otus.hw06.constants.Nominal.*;
import static ru.otus.hw06.constants.Nominal.RUB_100;

public class AtmTest {
    private AtmEmulator atmEmulator = new AtmEmulatorImpl();
    private List<Nominal> inputMoney = new ArrayList<>();

    @Before
    public void before() {
        inputMoney.addAll(List.of(RUB_500, RUB_500, RUB_2000, RUB_1000, RUB_1000, RUB_500, RUB_500, RUB_1000, RUB_1000, RUB_100, RUB_100, RUB_100));
        atmEmulator.putMoney(inputMoney);
    }

    @Test
    public void getBalanceTest() {
        assertEquals(atmEmulator.getBalance(), inputMoney.stream().mapToInt(Nominal::getIntValue).sum());
    }

    @Test
    public void notEnoughBanknoteTest() {
        assertThrows(NotEnoughBanknoteException.class, () -> atmEmulator.getMoney(3400));
    }

    @Test
    public void emptyInputSumTest() {
        assertThrows(EmptyRequiredSumException.class, () -> atmEmulator.getMoney(0));
    }

    @Test
    public void notEnoughMoneyTest() {
        assertThrows(NotEnoughMoneyException.class, () -> atmEmulator.getMoney(100500));
    }

    @Test
    public void getMoneyTest() {
        assertEquals(
                atmEmulator.getMoney(3300),
                List.of(RUB_2000, RUB_1000, RUB_100, RUB_100, RUB_100));
    }
}
