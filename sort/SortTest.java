package sort;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;


public class SortTest
{
		
	boolean print = true;
	
	@Test
//	@Ignore
	public void testAccuracy() {
		
		final int runs = 1;
		final int N = 10;
		
		for (AbstractSort<Integer> engine: getEngines())
			testAccuracy(runs, N, engine);
		
	}
	
	@SuppressWarnings("unchecked")
	AbstractSort<Integer>[] getEngines() {
		
		return new AbstractSort[] {
//			new InsertionSort<Integer>(),
//			new ShellSort<Integer>(),
//			new ShellSortHibbard<Integer>(),
			new MergeSort<Integer>()
		};
		
	}
	
	void testAccuracy(final int iterations, final int N, AbstractSort<Integer> engine) {
		
		Integer[] original, sorted;
		
		for (int i=0; i<iterations; i++) {
			
			original = randomIntegerArray(N, N);
			sorted = Arrays.copyOf(original, N);
			
			if(print) System.out.println(Arrays.toString(original));
			engine.sort(original, 0, N-1);
			Arrays.sort(sorted);
			if(print) System.out.println(Arrays.toString(original));
			if(print) System.out.println(Arrays.toString(sorted));
		
			assertTrue(arraysEqual(original, sorted));
		}
	}
	
	boolean arraysEqual(Integer[] a1, Integer[] a2) {
		for (int i = 0; i < a1.length; i++)
			if (a1[i].compareTo(a2[i]) != 0)
				return false;
		return true;
	}
	
	Integer[] randomIntegerArray(int N, int max) {
		Integer[] arr = new Integer[N];
		for (int i = 0; i < N; i++)
			arr[i] = (int)(Math.random()*max);
		return arr;
	}
	
	@Test
	@Ignore
	@SuppressWarnings("unchecked")
	public void compareSpeeds() {
		
		int N = 100000;
		Integer[] arr = randomIntegerArray(N, N);
		
		for (AbstractSort<Integer> engine: getEngines()) {
			long start = System.currentTimeMillis();
			engine.sort(arr);
			long end = System.currentTimeMillis();
			System.out.println(engine.getClass().getName().split("\\.")[1]+": "+(end-start));
		}
	}
	
	@Test
	@Ignore
	@SuppressWarnings("unchecked")
	public void countOperations() {
		
		int N = 100000;
		Integer[] arr = randomIntegerArray(N, N);
		
		for (AbstractSort<Integer> engine: getEngines()) {
			engine.sort(arr);
			System.out.println(engine.getClass().getName().split("\\.")[1]+" nAssigns: "+engine.nAssigns);
			System.out.println(engine.getClass().getName().split("\\.")[1]+" nCompares: "+engine.nCompares);
		}
		
	}
	
	
	
}
