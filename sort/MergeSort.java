/**
 * MergeSort.java
 * Created: 2 Feb 2015
 * @author	Tyler Parsons
 */
package sort;

import java.util.Arrays;

import utils.MathUtils;

/**
 * @author Tyler
 *
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {

	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(T[] arr, int lo, int hi) {

		// Array to which to copy values
		T[] aux = Arrays.copyOf(arr, arr.length);
		
		sort(arr, aux, lo, hi);
		
	}
	
	/**
	 * Performs a merge sort on {@code arr}.
	 * @param arr	array to sort
	 * @param aux	auxiliary array for merging
	 * @param lo	lowest index, inclusive
	 * @param hi	highest index, inclusive
	 */
	public void sort(T[] arr, T[] aux, int lo, int hi) {
		
		if (lo >= hi)
			return;
		
		int mid = MathUtils.getMiddleIndex(lo, hi);
		sort(arr, aux, lo, mid);
		sort(arr, aux, mid+1, hi);
		merge(arr, aux, lo, mid, hi);
		
	}
	
	/**
	 * Merges the sorted segments of {@code arr} from {@code lo} to
	 * {@code mid} and {@code mid+1} to {@code hi}.
	 * @param arr	array to sort
	 * @param aux	auxiliary array for merging
	 * @param lo	lowest index, inclusive
	 * @param mid	middle index, inclusive in bottom half
	 * @param hi	highest index, inclusive
	 */
	protected void merge(T[] arr, T[] aux, int lo, int mid, int hi) {
		
		copy(arr, aux, lo, hi);
//		System.out.println(Arrays.toString(arr));
		int k = lo, i = lo, j = mid+1;
		while (k <= hi) {
			if (i > mid)
				assign(arr, k++, aux[j++]);
			else if (j > hi)
				assign(arr, k++, aux[i++]);
			else if (compareTo(aux[i], aux[j]) < 0)
				assign(arr, k++, aux[i++]);
			else	// arr[i] >= arr[j]
				assign(arr, k++, aux[j++]);
		}
//		System.out.println(Arrays.toString(arr));
		
	}
	
	/**
	 * Copies {@code arr} to {@code aux}.
	 * @param arr
	 * @param aux
	 */
	protected void copy(T[] arr, T[] aux, int lo, int hi) {
		for (int i = lo; i <= hi; i++)
			assign(aux, i, arr[i]);
	}

}
