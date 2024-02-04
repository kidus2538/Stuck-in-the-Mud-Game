/**
 * JUnit test class for the CircularLinkedList implementation.
 */

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;


/**
 * CircularLinkedListTest class with methods to test CircularLinkedList class.
 *
 */
public class CircularLinkedListTest {

    /**
     * Tests the constructor with empty parameters.
     */
    @Test
    public void testConstructorNoParameters() {
        CircularLinkedList<Integer> linkedList = new CircularLinkedList<>();
        assertEquals(0, linkedList.getSize());
    }

    /**
     * Tests the constructor with parameters.
     */
    @Test
    public void testConstructorWithParameters() {
        CircularLinkedList<String> linkedList = new CircularLinkedList<>("Data 01", "Data 02", "Data 03");
        assertEquals(3, linkedList.getSize());

        linkedList = new CircularLinkedList<>("Data 01");
        assertEquals(1, linkedList.getSize());
    }

    /**
     * Tests the getSize() method of the CircularLinkedList class.
     */
    @Test
    public void testGetSize() {
        CircularLinkedList<Double> linkedList = new CircularLinkedList<>(1.1, -0.3, 11.12, 100.032, 0.0023);
        assertEquals(5, linkedList.getSize());
    }

    /**
     * Tests the get(int) method of the CircularLinkedList class.
     */
    @Test
    public void testGet() {
        CircularLinkedList<String> linkedList = new CircularLinkedList<>("Java", "C++", "C#", "Python");
        assertEquals("C++", linkedList.get(1));
        assertEquals("Python", linkedList.get(3));
    }

    /**
     * Tests the add(Object) method of the CircularLinkedList class.
     */
    @Test
    public void testAdd() {
        CircularLinkedList<Integer> linkedList = new CircularLinkedList<>();
        linkedList.add(42);
        assertEquals(1, linkedList.getSize());
        linkedList.add(-987);
        assertEquals(2, linkedList.getSize());
        linkedList.add(0);
        assertEquals(3, linkedList.getSize());
    }

    /**
     * Tests the remove(Object) method.
     */
    @Test
    public void testRemoveByValue() {
        CircularLinkedList<String> linkedList = new CircularLinkedList<>("A", "B", "C", "D", "E");
        linkedList.remove("A");
        assertEquals(4, linkedList.getSize());
        linkedList.remove("B");
        assertEquals(3, linkedList.getSize());
        linkedList.remove("C");
        assertEquals(2, linkedList.getSize());
    }

    /**
     * Tests the remove(int) method.
     */
    @Test
    public void testRemoveByPosition() {
        CircularLinkedList<String> linkedList = new CircularLinkedList<>("Batman", "Superman",
                                                                          "Cat woman", "Thor", "Eagle man");
        linkedList.remove(2);
        assertEquals("Thor", linkedList.get(2));
        linkedList.remove(0);
        assertEquals("Superman", linkedList.get(0));
    }

    /**
     * Tests the iterator over the CircularLinkedList.
     */
    @Test
    public void testIterator() {
        CircularLinkedList<Integer> linkedList = new CircularLinkedList<>();
        linkedList.add(100);
        linkedList.add(200);
        linkedList.add(300);

        Iterator<Integer> iterator = linkedList.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(100, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(200, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(300, iterator.next());
    }

    /**
     * Tests the iterator on an empty CircularLinkedList.
     */
    @Test
    public void testIteratorEmptyList() {
        CircularLinkedList<Double> linkedList = new CircularLinkedList<>();
        assertFalse(linkedList.iterator().hasNext());
        assertThrows(NoSuchElementException.class, linkedList.iterator()::next);
    }
}