package sort;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;


public class SortTest
{
	
	protected final int runs = 10;
	protected final int N = 1000;
	
	@SuppressWarnings("unused")
	boolean print = runs <= 10 && N <= 30;
	
	@SuppressWarnings("unchecked")
	AbstractSort<Integer>[] getEngines() {
		
		return new AbstractSort[] {
			new InsertionSort<Integer>(),
			new ShellSort<Integer>(),
			new ShellSortHibbard<Integer>(),
			new MergeSort<Integer>(),
			new SelectionSort<Integer>(),
			new QuickSort<Integer>(),
			new IntegerBucketSort(),
			new LSDRadixSort()
		};
		
	}
	
/*********
 * Tests *
 *********/
	
	@Test
	@Ignore
	public void testAccuracy() {
				
		for (AbstractSort<Integer> engine: getEngines())
			testAccuracy(runs, N, engine);
		
	}
	
	protected void testAccuracy(final int runs, final int N, AbstractSort<Integer> engine) {
		
		Integer[] original, sorted;
		
		for (int i=0; i<runs; i++) {
			
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
	
	@Test
	@Ignore
	public void compareSpeeds() {
		
		Integer[] arr = randomIntegerArray(N, N);
		
		for (AbstractSort<Integer> engine: getEngines()) {
			long start = System.currentTimeMillis();
			engine.sort(arr);
			long end = System.currentTimeMillis();
			System.out.println(getName(engine)+" - time(ms): "+(end-start));
		}
	}
	
	@Test
	@Ignore
	public void countOperations() {
		
		Integer[] arr = randomIntegerArray(N, N);
		
		for (AbstractSort<Integer> engine: getEngines()) {
			engine.sort(arr);
			String name = getName(engine);
			System.out.println(name+" - nAssigns: "+engine.getNAssigns());
			System.out.println(name+" - nCompares: "+engine.getNCompares());
		}
		
	}
	
	
/*********
 * Utils *
 *********/
	
	protected <T extends Comparable<T>>boolean arraysEqual(T[] a1, T[] a2) {
		for (int i = 0; i < a1.length; i++)
			if (a1[i].compareTo(a2[i]) != 0)
				return false;
		return true;
	}
	
	protected Integer[] randomIntegerArray(int N, int max) {
		Integer[] arr = new Integer[N];
		for (int i = 0; i < N; i++)
			arr[i] = (int)(Math.random()*max);
		return arr;
	}
	
	protected String getName(AbstractSort<?> engine) {
		return engine.getClass().getName().split("\\.")[1];
	}	
	
}
