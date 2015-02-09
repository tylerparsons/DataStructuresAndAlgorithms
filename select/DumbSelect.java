/**
 * DumbSelect.java
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
public class DumbSelect <T extends Comparable<T>>extends AbstractSelect<T> {

	@Override
	public T select(List<T> list, int k) throws IllegalArgumentException {

		// Ensure correct arguments
		throwIllegalArgumentException(list, k);
		// Copy list
		List<T> copy = new ArrayList<T>(list);
		
		// Find max key k times - O(Nk)
		T max = null;
		for (int i = 0; i < k; i++) {
			max = Collections.max(copy);
			copy.remove(max);
		}
		return max;
	}

}
