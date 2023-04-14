package org.example;

import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
       Integer item = 10;

        Predicate predicate = new Predicate() {

            @Override
            public boolean test(Object o) {
               return 10 == item;
            }
        };
        System.out.println(predicate.test(new Object()));
    }
}