package org.example;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Map<String, BiFunction<Double, String, Apple>> map = new HashMap<>();
        map.put("greenapple", Apple::new);
        map.put("redapple", Apple::new);
        map.put("yellowapple", Apple::new);
        System.out.println(map.get("redapple").apply(0.2, "red"));

        Apple apple1 = new Apple(0.2, "Red");
        Apple apple2 = new Apple(0.3, "Green");
        Apple apple3 = new Apple(0.25, "Yellow");
        Apple apple4 = new Apple(0.35, "Red");
        Apple apple5 = new Apple(0.28, "Green");
        Apple apple6 = new Apple(0.22, "Yellow");
        Apple apple7 = new Apple(0.33, "Red");

        // Add Apple instances to an ArrayList
        ArrayList<Apple> appleList = new ArrayList<>();
        appleList.add(apple1);
        appleList.add(apple2);
        appleList.add(apple3);
        appleList.add(apple4);
        appleList.add(apple5);
        appleList.add(apple6);
        appleList.add(apple7);

        //***** Sort Using Anonymous class *****
//        Collections.sort(appleList, new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//               return o1.getWeight().compareTo(o2.getWeight());
//            }
//        });

//***** Sort Using Lambda *****
//        appleList.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        ////***** Sort Using Method Reference *****
        appleList.sort(Comparator.comparing(Apple::getWeight).thenComparing(Comparator.comparing(Apple::getColor)).reversed());

        //Define 2 predicates
        Predicate<Apple> applePredicate = apple -> apple.getColor().equals("Red");
        Predicate<Apple> applePredicate1  = apple -> apple.getWeight() > 0.2;

        ArrayList<Apple> RedMoreThanTwoHundred = redHeavyApples(appleList, applePredicate.and(applePredicate1)); //Chain 2 predicates using "and"
        System.out.println();
        System.out.println(appleList);
        System.out.println();
        System.out.println(RedMoreThanTwoHundred);
    }

    //print only red apples that are more than 0.2 kg using chained predicates
    public static ArrayList<Apple> redHeavyApples(ArrayList<Apple> apples, Predicate<Apple> predicate) {
        ArrayList<Apple> newList = new ArrayList<>();
        for(Apple item : apples) {
            if(predicate.test(item)) {
                newList.add(item);
            }
        }
        return newList;
    }
}