/**
 * ProbabilityBucketSort.java
 * Created: 10 Feb 2015
 * @author	Tyler Parsons
 */
package sort;

/**
 * @author Tyler
 *
 */
public class ProbabilityBucketSort extends AbstractBucketSort<Double> {

	protected int AVG_BUCKET_SIZE = 10;
	
	/**
	 * Specifies that buckets should be sorted.
	 */
	public ProbabilityBucketSort() {
		super(true);
	}
	
	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(Double[] arr, int lo, int hi) {
		
		// Initialize buckets, divisor
		initBuckets(arr.length/AVG_BUCKET_SIZE);
		
		// Sort
		super.sort(arr, lo, hi);
				
	}
	
	/* (non-Javadoc)
	 * @see sort.AbstractBucketSort#getBucketIndex(java.lang.Comparable)
	 */
	@Override
	protected int getBucketIndex(Double key) {
		// Assumes 0 <= key < 1
		return (int)(buckets.length*key);
	}
	
/*********
 * Utils *
 *********/
	
	/**
	 * Sets the parameter which determines the
	 * total number of buckets. The given size
	 * is only the average size of each bucket.
	 * 
	 * @param size	estimated average number
	 * 				of elements in bucket
	 */
	public void setAvgBucketSize(int size) {
		AVG_BUCKET_SIZE = size;
	}

}
