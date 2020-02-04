package calculator;

import annotation.Log;

public class AnotherCalculatorImpl implements AnotherCalculatorInterface {
    @Override
    @Log
    public void calculate(int arg) {
        System.out.println("вызов другого метода с аннотацией");
    }

    @Override
    public void nonAnnotatedCalculate(int arg) {
        System.out.println("вызов другого метода без аннотации");
    }
}
