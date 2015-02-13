package queue;

import java.util.NoSuchElementException;

/**
 * Heap.java
 * Created: 11 Feb 2015
 * @author	Tyler Parsons
 */
public class ArrayHeap<T extends Comparable<T>> extends AbstractPriorityQueue<T> {

	/**
	 * Array storing heap
	 */
	protected T[] arr;
	/**
	 * Current number of elements in heap.
	 */
	protected int N;
	/**
	 * Offset index at which to start heap.
	 */
	protected int d;
	
	/**
	 * Builds a heap out of the given array.
	 * Note that a is a reference variable,
	 * and this heap will reorder the given
	 * array.
	 * 
	 * @param a array, not necessarily a heap
	 */
	public ArrayHeap(T[] a) {
		this(a, a.length, 0);
	}
	
	/**
	 * Builds a heap out of the given array.
	 * Note that a is a reference variable,
	 * and this heap will reorder the given
	 * array.
	 * 
	 * @param a array, not necessarily a heap
	 * @param initialSize 	number of significant elements
	 * 					  	initially in heap (higher index
	 * 					 	elements will be ignored/overwritten.
	 * @param offset	index at which to start heap.
	 */
	public ArrayHeap(T[] a, int initialSize, int offset) {
		arr = a;
		N = initialSize;
		d = offset;
		buildHeap();
	}
	
/********************
 * Heap Maintenance *
 ********************/
	
	/* (non-Javadoc)
	 * @see queue.AbstractPriorityQueue#add(java.lang.Comparable)
	 */
	@Override
	public void add(T key) {
		// Add key at the end of heap.
		// Assumes the array is indexed there.
		assign(arr, N+d, key);
		// Swim until array is heap
		swim(d+N++);
	}

	/* (non-Javadoc)
	 * @see queue.AbstractPriorityQueue#removeMax()
	 */
	@Override
	public T removeMax() throws NoSuchElementException {
		// Ensure max exists
		if (N == 0) throw new NoSuchElementException("Heap empty");
		// Replace max with rightmost leaf
		T max = arr[d];
		swap(arr, d, (--N)+d);
		// Sink leaf
		sink(d);
		return max;
	}

	/* (non-Javadoc)
	 * @see queue.AbstractPriorityQueue#size()
	 */
	@Override
	public int size() {
		return N;
	}

	/**
	 * Swims the element at index i.
	 * @param i index
	 */
	public void swim(int i) {
		int p;
		while(i > 0 && compareTo(arr[p = getParentIndex(i)], arr[i]) < 0) {
			swap(arr, p, i);
			i = p;
		}
	}
	
	/**
	 * Sinks the element at index i.
	 * @param i index
	 */
	public void sink(int i) {
		
		// Sink along path containing max children
		int j = getLeftChildIndex(i);
		while (j < getRMLeafIndex()+1)	{
			
			// Select max child
			if (j < N-1+d && compareTo(arr[j], arr[j+1]) < 0) j++;
			// Compare with parent
			if (compareTo(arr[i], arr[j]) >= 0) break;
			// Swap parent with larger child
			swap(arr, i, j);
			// Examine max subtree
			i = j;
			j = getLeftChildIndex(i);
			
		}
		
	}
	
	/**
	 * Creates a heap out of the array if it is not already.
	 */
	public void buildHeap() {
		// Sink all non leaf nodes
		for (int i = findLowestNonLeaf(); i >= d; i--)
			sink(i);
	}
	
/*********
 * Utils *
 *********/
	
	/**
	 * Sorts the heap in ascending order.
	 * Note that {@link #isHeap()} will return
	 * false after this operation, and
	 * {@link #buildHeap()} must be called to
	 * reconstruct a heap.
	 */
	public void sort() {
		
		// Ensure N is unchanged
		int oldN = N;
		
		// Calls removeMax, which places max elements
		// at the back of the heap in ascending order
		for (int i = getRMLeafIndex(); i > d; i--) {
			removeMax();
		}
		
		N = oldN;
		
	}
	
	/**
	 * Computes index of rightmost leaf.
	 * @return N-1+d
	 */
	public int getRMLeafIndex() {
		return N-1+d;
	}
	
	/**
	 * Parent index for a d-indexed heap
	 * @param i index
	 * @return (i-d+1)/2 - 1 + d
	 */
	public int getParentIndex(int i) {
		return (i-d+1)/2 - 1 + d;
	}

	/**
	 * Left child index for a d-indexed heap
	 * @param i index
	 * @return 2*(i+1) - 1 + d
	 */
	public int getLeftChildIndex(int i) {
		return 2*(i-d+1) - 1 + d;
	}

	/**
	 * Right child index for a zero-indexed heap
	 * @param i index
	 * @return 2*(i+1)
	 */
	public int getRightChildIndex(int i) {
		return 2*(i-d+1) + d;
	}
	
	/**
	 * @return index of the lowest non leaf
	 */
	public int findLowestNonLeaf() {
		return getParentIndex(N-1+d);
	}
	
	/**
	 * Checks if the tree rooted at i is a heap.
	 * @return true if the tree is a heap, false otherwise.
	 */
	protected boolean isHeap(int i) {
		
		// Examine children
		int l = getLeftChildIndex(i);
		int r = getRightChildIndex(i);
		int imax = N+d-1;
		
		if(l > imax && r > imax){
			return true;
		}
		else if (l == imax) {
			return compareTo(arr[i], arr[l]) >= 0
					&& isHeap(l);
		}
		else {
			return compareTo(arr[i], arr[l]) >= 0
					&& compareTo(arr[i], arr[r]) >= 0
					&& isHeap(l) && isHeap(r); 
		}
		
	}
	
	/**
	 * @return true if the tree is a heap, false otherwise.
	 */
	public boolean isHeap() {
		return isHeap(d);
	}

}
