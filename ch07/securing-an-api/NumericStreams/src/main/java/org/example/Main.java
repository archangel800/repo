package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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


        //if stream were empty it would return 0
        int calories = menu.stream().mapToInt(Dish::getCalories).sum();

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);

        Stream<Integer> integerStream = intStream.boxed();

        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();

        int max = maxCalories.orElse(1);

        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n%2 == 0);

        System.out.println(evenNumbers.count());


        List<double[]> collect = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> {
                    return IntStream.rangeClosed(1, 100)
                            .boxed()
                            .map(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                            .filter(t -> t[2] % 1 == 0);

                })
                .collect(Collectors.toList());

        collect.stream().limit(5)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}