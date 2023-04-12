package org.example;

import java.util.List;

public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public int getSize() {
        return this.size;
    }

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList (List<T> items) {
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
        for (T item : items) {
            add(item);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public boolean contains(T item) {
        for(int i = 0; i< size; i++) {
            if(elements[i].equals(item))
                return true;
        }
        return false;
    }

    public T get (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return (T) elements[index];
    }

    public boolean add (T item) {
        if(size == elements.length) {
            resize();
        }
        elements[size++] = item;

        if(elements[size-1] == item) {
            return true;
        }
        return false;
    }

    public void set (int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        elements[index] =  item;
    }

    public void removeAll() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void removeIndex (int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        int numberMoved = size - index -1 ;
        if(numberMoved > 0) {
            System.arraycopy(elements, index+1, elements, index, numberMoved);
        }
        elements[--size] = null;
    }

    public boolean remove (T item) {
        for(int i = 0; i < size; i++) {
            if(elements[i].equals(item)) {
                removeIndex(i);
                return true;
            }
        }
        return false;
    }
    private void resize() {
        int newLength = elements.length * 2;
        Object[] newElements = new Object[newLength];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}
