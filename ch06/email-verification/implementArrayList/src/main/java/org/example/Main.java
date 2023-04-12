package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList(List.of(2,4,3,54,1,2,3,12,43,12,446,3,5,631,23,221));
        MyArrayList<Integer> integerMyArrayList = new MyArrayList<>(list);
        System.out.println(integerMyArrayList.getSize());
        System.out.println(integerMyArrayList.get(0));
        System.out.println(integerMyArrayList.contains(54));
        System.out.println(integerMyArrayList.add(1000));
        System.out.println(integerMyArrayList.contains(1000));
        integerMyArrayList.remove(1000);
        System.out.println(integerMyArrayList.contains(1000));
        integerMyArrayList.remove(54);
        System.out.println(integerMyArrayList.contains(54));
        integerMyArrayList.removeAll();
        System.out.println(integerMyArrayList.getSize());

    }
}