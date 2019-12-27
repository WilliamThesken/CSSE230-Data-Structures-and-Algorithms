import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 
 * Implementation of most of the Set interface operations using a Binary Search Tree
 *
 * @author Matt Boutell and <<< William Thesken >>>.
 * @param <T>
 */

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T>{
	private BinaryNode root;
	private int edits; //counts edits to the BinarySearchTree

	// Most of you will prefer to use NULL NODES once you see how to use them.
	 private final BinaryNode NULL_NODE = new BinaryNode();

	public BinarySearchTree() {
		root = NULL_NODE;
		edits = 0;
	}

	public BinaryNode getRoot()
	{
		return root;
	}
	// For manual tests only
	void setRoot(BinaryNode n) {
		this.root = n;
	}
	
	public boolean isEmpty()
	{
		return this.root== NULL_NODE;
	}
	
	public int size()
	{
		
		return this.root.size();
	}
	
	public ArrayList<T> toArrayList()
	{
		ArrayList<T> list = new ArrayList<T>();
		this.root.toArrayList(list);
		return list;
	}
	
	// Not private, since we need access for manual testing.
	class BinaryNode {
		private T data;
		private BinaryNode left;
		private BinaryNode right;
		
		//Just to build the null node
		public BinaryNode() {
			this.data = null;
			this.left = null;
			this.right = null;
		}

		public BinaryNode(T element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public void toArrayList(ArrayList<T> list)
		{
			if(this==NULL_NODE)
			{
				return;
			}
			//uses inorder recursion
			this.left.toArrayList(list);
			list.add(this.data);
			this.right.toArrayList(list);
			
		}
		public T getData() {
			return this.data;
		}

		public BinaryNode getLeft() {
			return this.left;
		}


		public BinaryNode getRight() {
			return this.right;
		}

		// For manual testing
		public void setLeft(BinaryNode left) {
			this.left = left;
		}
		
		public void setRight(BinaryNode right) {
			this.right = right;
		}
		
		public int size()
		{
		
			if(this == NULL_NODE)
			{
				return 0;
			}
			
			
			return 1 + this.left.size() + this.right.size();
		}

		public boolean containsNonBST(T i) {
			// TODO Auto-generated method stub
			if(this == NULL_NODE)
			{
				return false;
			}
			//since this is for non binary search trees this need to recurse left and right to find all the elements
			return this.data.equals(i) || this.left.containsNonBST(i) || this.right.containsNonBST(i);
		}

		public int height() {
			// TODO Auto-generated method stub
			
			
			
			if(this==NULL_NODE)
			{
				return -1; // other wise you're counting too much if you have this set as 0
			}
			
			int leftHeight = this.left.height();
			int rightHeight = this.right.height();
			
			if(leftHeight>rightHeight) //compare the two heights and add one to the greater one
			{
				return leftHeight + 1;
			}
			return rightHeight + 1;

		}

		public BinaryNode insert(T i,BoolModificationTracker tracker) {
			// TODO Auto-generated method stub
			if(this==NULL_NODE) //if node is null node the return a new binary node here
			{
				
				tracker.changed = true; //changes the status of the tracker to true since the BST was modified
				return new BinaryNode(i);
			}
			if(i.compareTo(this.data)>0) //recurse right if the item is bigger than the current node
			{
				this.right = this.right.insert(i,tracker);
				
			}
			else if(i.compareTo(this.data)<0) //recurse left if the item is smaller than the current node
			{
				this.left = this.left.insert(i,tracker);
			}
			return this; 
		}

		public BinarySearchTree<T>.BinaryNode remove(T i, BinarySearchTree<T>.BoolModificationTracker tracker) {
			// TODO Auto-generated method stub
			if(this==NULL_NODE) //if you hit the bottom just return this
			{
				return this;
			}
			if(this.data.equals(i))
			{
				tracker.changed = true; //let the wrapper know it was removed
				
				if(this.left==NULL_NODE&&this.right==NULL_NODE) //if both children are NULL_NODE then set the current node to the NULL_NODE 
				{
					return NULL_NODE;
				}
				if(this.left!=NULL_NODE&&this.right!=NULL_NODE) //if both children are not NULL_NODE then find the biggest in the left subtree and set the removed node to that value 
				{
					BinaryNode biggestLeft = this.left.getRightMost();
					this.data = biggestLeft.data;
					this.left = this.left.remove(biggestLeft.data, tracker); //then remove that biggest value in the left subtree
					return this;
				}
				if(this.left!=NULL_NODE) //if the left node isn't NULL_NODE return the remove Node's left child
				{
					return this.left; 
				}
				if(this.right!=NULL_NODE) //if the right node isn't NULL_NODE return the remove Node's right child
				{
					return this.right;
				}
				
				
			}
			if(i.compareTo(this.data)>0)
			{
				this.right = this.right.remove(i, tracker); //search for the element to the right if it is bigger than the current node
			}
			if(i.compareTo(this.data)<0)
			{
				
				this.left = this.left.remove(i, tracker); //search for the element to the left if it is smaller than the current node
			}
			
			
			return this;
		}

		private BinarySearchTree<T>.BinaryNode getRightMost() {
			// TODO Auto-generated method stub
			BinaryNode current = this;
			while(current.right!=NULL_NODE) // keep searching right for the largest item till it is found
			{
				current = current.right;
			}
			return current;
		}

		public boolean contains(T i) { //this is efficient because you can use the size of the tree to determine which way to go
			// TODO Auto-generated method stub
			if(this==NULL_NODE) //if it is not found return false
			{
				return false;
			}
			if(i.compareTo(this.data)>0) //go right if larger
			{
				return this.right.contains(i);
			}
			if(i.compareTo(this.data)<0) //go left if smaller
			{
				return this.left.contains(i);
			}
			
			return true;
		}
		
	}

	public boolean containsNonBST(T i) {
		// TODO Auto-generated method stub
		return this.root.containsNonBST(i);
	}

	

	// TODO: Implement your 3 iterator classes here, plus any other inner helper classes you'd like. 
	public Iterator<T> inefficientIterator() {
		// TODO Auto-generated method stub
		return new InefficientIterator();
	}
	
	public class InefficientIterator implements Iterator<T>
	{
		
		private ArrayList<T> list;
		private int index = -1;
		public InefficientIterator()
		{
			list = toArrayList();// TODO
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index + 1< list.size();
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			index++;
			return list.get(index);
		}
		
		
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new InorderIterator();
	}

	public class InorderIterator implements Iterator
	{
		Stack<BinaryNode> nodeStack = new Stack<BinaryNode>();
		private int iterEdits = edits; //tracks edits outside of iterator
		private boolean nextCalled = false; //tracks if next was called prior to remove
		private T lastNextData = null; //tracks the last data next returned
		
		public InorderIterator()
		{
			
			if(root!=NULL_NODE)
			{
				addLeftToStack(root); //if root isn't null node add all the lefts
			}
		}
		
		public void addLeftToStack(BinaryNode root)
		{
			BinaryNode current = root;
			while(current!=NULL_NODE) //goes left till it hits a null node
			{
				nodeStack.add(current);
				current = current.left;
				
			}
			
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			
			return !nodeStack.isEmpty();
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			if(!hasNext()) 
			{
				throw new NoSuchElementException();
			}
			
			if(iterEdits!=edits)
			{
				throw new ConcurrentModificationException();
			}
			
			BinaryNode current = nodeStack.pop(); //pop left node
			if(current.right!=NULL_NODE) //if left node has a right child push the right child and all of it's left children to the stack using the add Left to Stack
			{
				addLeftToStack(current.right); 
			}
			nextCalled = true;
			lastNextData = current.data;
			return current.data;
			
			
		}
		
		public void remove()
		{
			if(!nextCalled)
			{
				throw new IllegalStateException();
			}
			
			BinarySearchTree.this.remove(lastNextData);	
			nextCalled = false;
		}
		
	}
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return this.toArrayList().toArray();
	}
	
	public String toString()
	{
		if(this.isEmpty())
		{
			return "[]";
		}
		String array = "[";
		Object[] tempArray = this.toArray();
		for(Object i: tempArray) //goes through the tree array and turns it into a string
		{
			array = array + i + ", ";
		}
		return array.substring(0,array.length()-2) + "]";
	}

	public int height() {
		// TODO Auto-generated method stub
		if(this.root==NULL_NODE) //if tree is empty
		{
			return -1;
		}
		return this.root.height();
	}
	
	public class PreOrderIterator implements Iterator<T>
	{
		Stack<BinaryNode> nodeStack = new Stack<BinaryNode>();
		private int iterEdits = edits; //tracks edits outside of iterator on tree
		private boolean nextCalled = false; //tracks if next was called prior to a remove
		private T lastNextData = null; //stores last data returned by next

		
		public PreOrderIterator()
		{
			if(root!=NULL_NODE)
			{
				nodeStack.push(root); // push the root to the stack if not NULL_NODE
			}
			
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !nodeStack.isEmpty();
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			if(iterEdits!=edits)
			{
				throw new ConcurrentModificationException();
			}
			nextCalled = true;
			BinaryNode current = nodeStack.pop(); //pop the top and store it
			if(current.right!=NULL_NODE) //if it has a right child add it prior to checking for a left
			{
				nodeStack.push(current.right);
			}
			if(current.left!=NULL_NODE) //if it has a left child push it;
			{
				nodeStack.push(current.left);
				
			}
			lastNextData = current.data;
			return current.data;
		}
		
		@Override
		public void remove()
		{
			if(!nextCalled)
			{
				throw new IllegalStateException();
			}
			
			BinarySearchTree.this.remove(lastNextData);
			
			nextCalled = false;
		}
		
		
	}

	public Iterator preOrderIterator() {
		// TODO Auto-generated method stub
		return new PreOrderIterator();
	}

	public class BoolModificationTracker //Wrapper that tracks to see if the BST was changed during an insertion or removal call
	{
		public boolean changed;
		public BoolModificationTracker()
		{
			changed = false;
		}
	}
	public boolean insert(T i) {
		// TODO Auto-generated method stub
		if(i==null) //if i is null don't add and throw instead
		{
			throw new IllegalArgumentException();
		}
		BoolModificationTracker tracker = new BoolModificationTracker();
		this.root = this.root.insert(i,tracker); //passes in a tracker to insert
		this.edits = this.edits + 1;
		return tracker.changed; //return tracker status
	}

	public boolean remove(T i) {
		// TODO Auto-generated method stub
		if(i==null)
		{
			throw new IllegalArgumentException();
		}
		BoolModificationTracker tracker = new BoolModificationTracker();
		this.root = this.root.remove(i, tracker); //passes in a tracker to remove
		this.edits = this.edits + 1;
		return tracker.changed; //return tracker status
	}

	public boolean contains(T i) {
		// TODO Auto-generated method stub
		return this.root.contains(i); //runs contains method
	}

}
