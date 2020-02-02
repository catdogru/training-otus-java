import calculator.CalculatorInterface;

public class AutomagicLoggingDemo {
    public static void main(String... args) {
        CalculatorInterface calculator = IoC.createCalculator();
        calculator.calculate(10500);
        calculator.nonAnnotatedCalculate(20500);
    }
}
