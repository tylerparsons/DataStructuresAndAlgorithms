/**
 * SelectionSort.java
 * Created: 2 Feb 2015
 * @author	Tyler Parsons
 */
package sort;

/**
 * @author Tyler
 *
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {

	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(T[] arr, int lo, int hi) {
		
		for (int i = lo; i < hi; i++) {
			
			// Select min node
			int min = i;
			for (int j = i+1; j <= hi; j++) {
				if (compareTo(arr[min], arr[j]) > 0)
					min = j;
			}
			
			// Swap min with i
			swap(arr, min, i);
			
		}
		
	}

}
