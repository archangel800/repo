package org.example;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
//        var planets = new String[] { "Mercury", "Venus", "Earth", "Mars", "18",
//                "Jupiter", "Saturn", "Uranus", "Neptune" };
//        System.out.println(Arrays.toString(planets));
//        System.out.println("Sorted in dictionary order:  ");
//        Arrays.sort(planets);
//        System.out.println(Arrays.toString(planets));
//        System.out.println("Sorted by length:  ");
//        Arrays.sort(planets, (String word1, String word2) -> word1.length()-word2.length());
//        BiFunction<Integer, Integer, String> biFunction = (i1, i2) -> "giorgi loves " + i1 + " and also " + i2;
//        System.out.println(biFunction.apply(12, 15));
//        System.out.println(Arrays.toString(planets));
//        List<Integer> lst = new ArrayList<>(List.of(2,5,1,4,7,12,3,4,19,20,22,25,21, 12,10));
//        removeList(lst);
//        System.out.println("In main method");
//        System.out.println(lst);
        ArrayList<Employee> employeeList = new ArrayList<>();

        Employee emp1 = new Employee("John", 25, 140000.0);
        Employee emp2 = new Employee("Jane", 30, 6120000.0);
        Employee emp3 = new Employee("Bob", 35, 70000000.0);
        Employee emp4 = new Employee("Alice", 40, 8120000.0);
        Employee emp5 = new Employee("Mike", 45, 2320000.0);
        Employee emp6 = new Employee("Samantha", 50, 56300000.0);
        Employee emp7 = new Employee("David", 55, 240000.0);

        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);
        employeeList.add(emp4);
        employeeList.add(emp5);
        employeeList.add(emp6);
        employeeList.add(emp7);

        // Output the employee list
        for (Employee emp : employeeList) {
            System.out.println(emp.toString());
        }
        System.out.println();
        System.out.println();
        Collections.sort(employeeList, Comparator.comparing(Employee::getSalary).thenComparing(Employee::getAge));
        for (Employee emp : employeeList) {
            System.out.println(emp.toString());
        }
    }
}