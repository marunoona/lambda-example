package com.marunoona.example;

public class Greeter {
    private String id;

    public Greeter(){
        System.out.println("Call Greeter Constructor.");
    }
    public Greeter(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void greet() {
        System.out.println("Hi, Nice to meet you");
    }

    public String sayName(String str) {
        return "My name is " + str;
    }

    public static String sayAge(int x) {
        return "I'm " + x;
    }
}
