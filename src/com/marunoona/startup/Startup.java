package com.marunoona.startup;

import com.marunoona.interfaces.Functionals;

public class Startup {
    public static void main(String[] args) {
        /**
         * ex1. thread
         */
        Runnable runnable = () -> System.out.println("Hello world");
        runnable.run();
        /**
         * ex2. functional interface
         */
        Functionals functionals = (int a, int b) -> a + b;
        System.out.println("#1. Add : " + functionals.calculate(1, 2));

        functionals = (a, b) -> (a + b);
        System.out.println("#2. Add : " + functionals.calculate(1, 2));

        functionals = (a, b) -> (a - b);
        System.out.println("#3. Minus : " + functionals.calculate(3, 2));

        functionals = (x, y) -> (x * y);
        System.out.println("#4 Multiply : " + functionals.calculate(3, 4));

        functionals = (x, y) -> (x / y);
        System.out.println("#5. Dividing : " + functionals.calculate(15, 3));
    }
}
