package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private Node sentinel;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        Node currentNode = sentinel.next;

        @Override
        public boolean hasNext() {
            return size != 0 && currentNode != sentinel;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T item = currentNode.item;
                currentNode = currentNode.next;
                return item;
            }
            return  null;
        }
    }

    private class Node {
        T item;
        Node pre;
        Node next;

        Node(T item) {
            this.item = item;
        }

        Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque. You can assume that item is never null.
     */
    public void addFirst(T item) {
        Node currentFirst = sentinel.next;
        Node newNode = new Node(item, sentinel, currentFirst);
        sentinel.next = newNode;
        currentFirst.pre = newNode;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque. You can assume that item is never null.
     */
    public void addLast(T item) {
        Node currentLast = sentinel.pre;
        Node newNode = new Node(item, currentLast, sentinel);
        sentinel.pre = newNode;
        currentLast.next = newNode;
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
        Node currentNode = sentinel.next;
        while (currentNode != sentinel) {
            System.out.print(currentNode.item + " ");
            currentNode = currentNode.next;
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
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        size -= 1;
        return firstItem;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T lastItem = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        size -= 1;
        return lastItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If
     * no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node current = sentinel.next;
        while (index > 0) {
            current = current.next;
            index -= 1;
        }
        return current.item;
    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return getWithStarterNode(index, sentinel.next);
    }

    /**
     * Gets the item at the given index starting from a given node.
     */
    private T getWithStarterNode(int index, Node node) {
        if (index == 0) {
            return node.item;
        }
        return getWithStarterNode(index - 1, node.next);
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
        if (castedO.size() != size) {
            return false;
        }
        Iterator<?> iterator = iterator();
        Iterator<?> oIterator = castedO.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().equals(oIterator.next())) {
                return false;
            }
        }
        return true;
    }
}
