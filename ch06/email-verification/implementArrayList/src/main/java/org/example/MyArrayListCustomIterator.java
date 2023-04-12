package org.example;

import java.util.Iterator;

public class MyArrayListCustomIterator<T> implements Iterator<T> {
    private T[] elements;
    private int currentIndex;

    public MyArrayListCustomIterator (T[] elements) {
        this.elements = elements;
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < elements.length;
    }

    @Override
    public T next() {
       if(hasNext()) {
           return elements[currentIndex++];
       }
       else {
           throw new IllegalStateException("No more elements in the MyArrayList");
       }
    }
}
