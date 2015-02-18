package tree;

/**
 * BinaryNode.java
 * Created: 17 Feb 2015
 * @author	Tyler Parsons
 */
public class BinaryNode<T extends Comparable<T>>
		extends AbstractBinaryNode<T, BinaryNode<T>> {

	public BinaryNode(T key) {
		super(key);
	}

}
