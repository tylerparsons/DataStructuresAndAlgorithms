/**
 * AbstractBucketSort.java
 * Created: 4 Feb 2015
 * @author	Tyler Parsons
 */
package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Tyler
 *
 */
public abstract class AbstractBucketSort<T extends Comparable<T>>
		extends	AbstractSort<T> 
		implements Iterable<T> {
	
	protected List<T>[] buckets;
	protected boolean sortBuckets;
	
	/**
	 * For subclasses to call.
	 * @param bucketSize number of buckets
	 * @param sort		 sort buckets
	 */
	@SuppressWarnings("unchecked")
	protected AbstractBucketSort(int bucketSize, boolean sort) {
		buckets = new List[bucketSize];
		sortBuckets = sort;
	}

	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(T[] arr, int lo, int hi) {

		// Put arr elements into buckets
		for(int i = lo; i <= hi; i++) {
			int bi = getBucketIndex(arr[i]);
			if(buckets[bi] == null) {
				buckets[bi] = new ArrayList<>();
				buckets[bi].add(arr[i]);
			}
			else {
				buckets[bi].add(arr[i]);
			}
		}

		// Sort buckets if requested
		if (sortBuckets) {
			for (List<T> bucket: buckets)
				Collections.sort(bucket);
		}
		
		// Repopulate arr with elements from buckets
		int i = lo;
		for (List<T> bucket: buckets)
			for (T elem: bucket)
				assign(arr, i++, elem);
		
	}
	
	/**
	 * Returns the bucket index for the given T.
	 * @param key
	 * @return
	 */
	protected abstract int getBucketIndex(T key);
	
}
