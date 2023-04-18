package org.example;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));

        filterThreeHighCaloricDishesNames(menu);
        lessThanThreeHundredAndTwenty(specialMenu);
        moreThanThreeHundredAndTwenty(specialMenu);

        List<String> stringList = Arrays.asList("giorgi", "mate", "mariami", "malxazi", "tea", "mimi", "posteri");

        List<Integer> integerList = stringList.stream()
                .map(item -> item.length())
                .collect(Collectors.toList());

        for(int i = 0; i < stringList.size(); i++) {
            System.out.println(stringList.get(i) + " has a size of " + integerList.get(i) + " letters");
        }

        List<String> strings = Arrays.asList("Hello", "World");

        List<String> individualCharacters = strings.stream()
                .map(item -> item.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println();
        System.out.println(individualCharacters);
        System.out.println();
        squareOfNumber(Arrays.asList(1,2,3,4,5));
        System.out.println();

        if(menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        if(menu.stream().allMatch(Dish::isVegetarian)) {
            System.out.println("The menu is fully vegetarian friendly!!");
        }

        else{
            System.out.println("(somewhat) Not a vegetarian friendly");
        }
        boolean isHealthy = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);

        System.out.println("our restaurant is healthy: " + isHealthy);

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println("this is our vegetarian dish: " + dish.getName()));

        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(item -> (item * item))
                .filter(item -> item % 3 == 0)
                .findFirst();
        System.out.println("first square Dibisible By Three is " + firstSquareDivisibleByThree.orElse(-1));
    }

    public static void filterThreeHighCaloricDishesNames(List<Dish> menu) {
        List<String> moreThanThreeHundred = menu.stream().filter(dish -> {
                    System.out.println(dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping: " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("Dishes that have more than 300 calories");
        System.out.println(moreThanThreeHundred);
        System.out.println();

    }
    public static void lessThanThreeHundredAndTwenty(List<Dish> specialMenu) {
    List<Dish> slicedMenu1 = specialMenu.stream()
            .takeWhile(dish -> dish.getCalories() < 320)
            .collect(Collectors.toList());
        System.out.println("Dishes that have less than 320 calories");
        System.out.println(slicedMenu1);
        System.out.println();
    }

    public static void moreThanThreeHundredAndTwenty(List<Dish> specialMenu) {
        List<Dish> slicedMenu1 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
        System.out.println("Dishes that have more than 320 calories");
        System.out.println(slicedMenu1);
        System.out.println();
    }

    public static void squareOfNumber(List<Integer> integerList) {
        List<Integer> squared = integerList.stream()
                .map(item -> item*item)
                .collect(Collectors.toList());
        System.out.println("Square of Numbers");
        System.out.println(squared);
        System.out.println();
    }

    public static void combineTwoLists(List<Integer> list1, List<Integer> list2) {
        List<int[]> pairs =
                list1.stream()
                        .flatMap(i -> list2.stream()
                                .filter(b -> (b+i) % 3 == 0 )
                                .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());
    }
}