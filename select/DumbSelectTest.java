/**
 * BinarySearchTest.java
 * Created: 28 Jan 2015
 * @author	Tyler Parsons
 */
package select;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

/**
 * @author Tyler
 *
 */
public class DumbSelectTest extends SelectTest {

	protected boolean print = false;
	
	@Test
	public void selectTest() {
		
		// Prepare test data
		ArrayList<Integer> list = randomArrayList(100, 500);
		Collections.sort(list);
		if (print) System.out.println(list);
		int k = (int)(Math.random()*list.size() + 1);
		if (print) System.out.println(k);
		int kthMax = list.get(list.size() - k);
		if (print) System.out.println(kthMax);
		Collections.shuffle(list);
		if (print) System.out.println(list);
		
		// Test DumbSelect
		DumbSelect<Integer> select = new DumbSelect<Integer>();
		assertEquals(new Integer(kthMax), select.select(list, k));
		
	}

}
