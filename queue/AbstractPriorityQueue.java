package queue;

import java.util.NoSuchElementException;

/**
 * 
 * @author Tyler
 *
 * @param <T>
 */
public abstract class AbstractPriorityQueue<T extends Comparable<T>> {

/********************
 * Abstract Methods *
 ********************/
	
	/**
	 * @param key T corresponding to a priority
	 */
	public abstract void add(T key);
	/**
	 * @return highest priority key
	 * @throws NoSuchElementException for empty queue
	 */
	public abstract T removeMax() throws NoSuchElementException;
	/**
	 * @return size of list, dependent upon implementation
	 */
	public abstract int size();

	
/***********************
 * Implemented Methods *
 ***********************/
	/**
	 * Depends on implementation of size()
	 * @return true if size() returns 0
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Invokes {@link java.lang.Comparable#compareTo(Object)}
	 * with {@code from} on {@code to}.
	 * 
	 * @param from	some T
	 * @param to	some T
	 * @return		int representing comparison
	 * @see	java.lang.Comparable#compareTo(Object)
	 */
	protected int compareTo(T from, T to) {
		return from.compareTo(to);
	}
	
}
