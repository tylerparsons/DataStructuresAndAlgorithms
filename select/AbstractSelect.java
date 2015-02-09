package select;

import java.util.List;

/**
 * AbstractSelect.java
 * Created: 23 Jan 2015
 * @author Tyler Parsons
 */
public abstract class AbstractSelect<T extends Comparable<T>> {

	/**
	 * Standardizes access to select algorithms.
	 * 
	 * @param list	list of unsorted keys
	 * @param k		number from max to select
	 * @return		kth max T
	 * @throws IllegalArgumentException if list.size() < k
	 */
	public abstract T select(List<T> list, int k)
	throws IllegalArgumentException;
	
	/**
	 * 
	 * @param list
	 * @param k
	 */
	protected void throwIllegalArgumentException(List<T> list, int k) {
		
		if (list.size() < k)
			throw new IllegalArgumentException("list.size() < k");
		
	}
	
}
