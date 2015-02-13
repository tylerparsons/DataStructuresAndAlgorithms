package sort;

import queue.ArrayHeap;

/**
 * HeapSort.java
 * Created: 11 Feb 2015
 * @author	Tyler Parsons
 */
public class HeapSort<T extends Comparable<T>> extends AbstractSort<T> {
	
	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(T[] arr, int lo, int hi) {
		
		// Construct heap to store array
		ArrayHeap<T> heap = new ArrayHeap<T>(arr, (hi-lo)+1, lo);
		// At this point, the section of arr from lo to hi is a heap.
		
		// Delegate sorting to ArrayHeap
		heap.sort();
		
		// Update operation counts
		nAssigns = heap.getNAssigns();
		nCompares = heap.getNCompares();
		
	}

}
