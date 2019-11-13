package ru.otus.hw02.collections;

import java.util.*;

public class CustomArrayList<E> implements List<E> {
    private Object[] innerArray;
    private int elementCount;
    private static final int CAPACITY = 10; //magic digit

    public CustomArrayList() {
        innerArray = new Object[CAPACITY];
    }

    public CustomArrayList(int capacity) {
        innerArray = new Object[capacity];
    }

    public CustomArrayList(Collection<? extends E> collection) {
        this.innerArray = collection.toArray();
    }

    public CustomArrayList(Object[] array) {
        this.innerArray = array;
    }

    public int size() {
        return elementCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object checkedObject) {
        return indexOf(checkedObject) >= 0;
    }

    public boolean addAll(Collection<? extends E> addedCollection) {
        try {
            int newInnerArrayLength = innerArray.length + addedCollection.size();
            innerArray = Arrays.copyOf(innerArray, newInnerArrayLength);
            for (E e : addedCollection) {
                innerArray[elementCount] = e;
                elementCount++;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean add(E element) {
        if (elementCount == innerArray.length) {
            innerArray = Arrays.copyOf(innerArray, innerArray.length + CAPACITY);
        }
        innerArray[elementCount] = element;
        elementCount++;
        return true;
    }

    public E get(int index) {
        checkIndex(index);
        return getInnerArrayElement(index);
    }

    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = getInnerArrayElement(index);
        innerArray[index] = element;
        return oldValue;
    }

    private void checkIndex(int index) {
        if (index >= 0 && index < size()) {
            return;
        }
        throw new IndexOutOfBoundsException();
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
        return new CustomListIterator();
    }

    public E[] toArray() {
        return (E[]) innerArray;
    }

    @SuppressWarnings("unchecked")
    private E getInnerArrayElement(int index) {
        return (E) innerArray[index];
    }

    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public E remove(int index) {
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

    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "innerArray=" + Arrays.toString(innerArray) +
                '}';
    }

    private class CustomIterator implements Iterator<E> {
        int currentPosition = 0;
        int lastReturned = -1;

        @Override
        public boolean hasNext() {
            return currentPosition < CustomArrayList.this.size();
        }

        @Override
        public E next() {
            CustomArrayList.this.checkIndex(currentPosition);
            lastReturned = currentPosition;
            currentPosition++;
            return CustomArrayList.this.getInnerArrayElement(lastReturned);
        }
    }

    private class CustomListIterator extends CustomIterator implements ListIterator<E> {
        @Override
        public void set(E e) {
            innerArray[lastReturned] = e;
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
