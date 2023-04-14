package org.example;


import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Apple> apples = List.of(new Apple("Red", 200, "Round"),new Apple("Red", 205, "Square"),new Apple("Green", 220, "Round"),new Apple("Yellow", 145, "No shape"));
        prettyPrintApple(apples, apple -> {
            String weight = apple.getWeight()>=200 ? "Heavy" : "Light";
            return  "the color of this apple is \"" + apple.getColor() + "\" and this is \"" + weight +"\" apple";
        });
    }
    public static void prettyPrintApple(List<Apple> apples, AppleFilter appleFilter) {
        for(Apple apple : apples) {
            String output = appleFilter.filter(apple);
            System.out.println(output);
        }
    }
}
