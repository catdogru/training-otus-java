package calculator;

import annotation.Log;

public class CalculatorImpl implements CalculatorInterface {
    @Override
    @Log
    public void calculate(int arg) {
        System.out.println("вызов метода с аннотацией");
    }

    @Override
    public void nonAnnotatedCalculate(int arg) {
        System.out.println("вызов метода без аннотации");
    }
}
