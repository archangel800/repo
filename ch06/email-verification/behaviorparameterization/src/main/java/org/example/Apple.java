package org.example;

public class Apple {
    private String color;
    private int weight;
    private String shape;

    public Apple(String color, int weight, String shape) {
        this.color = color;
        this.weight = weight;
        this.shape = shape;
    }

    public Apple() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
