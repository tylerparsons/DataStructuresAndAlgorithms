package utils;

public class MathUtils {

	/**
	 * Computes the integer midpoint of lo and hi.
	 * @param lo an index
	 * @param hi an index, greater than or equal to hi
	 * @return midpoint
	 */
	public static int getMiddleIndex(int lo, int hi) {
		return (hi-lo)/2 + lo;
	}
	
	public static void main(String[] args) {
		System.out.println(getMiddleIndex(0, 10));
	}

}
