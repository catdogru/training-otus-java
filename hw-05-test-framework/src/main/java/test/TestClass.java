package test;

import annotation.Before;
import annotation.Test;
import annotation.After;

public class TestClass {
    @Test
    public static void testMethod() {
        System.out.println("running test method");
    }

    @Test
    public static void testMethodWithException() {
        System.out.println("running failed test method");
        throw new RuntimeException();
    }

    @Before
    public static void before() {
        System.out.println("running before method");
    }

    @After
    public static void after() {
        System.out.println("running after method");
    }
}