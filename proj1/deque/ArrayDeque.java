package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        int index = moveRight(nextFirst);
        @Override
        public boolean hasNext() {
            return size != 0 && index != nextLast;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T item = items[index];
                index = moveRight(index);
                return item;
            }
            return  null;
        }
    }

    /**
     * Creates an empty array deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    /**
     *  Resize the deque to the length of capacity.
     */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(nextFirst + 1 + i) % items.length];
        }
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /**
     * Adds an item of type T to the front of the deque. You can assume that item is never null.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 4);
        }
        items[nextFirst] = item;
        nextFirst = moveLeft(nextFirst);
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque. You can assume that item is never null.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 4);
        }
        items[nextLast] = item;
        nextLast = moveRight(nextLast);
        size += 1;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space. Once all the items
     * have been printed, print out a new line.
     */
    public void printDeque() {
        int index = moveRight(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[index] + " ");
            index = moveRight(index);
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length > 16 && items.length > size * 4) {
            resize(items.length / 4);
        }
        nextFirst = moveRight(nextFirst);
        size -= 1;
        return items[nextFirst];
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length > 16 && items.length > size * 4) {
            resize(items.length / 4);
        }
        nextLast = moveLeft(nextLast);
        size -= 1;
        return items[nextLast];
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If
     * no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return items[(nextFirst + 1 + index) % items.length];
    }

    /**
     * Returns whether the parameter o is equal to the Deque. o is considered equal if it is a Deque
     * and if it contains the same contents (as governed by the generic T’s equals method) in the
     * same order.
     */
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<?> castedO = (Deque<?>) o;
        if (castedO.size() != this.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(get(i).equals(castedO.get(i)))) {
                return false;
            }
        }
        return true;
    }

    private int moveRight(int index) {
        return (index + 1) % items.length;

    }

    private int moveLeft(int index) {
        return (index - 1 + items.length) % items.length;
    }
}
