package search;

import java.util.List;

import utils.MathUtils;

/**
 * BinarySearch.java
 * Created: 27 Jan 2015
 * @author	Tyler Parsons
 */
public class BinarySearch<T extends Comparable<T>> implements ISearch<T> {

	/**
	 * Assumes list is sorted in ascending order.
	 */
	@Override
	public int search(List<T> list, T key) {
		// Delegate to recursive method
		return search(list, key, 0, list.size()-1);
	}

	/**
	 * Recursive implementation of binary search.
	 * 
	 * @param list	list containing any number of keys.
	 * @param key	search key
	 * @param lo	lowest index
	 * @param hi	highest index
	 * @return	
	 */
	private int search(List<T> list, T key, int lo, int hi) {
		
		if (lo > hi) {
			// 
			return -1;
		}
		
		int mid = MathUtils.getMiddleIndex(lo, hi);
		int cmp = key.compareTo(list.get(mid));
		
		if (cmp < 0)
			return search(list, key, lo, mid-1);
		else if (cmp > 0)
			return search(list, key, mid+1, hi);
		else
			return mid;	// search key found!
	}
	
	

}
