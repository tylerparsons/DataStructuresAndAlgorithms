package sort;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * ProbabilityBucketSortTest.java
 * Created: 11 Feb 2015
 * @author	Tyler Parsons
 */
public class ProbabilityBucketSortTest extends SortTest {


/*********
 * Tests *
 *********/
	
	@Test
//	@Ignore
	public void testProbabilityBucketSort() {
		
		test(runs, N, new ProbabilityBucketSort());
		
	}
	
	protected void test(int runs, int N, AbstractSort<Double> engine) {
		
		Double[] original, sorted;
		
		for (int i=0; i<runs; i++) {
			
			original = randomDoubleArray(N, 1);
			sorted = Arrays.copyOf(original, N);
			
			if(print) System.out.println(Arrays.toString(original));
			engine.sort(original);
			Arrays.sort(sorted);
			if(print) System.out.println(Arrays.toString(original));
			if(print) System.out.println(Arrays.toString(sorted));
		
			assertTrue(arraysEqual(original, sorted));
		}
	}
	
	
/*********
 * Utils *
 *********/
	
	protected Double[] randomDoubleArray(int N, double max) {
		Double[] arr = new Double[N];
		for (int i = 0; i < N; i++)
			arr[i] = Math.random()*max;
		return arr;
	}
	
}
