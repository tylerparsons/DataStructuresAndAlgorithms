/**
 * Copyright 2014, Emory University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tree;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 * @author Tyler Parsons
 */
public abstract class AbstractBinaryNode<T extends Comparable<T>,N extends AbstractBinaryNode<T,N>>
{
	/** Key */
	protected T k;
	/** Parent */
	protected N p;
	/** Left */
	protected N l;
	/** Right */
	protected N r;
	
	public AbstractBinaryNode(T key)
	{
		setKey(key);
	}
	

/***********
 * Getters *
 ***********/
	
	public T getKey()
	{
		return k;
	}
	
	public N getParent()
	{
		return p;
	}
	
	public N getLeftChild()
	{
		return l;
	}
	
	public N getRightChild()
	{
		return r;
	}
	
	public N getGrandParent()
	{
		return hasParent() ? p.getParent() : null;
	}
	
	@SuppressWarnings("unchecked")
	public N getSibling()
	{
		if (hasParent())
		{
			N parent = getParent();
			return parent.isLeftChild((N)this) ? parent.getRightChild() : parent.getLeftChild();
		}
		
		return null;
	}
	
	public N getUncle()
	{
		return hasParent() ? p.getSibling() : null;
	}

/***********
 * Setters *
 ***********/
	
	public void setKey(T key)
	{
		k = key;
	}
	
	public void setParent(N node)
	{
		p = node;
	}
	
	public void setLeftChild(N node)
	{
		replaceParent(node);
		l = node;
	}
	
	public void setRightChild(N node)
	{
		replaceParent(node);
		r = node;
	}
	
	@SuppressWarnings("unchecked")
	protected void replaceParent(N node)
	{
		if (node != null)
		{
			if (node.hasParent()) node.getParent().replaceChild(node, null);
			node.setParent((N)this);
		}
	}
	
	/** Replaces the old child with the new child if exists. */
	public void replaceChild(N oldChild, N newChild)
	{
		if      (isLeftChild (oldChild)) 	setLeftChild (newChild);
		else if (isRightChild(oldChild))	setRightChild(newChild);
	}

/**********
 * Checks *
 **********/
	
	public boolean hasParent()
	{
		return p != null;
	}
	
	public boolean hasLeftChild()
	{
		return l != null;
	}
	
	public boolean hasRightChild()
	{
		return r != null;
	}
	
	public boolean hasBothChildren()
	{
		return hasLeftChild() && hasRightChild();
	}
	
	/** @return {@code true} if the specific node is the left child of this node. */
	public boolean isLeftChild(N node)
	{
		return l == node;
	}
	
	/** @return {@code true} if the specific node is the right child of this node. */
	public boolean isRightChild(N node)
	{
		return r == node;
	}

/*********
 * Utils *
 *********/
	
	@Override
	public String toString()
	{
		return k + " -> (" + l +", " + r +")";
	}
}
