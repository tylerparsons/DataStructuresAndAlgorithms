/**
 * QuickSort.java
 * Created: 2 Feb 2015
 * @author	Tyler Parsons
 */
package sort;

/**
 * @author Tyler
 *
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> {

	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(T[] arr, int lo, int hi) {
		
		if (lo >= hi)
			return;
		
		// Determine partition location
		int part = partition(arr, lo, hi);
		// Sort array recursively
		sort(arr, lo, part-1);
		sort(arr, part+1, hi);
		
	}
	
	/**
	 * Partitions the array about a pivot value
	 * such that all values with indices lower
	 * than the pivot are less than the pivot and
	 * all values with indices greater than the
	 * pivot are greater than the pivot.
	 *  
	 * @param arr	array to sort
	 * @param lo	lower bound of partition
	 * @param hi	upper bound of partition
	 * @return Index of pivot value
	 */
	public int partition(T[] arr, int lo, int hi) {
		
		// Pivot location
		T pivot = arr[hi];
		
		int i = lo-1;
		int j = hi;
		
		while (j >= i) {
			
			// Step i, j until values are larger, smaller than pivot
			while (--j >= lo && compareTo(arr[j], pivot) >= 0);
			while (++i <= hi-1 && compareTo(arr[i], pivot) <= 0);
			// Swap if i, j have not crossed
			if (i < j) swap(arr, i, j);
			
		}
		
		// Swap value at left pointer (greater than pivot) with pivot
		swap(arr, i, hi);
		
		// Return pivot location
		return i;
		
	}

}
