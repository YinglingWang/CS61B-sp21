package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    /* Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();

        assertTrue("A newly initialized arrayDeque should be empty", arrayDeque.isEmpty());
        arrayDeque.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, arrayDeque.size());
        assertFalse("arrayDeque should now contain 1 item", arrayDeque.isEmpty());

        arrayDeque.addLast("middle");
        assertEquals(2, arrayDeque.size());

        arrayDeque.addLast("back");
        assertEquals(3, arrayDeque.size());

        System.out.println("Printing out deque: ");
        arrayDeque.printDeque();
    }

    @Test
    /* Adds an item, then removes an item, and ensures that dll is empty afterward. */
    public void addRemoveTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        // should be empty
        assertTrue("arrayDeque should be empty upon initialization", arrayDeque.isEmpty());

        arrayDeque.addFirst(10);
        // should not be empty
        assertFalse("arrayDeque should contain 1 item", arrayDeque.isEmpty());

        arrayDeque.removeFirst();
        // should be empty
        assertTrue("arrayDeque should be empty after removal", arrayDeque.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(3);

        arrayDeque.removeLast();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.removeFirst();

        int size = arrayDeque.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create arrayDeque with different parameterized types*/
    public void multipleParamTest() {
        ArrayDeque<String>  arrayDeque1 = new ArrayDeque<>();
        ArrayDeque<Double>  arrayDeque2 = new ArrayDeque<>();
        ArrayDeque<Boolean> arrayDeque3 = new ArrayDeque<>();

        arrayDeque1.addFirst("string");
        arrayDeque2.addFirst(3.14159);
        arrayDeque3.addFirst(true);

        String s = arrayDeque1.removeFirst();
        double d = arrayDeque2.removeFirst();
        boolean b = arrayDeque3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, arrayDeque.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, arrayDeque.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            arrayDeque.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) arrayDeque.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) arrayDeque.removeLast(), 0.0);
        }
    }

    @Test
    /* Test whether the get method works. */
    public void getTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

        arrayDeque.addFirst(3);
        arrayDeque.addLast(4);

        assertEquals(Integer.valueOf(3), arrayDeque.get(0));
        assertEquals(Integer.valueOf(4), arrayDeque.get(1));
        assertNull(arrayDeque.get(2));
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> arrayDeque1 = new ArrayDeque<>();
        ArrayDeque<Integer> arrayDeque2 = new ArrayDeque<>();

        arrayDeque1.addFirst(2);
        arrayDeque1.addFirst(1);
        arrayDeque1.addLast(3);

        arrayDeque2.addFirst(2);
        arrayDeque2.addFirst(1);
        arrayDeque2.addLast(3);

        assertEquals(arrayDeque1, arrayDeque2);
    }
}
