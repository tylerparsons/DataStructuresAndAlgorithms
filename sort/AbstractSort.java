package sort;

import java.util.Arrays;
import java.util.List;

import common.AbstractAlgorithm;

/**
 * AbstractSort.java
 * Created: 2 Feb 2015
 * @author	Tyler Parsons
 */
public abstract class AbstractSort<T extends Comparable<T>> extends AbstractAlgorithm<T> {

	
/********************
 * Abstract Methods *
 ********************/
	
	/**
	 * Sorts array using implementation
	 * determined by subclass.
	 * 
	 * @param arr	array to sort
	 * @param lo	lower bound of array section to sort
	 * @param hi	upper bound of array section to sort
	 */
	public abstract void sort(T[] arr, int lo, int hi);

	
/*********
 * Utils *
 *********/
	
	/**
	 * Calls {@link #sort(Comparable[], int, int)}
	 * with the first and last index of {@code arr}.
	 * @param arr
	 */
	public void sort(T[] arr) {
		sort(arr, 0, arr.length-1);
	}
	
	/**
	 * Sorts the {@code list} using the given
	 * sorting engine, which sorts a {@code T[]}.
	 * 
	 * @param engine An {@link AbstractSort<T extends Comparable<T>>}
	 * @param list	 An {@link java.util.List<T>}
	 */
	@SuppressWarnings("unchecked")
	public void sortList(List<T> list) {
		T[] arr = (T[])list.toArray();
		sort(arr);
		list = Arrays.asList(arr);
		// Add 2N copy operations to nAssigns
		nAssigns += 2*list.size();
	}
	
}
