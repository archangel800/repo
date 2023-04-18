package org.example;

import jdk.jshell.PersistentSnippet;

import java.awt.*;
import java.util.List;

public class Person {
    private List<String> color;

    public Person(List<String> colors) {
        color = colors;
    }
    public Person(){}

    public void setColor(List<String> newColor) {
        color = newColor;
    }
    public List<String> getColor () {
        return List.copyOf(color);
    }
}
