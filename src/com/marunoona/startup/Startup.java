package com.marunoona.startup;

import com.marunoona.interfaces.Functionals;

public class Startup {
    public static void main(String[] args){
        /**
         * ex1. thread
         */
        Runnable runnable = () -> System.out.println("Hello world");
        runnable.run();
        /**
         * ex2. functional interface
         */
        Functionals functionals  = (int a, int b) -> a+b;
        System.out.println(functionals.sum(1,2));

        functionals = (a, b) -> (a+b);
        System.out.println(functionals.sum(1,2));
    }
}
