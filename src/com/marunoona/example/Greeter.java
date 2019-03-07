package com.marunoona.example;

public class Greeter {
    public void greet(){
        System.out.println("Hi, Nice to meet you");
    }
    public String sayName(String str){
       return "My name is "+str;
    }
    public static String sayAge(int x){
        return "I'm " + x;
    }
}
