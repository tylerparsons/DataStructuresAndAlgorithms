/**
 * ShellSort.java
 * Created: 3 Feb 2015
 * @author	Tyler Parsons
 */
package sort;

/**
 * @author Tyler
 *
 */
public class ShellSort<T extends Comparable<T>> extends	AbstractSort<T> {

	/* (non-Javadoc)
	 * @see sort.AbstractSort#sort(java.lang.Comparable[], int, int)
	 */
	@Override
	public void sort(T[] arr, int lo, int hi) {
		
		// Find first Hibbard term
		int term = maxTerm(arr.length);
		
		// Perform Insertion sort at intervals of h
		while (term > 0) {
			
			// Determine base index from which to start comparison intervals
			for (int base = lo; base < (lo+term); base++) {
				
				// Insertion sort
				for (int i = base; i <= (hi-term); i += term) {
					
					// Find insertion 
					int j = i+term;
					T insert = arr[j];
					while (j > base && compareTo(arr[j-term], insert) > 0) {
						assign(arr, j, arr[j-term]);
						j -= term;
					}
					// Insert at j
					assign(arr, j, insert);
					
				}
				
			}
			
			term = prevTerm(term);			
		}
		
	}
	
	/**
	 * Computes the max sequence term allowed for
	 * a ShellSort on an array of size {@code n}.
	 * @param n array size
	 * @return	a term in the sequence
	 * 			less than {@code n}.
	 */
	protected int maxTerm(int n) {
		return (n%2 == 0) ? n/2 : n-1;
	}
	
	/**
	 * Computes the term below the given term
	 * in the sequence.
	 * @param term	a term in the sequence
	 * @return	term of k-1
	 */
	protected int prevTerm(int term) {
		return term/2;
	}

}
