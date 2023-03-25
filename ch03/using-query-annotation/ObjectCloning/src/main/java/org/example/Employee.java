package org.example;

public class Employee implements Cloneable{
    private int age;
    private String name;
    private Address address;

    public Employee() {
    }

    public Employee(int age, String name, Address address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee clone() throws CloneNotSupportedException
    {
        Employee emp = (Employee) super.clone();
        emp.address =address.clone();
        return emp;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
