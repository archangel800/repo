package org.example;

import java.util.Iterator;

public class CustomIterator <T> implements Iterator<T> {

    private T [] elements;
    private int currentIndex;

    public CustomIterator(T[] elements) {
        this.elements = elements;
        currentIndex = 0;
    }


    @Override
    public boolean hasNext() {
        if(currentIndex < elements.length)
            return true;
        return false;
    }

    @Override
    public T next() {
       if(hasNext()) {
           return elements[currentIndex++];
       }
       else {
           throw new IllegalStateException("No more Elements");
       }
    }
}
