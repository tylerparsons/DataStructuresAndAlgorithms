/**
 * SmartSelect.java
 * Created: 28 Jan 2015
 * @author	Tyler Parsons
 */
package select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tyler
 *
 */
public class SmartSelect<T extends Comparable<T>> extends AbstractSelect<T> {

	@Override
	public T select(List<T> list, int k) throws IllegalArgumentException {
		// Construct ArrayList with initial size
		// such to minimize resizing operations.
		List<T> maxK = new ArrayList<T>((int)(1.5*k));
		for (T t: list)
			insert(maxK, t, k);
		// Return min in maxK, or kthMax element
		return maxK.get(0);
	}

	/**
	 * 
	 * @param maxK
	 * @param elem
	 * @param k
	 */
	private void insert(List<T> maxK, T t, int k) {
		
		int index = Collections.binarySearch(maxK, t);
		if (index < 0) {
			// Insert at value encoded in negative index
			index = -(index + 1);
		}
		// Maintain array in ascending order
		if (index < k) {
			maxK.add(index, t);
			if (maxK.size() > k)
				maxK.remove(0);
		}
		
	}
	
}
