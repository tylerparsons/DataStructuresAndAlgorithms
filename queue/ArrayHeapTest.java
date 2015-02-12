package queue;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * ArrayHeapTest.java
 * Created: 11 Feb 2015
 * @author	Tyler Parsons
 */
public class ArrayHeapTest {

	protected final int runs = 100;
	protected final int N = 100;
	
	@SuppressWarnings("unused")
	boolean print = runs <= 10 && N <= 30;
	
	@Test
	public void testArrayHeap() {
		
		Integer[] arr;
		
		for (int i=0; i<runs; i++) {
			
			// Initialization
			arr = randomIntegerArray(N, N);
			if(print) System.out.println(Arrays.toString(arr));
			int offset = (int)(Math.random()*N);
			int max = (int)(Math.random()*(N-offset));
			ArrayHeap<Integer> heap = new ArrayHeap<Integer>(arr, N-offset-max, offset);
			if(print) System.out.println(Arrays.toString(arr));
			
			// Tests
			testIsHeap(heap);
			testRemoveMax(heap, arr, offset);
			testAdd(heap, N, offset);
		}
		
	}
	
	protected void testIsHeap(ArrayHeap<Integer> heap) {
		assertTrue(heap.isHeap());
	}
	
	protected void testRemoveMax(ArrayHeap<Integer> heap, Integer[] arr, int offset) {
		
		if(print) {
			System.out.println(Arrays.toString(arr));
			char[] spaces = new char[arr.length*3];
			// Add pointers to first and last heap elements
			spaces[1+offset*3] = spaces[1+heap.size()*3] = '^';
			System.out.println(new String(spaces).replace('\0', ' '));
		}
		
		int max = heap.removeMax();
		while(heap.size() > 0) {
			
			if(print) {
				System.out.println(Arrays.toString(arr));
				char[] spaces = new char[arr.length*3];
				// Add pointers to first and last heap elements
				spaces[1+offset*3] = spaces[1+heap.size()*3] = '^';
				System.out.println(new String(spaces).replace('\0', ' '));
			}
			
			int tmp = heap.removeMax();
			assertTrue(max >= tmp);
			max = tmp;
		}
	}
	
	protected void testAdd(ArrayHeap<Integer> heap, int N, int offset) {
		
		for (int i = heap.size()+offset; i < N; i++) {
			heap.add((int)(Math.random()*N));
			assertTrue(heap.isHeap());
		}
		
	}
	
	protected Integer[] randomIntegerArray(int N, int max) {
		Integer[] arr = new Integer[N];
		for (int i = 0; i < N; i++)
			arr[i] = (int)(Math.random()*max);
		return arr;
	}
	
}
