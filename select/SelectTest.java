/**
 * SearchTest.java
 * Created: 27 Jan 2015
 * @author	Tyler Parsons
 */
package select;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Tyler
 *
 */
public abstract class SelectTest {
	
	protected ArrayList<Integer> randomArrayList(int N, int max) {
		ArrayList<Integer> list = new ArrayList<Integer>(N);
		for (int i = 0; i < N; i++)
			list.add((int)(Math.random()*max));
		return list;
	}
	
	@Test
	public abstract void selectTest();
	
}
