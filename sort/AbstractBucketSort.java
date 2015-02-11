package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.IllegalStateException;

/**
 * AbstractBucketSort.java
 * Created: 4 Feb 2015
 * @author	Tyler Parsons
 */
public abstract class AbstractBucketSort<T extends Comparable<T>>
		extends	AbstractSort<T> {
	
	/**
	 * Array of list buckets.
	 */
	protected List<T>[] buckets;
	/**
	 * Flag indicating whether buckets should be sorted.
	 */
	protected boolean sortBuckets;
	
/******************
 * Initialization *
 ******************/
	
	/**
	 * Calls {@link #initBuckets(int)} passing {@code nBuckets}
	 * as an argument.
	 * @param nBuckets	number of buckets
	 * @param sort		Indicates whether buckets should be sorted.
	 */
	protected AbstractBucketSort(int nBuckets, boolean sort) {
		sortBuckets = sort;
		initBuckets(nBuckets);
	}
	
	/**
	 * Does not call {@link #initBuckets(int)}. Subclasses
	 * must call {@link #initBuckets(int)} so that {@link #buckets}
	 * is initialized and a {@link java.lang.IllegalStateException}
	 * is not thrown in {@link #sort(T[],int,int)}.
	 * 
	 * @param sort	Indicates whether buckets should be sorted.
	 */
	protected AbstractBucketSort(boolean sort) {
		sortBuckets = sort;
	}
	
	/**
	 * Initializes bucket list {@link #buckets}.
	 * @param nBuckets number of buckets
	 */
	@SuppressWarnings("unchecked")
	protected void initBuckets(int nBuckets) {
		buckets = new List[nBuckets];
	}

	
/***********
 * Sorting *
 ***********/

	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(T[] arr, int lo, int hi) {
		
		// Ensures init has been called.
		if (buckets == null) {
			throw new IllegalStateException(
				"Bucket list was never initialized. " +
				"Subclass must called init to initialize" +
				" bucket list."
			);
		}

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
			for (List<T> bucket: buckets) {
				// Check for null buckets
				if (bucket != null)
					Collections.sort(bucket);
			}
		}
		
		// Re-populate arr with elements from buckets
		int i = lo;
		for (List<T> bucket: buckets) {
			// Check for null buckets
			if (bucket != null) {
				// Empty bucket
				for (T elem: bucket) {
					assign(arr, i++, elem);
				}
				bucket.clear();
			}
		}
		
	}
	
	/**
	 * Returns the bucket index for the given T.
	 * @param key Some T
	 * @return the index of the bucket into which to insert T
	 */
	protected abstract int getBucketIndex(T key);
	
}
