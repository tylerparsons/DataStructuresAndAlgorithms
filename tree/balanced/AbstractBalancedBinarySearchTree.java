package tree.balanced;

import tree.AbstractBinaryNode;
import tree.AbstractBinarySearchTree;

/**
 * AbstractBalancedBinarySearchTree.java
 * Created: 17 Feb 2015
 * @author	Tyler Parsons
 */
public abstract class AbstractBalancedBinarySearchTree
		<T extends Comparable<T>, N extends AbstractBinaryNode<T, N>>
		extends AbstractBinarySearchTree<T, N> {

	/**
	 * @param n node to balance
	 */
	public abstract void balance(N n);
	
	/**
	 * Adds node and calls balance.
	 * @param key key of node to add
	 * @return Added node
	 */
	public N add(T key) {
		N added = super.add(key);
		balance(added);
		return added;
	}
	
	/**
	 * Remove node and calls balance.
	 * @param key key of node to remove
	 * @return 	Removed node or null if node
	 * 			did not exist
	 */
	public N remove(T key) {
		N n = findNode(root, key);
		if (n != null) {
			// Perform removal
			// Lowest updated subtree root
			N lusr = n.hasBothChildren() ? removeHibbard(n) : removeSelf(n);
			// Balance only necessary for lusr not null or n
			if (lusr != null && lusr != n) balance(lusr);
			
		}
		return n;
	}
	
/**********
 * Rotate *
 **********/
	
	/**
	 * Rotates node to the left.
	 * @param n node
	 */
	public void rotateLeft(N n) {
		
		// Grab right child
		N rc = n.getRightChild();
		
		if (rc != null) {
			// Steal left child of rc
			n.setRightChild(rc.getLeftChild());
			
			// Update parent links
			if (n.hasParent())
				n.getParent().replaceChild(n, rc);
			else	// n is the root
				setRoot(rc);
			
			// Replace left child of rc with n
			rc.setLeftChild(n);
		}
		
	}
	
	/**
	 * Rotates node to the right.
	 * @param n node
	 */
	public void rotateRight(N n) {
		// Grab left child
		N lc = n.getLeftChild();
		
		if (lc != null) {
			// Steal right child of lc
			n.setLeftChild(lc.getRightChild());
			
			// Update parent links
			if (n.hasParent())
				n.getParent().replaceChild(n, lc);
			else	// n is the root
				setRoot(lc);
			
			// Replace Right child of rc with n
			lc.setRightChild(n);
		}
	}


}
