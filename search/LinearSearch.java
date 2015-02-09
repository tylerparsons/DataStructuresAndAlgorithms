package search;

import java.util.List;

public class LinearSearch<T extends Comparable<T>> implements ISearch<T> {

	@Override
	public int search(List<T> list, T key) {

		// Search linearly
		for (int i = 0; i < list.size(); i++) {
			if (key.compareTo(list.get(i)) == 0)
				return i;
		}
		// Search key not found
		return -1;
		
	}

}
