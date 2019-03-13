package com.marunoona.example;

import java.util.function.Function;
import java.util.function.Supplier;

public class ConcurrentGreeter extends Greeter {
    public void greeter(){
        // ex01. super class method reference
        Thread thread = new Thread(super::greet); // Method Reference, equal to '() -> super.greet()'
        thread.start();

        // ex02. instance method reference
        Function<String, String> function;
        Greeter greeter = new Greeter();
        function = greeter::sayName;
        System.out.println(function.apply("apple"));

        // ex03. static method reference
        Function<Integer, String> sayAge = Greeter::sayAge;
        System.out.println(sayAge.apply(28));

        // ex04. constructor reference > supplier
        // Constructor without arguments
        Supplier<Greeter> greeterSupplier = Greeter::new;
        greeterSupplier.get();
        // Constructor with arguments
        greeterSupplier = () -> new Greeter("yejin92");
        greeter = greeterSupplier.get();
        System.out.println("Your ID is " + greeter.getId());

    }
}
