package tree;

/**
 * BinarySearchTree.java
 * Created: 17 Feb 2015
 * @author	Tyler Parsons
 */
public class BinarySearchTree<T extends Comparable<T>>
		extends AbstractBinarySearchTree<T, BinaryNode<T>> {

	/* (non-Javadoc)
	 * @see tree.AbstractBinarySearchTree#createNode(java.lang.Comparable)
	 */
	@Override
	public BinaryNode<T> createNode(T key) {
		return new BinaryNode<T>(key);
	}

}
