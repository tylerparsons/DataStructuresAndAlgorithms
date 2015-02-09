/**
 * InsertionSort.java
 * Created: 3 Feb 2015
 * @author	Tyler Parsons
 */
package sort;

/**
 * @author Tyler
 *
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSort<T> {

	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(T[] arr, int lo, int hi) {
		
		for (int i = lo; i < hi; i++) {
			
			// Store insertion value
			int j = i+1;
			T insert = arr[i+1];
			// Make room for insertion
			while (j > lo && compareTo(arr[j-1], insert) > 0) {
				assign(arr, j, arr[j-1]);
				j--;
			}
			// Insert at j
			assign(arr, j, insert);
			
		}
		
	}

}
