package search;

import java.util.List;

/**
 * ISearch.java
 * Created: 21 January 2015
 * @author Tyler Parsons
 */
public interface ISearch<T extends Comparable<T>> {

	/**
	 * Standardizes access to search algorithms.
	 * 
	 * @param list	list containing any number of keys.
	 * @param key	search key
	 * @return	index of the key in the list if exists;
	 * 			otherwise, a negative integer. 
	 */
	public int search(List<T> list, T key);
	
}
