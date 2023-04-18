package org.example;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,4,56,1,23);
        int product = numbers.stream().reduce(1, (a, b) -> a*b);
        System.out.println(product);

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

        List<Person> people = Arrays.asList(
                new Person(Arrays.asList("red", "green", "blue")),
                new Person(Arrays.asList("orange", "yellow")),
                new Person(Arrays.asList("purple", "pink", "white")));

        List<String> uniqueColors = people.stream()
                .map(Person::getColor)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueColors);

        System.out.println();
        System.out.println("Calculate Number of dishes in a stream");

        Optional<Integer> number = menu.stream().map(item -> 1)
                .reduce(Integer::sum);
    }
}