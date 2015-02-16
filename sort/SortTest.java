package sort;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

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
//			new InsertionSort<Integer>(),
//			new ShellSort<Integer>(),
//			new ShellSortHibbard<Integer>(),
//			new MergeSort<Integer>(),
//			new SelectionSort<Integer>(),
			new QuickSort<Integer>(),
//			new IntegerBucketSort(),
//			new LSDRadixSort(),
			new HeapSort<Integer>(),
			new IntroSort<Integer>(),
			new IntroSort<Integer>(new ShellSort<Integer>())
		};
		
	}
	
/*********
 * Tests *
 *********/
	
	@Test
//	@Ignore
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
//	@Ignore
	public void compareSpeeds() {
		
		AbstractSort<Integer>[] engines = getEngines();
		
		int runs = 1000;
		int Ni = 100;
		int Nf = 1000;
		int dN = 100;
		
		// Create HashMaps to store total sort times for ran, asc, dsc
		HashMap<Integer, long[]> totalTimes_ran = new HashMap<Integer, long[]>();
		HashMap<Integer, long[]> totalTimes_asc = new HashMap<Integer, long[]>();
		HashMap<Integer, long[]> totalTimes_dsc = new HashMap<Integer, long[]>();
		// Initialize each N with arrays of zeros
		for (int N = Ni; N <= Nf; N+=dN) {
			long[] zeros = new long[engines.length];
			for(int z=0;z<zeros.length;z++)	zeros[z]=0;
			totalTimes_ran.put(N, Arrays.copyOf(zeros, zeros.length));
			totalTimes_asc.put(N, Arrays.copyOf(zeros, zeros.length));
			totalTimes_dsc.put(N, Arrays.copyOf(zeros, zeros.length));
		}
		
		for (int r = 0; r < runs; r++) {
			for (int N = Ni; N <= Nf; N+=dN) {
				// Record times
				long[] ranTimes = compareSpeeds(randomIntegerArray(N, N), engines);
				long[] dscTimes = compareSpeeds(descendingIntegerArray(N, N), engines);
				long[] ascTimes = compareSpeeds(ascendingIntegerArray(N, N), engines);
				// Compute sums
				for(int i = 0; i < engines.length; i++) {
					totalTimes_ran.get(N)[i] += ranTimes[i];
					totalTimes_asc.get(N)[i] += ascTimes[i];
					totalTimes_dsc.get(N)[i] += dscTimes[i];
				}
			}
		}
		appendToCsv(totalTimes_ran, engines, "runTimes_rand.csv");
		appendToCsv(totalTimes_asc, engines, "runTimes_asc.csv");
		appendToCsv(totalTimes_dsc, engines, "runTimes_desc.csv");
	}
	
	public long[] compareSpeeds(Integer[] arr, AbstractSort<Integer>[] engines) {
		
		long[] times = new long[engines.length];
		
		for (int i = 0; i < engines.length; i++) {
			long start = System.currentTimeMillis();
			engines[i].sort(Arrays.copyOf(arr, arr.length));
			times[i] = System.currentTimeMillis() - start;
		}
		return times;
	}
	
	@Test
	@Ignore
	public void countOperations() {
		
		Integer[] arr = randomIntegerArray(N, N);
		
		for (AbstractSort<Integer> engine: getEngines()) {
			engine.sort(Arrays.copyOf(arr, arr.length));
			String name = getName(engine);
			System.out.println(name+" - nAssigns: "+engine.getNAssigns());
			System.out.println(name+" - nCompares: "+engine.getNCompares());
		}
		
	}
	
	
/*********
 * Utils *
 *********/
	
	protected <T extends Comparable<T>> boolean arraysEqual(T[] a1, T[] a2) {
		for (int i = 0; i < a1.length; i++)
			if (a1[i].compareTo(a2[i]) != 0)
				return false;
		return true;
	}
	
	protected <T> void reverse(T[] arr) {
		for (int i = 0; i < arr.length/2; i++) {
			T swap = arr[i];
			arr[i] = arr[arr.length-i-1];
			arr[arr.length-i-1] = swap;
		}
	}
	
	protected <T> void print(T[] arr) {
		System.out.println(Arrays.toString(arr));
	}
	
	protected Integer[] randomIntegerArray(int N, int max) {
		Integer[] arr = new Integer[N];
		for (int i = 0; i < N; i++)
			arr[i] = (int)(Math.random()*max);
		return arr;
	}
	
	protected Integer[] ascendingIntegerArray(int N, int max) {
		Integer[] arr = new Integer[N];
		for (int i = 0; i < N; i++)
			arr[i] = (int)(Math.random()*max);
		new IntroSort<Integer>().sort(arr);
		return arr;
	}
	
	protected Integer[] descendingIntegerArray(int N, int max) {
		Integer[] arr = new Integer[N];
		for (int i = 0; i < N; i++)
			arr[i] = (int)(Math.random()*max);
		new IntroSort<Integer>().sort(arr);
		reverse(arr);
		return arr;
	}
	
	protected void appendToCsv(
		HashMap<Integer, long[]> runTimes,
		AbstractSort<Integer>[] engines,
		String fileName
	) {
		
		File csv = new File("data\\"+fileName);
		try {
			
			FileWriter out = new FileWriter(csv, true);
			for (int N: runTimes.keySet()) {
			
				int engineIndex = 0;
				for (int j = 0; j < runTimes.get(N).length; j++) {
					out.append(N + ",");
					out.append(getName(engines[engineIndex++])+",");
					out.append(runTimes.get(N)[j] + "\n");
				}
			}
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	protected String getName(AbstractSort<?> engine) {
		return engine.getClass().getName().split("\\.")[1];
	}
	
}
