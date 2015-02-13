package queue;

import java.util.NoSuchElementException;

import common.AbstractAlgorithm;

/**
 * 
 * @author Tyler
 *
 * @param <T>
 */
public abstract class AbstractPriorityQueue<T extends Comparable<T>> extends AbstractAlgorithm<T> {

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
	
}
