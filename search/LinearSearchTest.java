/**
 * LinearSearchTest.java
 * Created: 28 Jan 2015
 * @author	Tyler Parsons
 */
package search;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Tyler
 *
 */
public class LinearSearchTest extends SearchTest {
	
	@Test
	public void searchTest() {
		
		// Prepare test data
		ArrayList<Integer> list = randomArrayList(100, 500);
		int index = (int)(Math.random()*100);
		int search = list.get(index);
		
		// Test BinarySearch
		LinearSearch<Integer> linSearch = new LinearSearch<Integer>();		
		assertEquals(index, linSearch.search(list, search));
		
	}
	
}
