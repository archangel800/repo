package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4, 5};
        CustomIterator<Integer> iterator = new CustomIterator<>(numbers);

        while(iterator.hasNext()) {
            Integer num = iterator.next();
            System.out.println(num);
        }
    }
}