package ru.otus.hw02.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomArrayList<E> implements List<E> {
    private Object[] innerArray;
    private static final int INITIAL_CAPACITY = 10; //magic digit

    public CustomArrayList() {
        innerArray = new Object[INITIAL_CAPACITY];
    }

    public CustomArrayList(int capacity) {
        innerArray = new Object[capacity];
    }

    public CustomArrayList(E[] array) {
        this.innerArray = array;
    }

    public int size() {
        return innerArray.length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object checkedObject) {
        return indexOf(checkedObject) >= 0;
    }

    public boolean addAll(Collection<? extends E> addedCollection) {
        throw new UnsupportedOperationException();
    }

    public boolean add(E element) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public E get(int index) {
        throw new UnsupportedOperationException();
    }

    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param object may be null
     * @return first index of element or -1 if element was not found
     */
    public int indexOf(Object object) {
        for (int i = 0; i < size(); i++) {
            Object currentElement = innerArray[i];
            if (object == currentElement || object.equals(currentElement)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param object may be null
     * @return last index of element or -1 if element was not found
     */
    public int lastIndexOf(Object object) {
        for (int i = size() - 1; i == 0; i--) {
            Object currentElement = innerArray[i];
            if (object == currentElement || object.equals(innerArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }
}
