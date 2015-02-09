package sort;

/**
 * ShellSortHibbard.java
 * Created: 3 Feb 2015
 * @author	Tyler Parsons
 * <p>
 * 
 * A ShellSort Implementation which uses
 * the Hibbard sequence.
 */
public class ShellSortHibbard<T extends Comparable<T>> extends ShellSort<T> {

	@Override
	protected int maxTerm(int n) {
		int k = (int)(Math.log(n+1)/Math.log(2));
		int term = (int)Math.round(Math.pow(2, k)) - 1;
		return (term == n) ? prevTerm(term) : term;
	}

	@Override
	protected int prevTerm(int term) {
		return (term-1)/2;
	}

}
