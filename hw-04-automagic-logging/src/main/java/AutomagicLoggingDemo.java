import calculator.AnotherCalculatorImpl;
import calculator.AnotherCalculatorInterface;
import calculator.CalculatorImpl;
import calculator.CalculatorInterface;

public class AutomagicLoggingDemo {
    public static void main(String... args) {
        CalculatorInterface calculator = IoC.<CalculatorInterface, CalculatorImpl>
                createProxy(new CalculatorImpl());
        calculator.calculate(100500);
        calculator.calculate(100500, 200500);
        calculator.nonAnnotatedCalculate(200500);

        AnotherCalculatorInterface anotherCalculator = IoC.<AnotherCalculatorInterface, AnotherCalculatorImpl>
                createProxy(new AnotherCalculatorImpl());
        anotherCalculator.calculate(300500);
        anotherCalculator.nonAnnotatedCalculate(400500);
    }
}
