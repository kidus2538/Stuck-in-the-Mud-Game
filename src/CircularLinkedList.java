
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CircularLinkedList class represents a circular linked list with various operations.
 *
 * @param <E> type of elements stored in list
 */
public class CircularLinkedList<E> implements CircularLinkedListInterface<E> {

    /**
     * Reference to the front (first) node in the list.
     */
    private Node<E> front;

    /**
     * Reference to the back (last) node in the list.
     */
    private Node<E> back;

    /**
     * Size count of the list.
     */
    private int size;

    /**
     * Constructs an empty CircularLinkedList.
     */
    public CircularLinkedList () {

    }

    /**
     * Constructs a CircularLinkedList with the specified elements.
     *
     * @param data elements to be added to the list
     */
    @SafeVarargs
    public CircularLinkedList (E... data) {
        for (E element : data) {
            add(element);
        }
    }

    /**
     * Retrieves a count of elements being maintained by the list.
     *
     * @return the size of the list (count of elements)
     */
    @Override
    public int getSize() {

        return size;
    }

    /**
     * Retrieves the data at the specified position in the list
     *
     * @param position 0-based index for the list; must be in the range 0 to size - 1
     * @return the data in the specified position in the list
     */
    @Override
    public E get(int position) {
        if (position < 0 || position >= getSize()) {
            throw new IndexOutOfBoundsException("position: " + position);
        }
        Node<E> current = front;

        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        return current.data;
    }

    /**
     * Adds a new node to the end of the list; by nature, this element's next will point to the first element
     *
     * @param value the element to add to the list
     */
    @Override
    public void add(E value) {
        Node<E> newNode =  new Node<>(value);

        if(front == null) {
            front = newNode;
            back = newNode;
            newNode.next = front;
        }
        else {
            back.next = newNode;
            back = newNode;
            back.next = front;
        }
        size++;
    }

    /**
     * Removes the specified item from the list, if it exists there.
     *
     * @param value the element to remove from the list
     * @return true, if the element was found and removed; false, if not found or list is empty
     */
    @Override
    public boolean remove(E value) {
        if (front == null || size == 0) {
            return false;
        }

        if (front.data.equals(value)) {
            front = front.next;
            back.next = front;
            if (--size == 0) {
                back = null;
            }
            return true;
        }

        Node<E> current = front;
        while (current.next != front && !current.next.data.equals(value)) {
            current = current.next;
        }

        if (current.next != front) {
            current.next = current.next.next;
            size--;
            return true;
        }
        return false;
    }

    /**
     * Removes the node at the specified position in the list
     *
     * @param position position in the list; must be in range 0 to size - 1
     */
    @Override
    public void remove(int position) {
        if (position < 0 || position >= getSize()) {
            throw new IndexOutOfBoundsException("position: " + position);
        }

        if (position == 0) {
            front = front.next;
            back.next = front;
            if (--size == 0) {
                back = null;
            }
            return;
        }

        Node<E> current = front;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    /**
     * Retrieves an iterator over the list's elements.  Do not do other list operations like add or remove
     * from within an iterator loop; the results are not guaranteed to function as you might expect
     *
     * @return a strongly typed iterator over elements in the list
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Private inner class representing an iterator for the CircularLinkedList.
     */
    private class LinkedListIterator implements Iterator<E> {

        /**
         * Reference to the current node in the iteration.
         */
        private Node<E> current = front;

        /**
         * Checks if there is another element in the iteration.
         *
         * @return true if there is another element, false if the list is empty
         */
        @Override
        public boolean hasNext() {

            return size > 0;
        }

        /**
         * Retrieves the next data in the iteration.
         *
         * @return the next data in the iteration
         * @throws NoSuchElementException if there are no more data in the list
         */
        @Override
        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException("No more data in the list");
            }

            E data = current.data;
            current = current.next;
            return data;
        }
    }


    /**
     * Private static inner class representing a node in the CircularLinkedList
     *
     * @param <T> the type of data stored in the node
     */
    private static class Node<T> {

        /**
         * Data stored in the node
         */
        public T data;

        /**
         * Reference to the next node in the list
         */
        public Node<T> next;

        /**
         * Constructs a new node with the given data.
         *
         * @param data the data to be stored in the node
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
