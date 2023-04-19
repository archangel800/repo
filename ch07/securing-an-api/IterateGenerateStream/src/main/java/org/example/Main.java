package org.example;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        Stream.iterate(0, n -> n + 2)
//                .limit(10)
//                .forEach(System.out::println);

        System.out.println("Fibonacci");
        Stream.iterate(new int[]{0, 1}, a -> new int[] {a[1], a[0]+a[1]})
                .limit(20)
                .forEach(item -> System.out.println(item[0] + " " + item[1] + " "));
    }
}