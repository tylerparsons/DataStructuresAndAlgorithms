/**
 * BinarySearchTest.java
 * Created: 28 Jan 2015
 * @author	Tyler Parsons
 */
package search;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

/**
 * @author Tyler
 *
 */
public class BinarySearchTest extends SearchTest {

	@Test
	public void searchTest() {
		
		// Prepare test data
		ArrayList<Integer> list = randomArrayList(100, 500);
		Collections.sort(list);
		int index = (int)(Math.random()*100);
		int search = list.get(index);
		
		// Test BinarySearch
		BinarySearch<Integer> binSearch = new BinarySearch<Integer>();
		// Contains value
		assertEquals(index, binSearch.search(list, search));
		// Does not contain value
		assertEquals(-1, binSearch.search(list, 501));
		
	}

}
