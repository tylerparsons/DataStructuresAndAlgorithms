package sort;

/**
 * LSDRadixSort.java
 * Created: 10 Feb 2015
 * @author	Tyler Parsons
 */
public class LSDRadixSort extends AbstractBucketSort<Integer> {

	/**
	 * A multiple of 10 used to isolate digits
	 * of certain significance.
	 */
	protected int divisor;
	
	/**
	 * Specifies that buckets should be unsorted.
	 */
	public LSDRadixSort() {
		super(false);
	}
	
	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(Integer[] arr, int lo, int hi) {
		
		// Initialize buckets, divisor
		initBuckets(10);
		divisor = 1;
		
		// Find max
		int max = arr[lo];
		for (int i = lo + 1; i <= hi; i++) {
			if (arr[i] > max) max = arr[i];
		}
		
		// Perform bucket sort on each
		// digit in ascending significance.
		do {
			super.sort(arr, lo, hi);
			divisor *= 10;
		} while (max/divisor > 0);
				
	}
	
	/* (non-Javadoc)
	 * @see sort.AbstractBucketSort#getBucketIndex(java.lang.Comparable)
	 */
	@Override
	protected int getBucketIndex(Integer key) {
		return key/divisor % 10;
	}

}
