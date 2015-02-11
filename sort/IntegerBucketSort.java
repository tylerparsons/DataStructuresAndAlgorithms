package sort;

/**
 * IntegerBucketSort.java
 * Created: 10 Feb 2015
 * @author	Tyler Parsons
 */
public class IntegerBucketSort extends AbstractBucketSort<Integer> {

	/**
	 * Subtracted from key to obtain the appropriate bucket index.
	 */
	protected int offset;
	
	/**
	 * Calls super class constructor indicating
	 * that buckets should be sorted.
	 */
	public IntegerBucketSort() {
		super(true);
	}
	
	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(Integer[] arr, int lo, int hi) {
		
		// Find max, min to determine bucket range
		int max = arr[lo], min = arr[lo];
		for (int i = lo + 1; i <= hi; i++) {
			if (arr[i] > max) max = arr[i];
			else if (arr[i] < min) min = arr[i];
		}
		
		// Initialize buckets, determine offset
		initBuckets(max - min + 1);
		offset = min;
		
		// Delegate sorting to super
		super.sort(arr, lo, hi);
				
	}

	/* (non-Javadoc)
	 * @see sort.AbstractBucketSort#getBucketIndex(java.lang.Comparable)
	 */
	@Override
	protected int getBucketIndex(Integer key) {
		return key - offset;
	}

}
