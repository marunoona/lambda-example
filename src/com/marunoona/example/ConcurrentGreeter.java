package com.marunoona.example;

import java.util.function.Function;

public class ConcurrentGreeter extends Greeter {
    public void greeter(){
        // ex01. super class method reference
        Thread thread = new Thread(super::greet); // Method Reference, equal to '() -> super.greet()'
        thread.start();

        // ex02. instance method reference
        Function<String, Object> function;
        Greeter greeter = new Greeter();
        function = greeter::sayName;
        System.out.println(function.apply("apple"));

        // ex03. static method reference
        Function<Integer, String> sayAge = Greeter::sayAge;
        System.out.println(sayAge.apply(28));
    }
}
