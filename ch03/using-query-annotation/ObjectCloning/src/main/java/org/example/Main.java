package org.example;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee employee = new Employee(18, "giorgi", new Address("Tbilisi's Street", 27));
        Employee emp1 = employee.clone();
        emp1.setName("niko");
        emp1.getAddress().setStreet("Todria");
        System.out.println(employee);
        System.out.println(emp1);
    }
}