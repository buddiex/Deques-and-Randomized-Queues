/**
 * 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author osayamen
 *
 */
public class Deque<Item> implements Iterable<Item> {
	private Node<Item> first; // beginning
	private Node<Item> last; // end
	private int n; // number of elements on queue

	// helper class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> previous;
	}

	/**
	 * Initializes an empty queue.
	 */
	public Deque() {
		first = null;
		last = null;
		n = 0;
	}

	/**
	 * Returns true if this queue is empty.
	 *
	 * @return {@code true} if this queue is empty; {@code false} otherwise
	 */
	public boolean isEmpty() {
		return first == null || last == null;
	}

	/**
	 * Returns the number of items in this queue.
	 *
	 * @return the number of items in this queue
	 */
	public int size() {
		if (isEmpty())
			return 0;
		return n;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.previous = null;
		if (isEmpty()) {
			first.next = null;
			last = first;
		} else {
			oldfirst.previous = first;
			first.next = oldfirst;
		}
		n++;
	}

	public void addLast(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			last.previous = null;
			first = last;
		} else {
			last.previous = oldlast;
			oldlast.next = last;
		}
		n++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		Node<Item> oldfirst = first;
		first = oldfirst.next;
		if (isEmpty())
			last = null;
		else
			first.previous = null;
		n--;
		return oldfirst.item;
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		Node<Item> oldlast = last;
		last = oldlast.previous;
		if (isEmpty())
			first = null;
		else
			last.next = null;
		n--;
		return oldlast.item;
	}

	/**
	 * Returns an iterator that iterates over the items in this queue in FIFO
	 * order.
	 *
	 * @return an iterator that iterates over the items in this queue in FIFO
	 *         order
	 */
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;

		public ListIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	/**
	 * Unit tests the {@code Queue} data type.
	 *
	 * @param args
	 *            the command-line arguments
	 */

	public static void main(String[] args) {
		Deque<Integer> dq = new Deque<Integer>();
		dq.addLast(7);
		dq.addLast(2);
		dq.addLast(6);
		dq.removeFirst();
		dq.removeLast();
		for (Integer i : dq) {
			System.out.println(i);
		}
	} // unit testing (optional)
}
