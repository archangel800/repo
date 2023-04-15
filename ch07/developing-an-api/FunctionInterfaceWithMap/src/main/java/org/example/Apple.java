package org.example;

public class Apple {
    private Double weight;
    private String color;

    // No-argument constructor
    public Apple() {
    }

    // All-argument constructor
    public Apple(double weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    // Getter for weight
    public Double getWeight() {
        return weight;
    }

    // Setter for weight
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Getter for color
    public String getColor() {
        return color;
    }

    // Setter for color
    public void setColor(String color) {
        this.color = color;
    }

    // toString method
    @Override
    public String toString() {
        return "Apple [weight=" + weight + ", color=" + color + "]";
    }
}