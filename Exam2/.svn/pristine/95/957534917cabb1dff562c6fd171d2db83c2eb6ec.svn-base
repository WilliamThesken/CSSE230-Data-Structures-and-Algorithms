package bst;

//Name: William Thesken

/**
 *
 * Exam 2. Tree methods.
 * 
 * @author
 */

/*
 * TODO: Directions: Implement the method below. See the paper for details.
 */
public class BinarySearchTree {

	BinaryNode root;

	// The -17 is arbitrary -any int would be fine since we never refer to it.
	final BinaryNode NULL_NODE = new BinaryNode(-17); 

	public BinarySearchTree() {
		root = NULL_NODE;
	}
	
	//Wrapper class to keep track if the item was replaced by its subtree
	public class LeftReplaceBool 
	{
		public boolean replaced; //boolean will switch if replaced but instantiated in the constructor as false
		public LeftReplaceBool()
		{
			replaced = false;
		}
		
	}
	
	//Wrapper class to keep track of the current maximum difference
	public class MaxDiffWrap
	{
		public int maxDifference; 
		public MaxDiffWrap()
		{
			maxDifference = 0; //default maxDifference should be zero
		}
	}

	public int maxSiblingDiff() {
		// TODO Write this
		
		MaxDiffWrap wrapper = new MaxDiffWrap();
		this.root.maxSiblingDiff(wrapper); //searches the BinaryNodes in the BST and modifies the wrapper if need be
		return wrapper.maxDifference; //returns the maxDifference integer count of the wrapper
	}

	public boolean leftSubtreeReplace(int value) {
		// TODO Write this
		LeftReplaceBool boolWrapper = new LeftReplaceBool();
		this.root = this.root.leftSubtreeReplace(value, boolWrapper); //searches the tree for the item in the BST and modifies the wrapper if need be and modifies the tree if need be as well
		return boolWrapper.replaced; //returns true or false based which is dependent on if the wrapper was modified
	}

	public boolean hasNodeInRange(int lower, int upper) {
		// TODO: Write this
		return this.root.hasNodeInRange(lower, upper); //searches for a node within range and returns true if such a node is found
	}
	
	// The next methods are used by the unit tests
	public void insert(Integer e) {
		root = root.insert(e);
	}

	/**
	 * Feel free to call from tests to use to verify the shapes of your trees
	 * while debugging. Just remove the calls you are done so the output isn't
	 * cluttered.
	 * 
	 * @return A string showing a traversal of the nodes where children are
	 *         indented so that the structure of the tree can be determined.
	 * 
	 */
	public String toIndentString() {
		return root.toIndentString("");
	}

	@Override
	public String toString() {
		return root.toString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {
		public int data;
		public BinaryNode left;
		public BinaryNode right;

		// TODO: Add recursive methods here.
		
		
		// The rest of the methods are used by the unit tests and for debugging
		public BinaryNode(int element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}
		
		public boolean hasNodeInRange(int lower, int upper) {
			// TODO Auto-generated method stub
			if(this == NULL_NODE) //if it is a NULL_NODE it means it has hit the bottom of the tree and must therefore return false
			{
				return false;
			}
			if(this.data >= lower && this.data <= upper) //if the data is greater than or equal to the lower bound integer and less than or equal to the upper bound integer it is within range and you can return true
			{
				return true;
			}
			
			//this check, using the lower bound and this node's data, below allows us to go O(Height(T))
			if(lower > this.data) //if the lower bound is greater than our current node recurse right and return if there is a node within range in the right subtree
			{
				return this.right.hasNodeInRange(lower, upper);
			}
					
			return this.left.hasNodeInRange(lower, upper);  //this return is called if the lower bound is less than the current data and checks if there is a node within range of the left subtree
		}

		//leftSubtreeReplace takes in two inputs a value and a wrapper
		public BinaryNode leftSubtreeReplace(int value, LeftReplaceBool boolWrapper) {
			// TODO Auto-generated method stub
			if(this==NULL_NODE)
			{
				return this;
			}
			
			//if this node has the same value as your target then return this.left to set this as the left subtree
			if(this.data==value)
			{
				boolWrapper.replaced = true;
				return this.left;
			}
			
			//if the value is bigger than the current data then recurse right
			if(value > this.data)
			{
				this.right = this.right.leftSubtreeReplace(value, boolWrapper);
			}
			
			//otherwise go down the left subtree since it is less than the current node
			else
			{
				this.left = this.left.leftSubtreeReplace(value, boolWrapper); 
			}
			
			
			return this;
		}

		//returns true if the the node is a full node
		public boolean isFullNode()
		{
			if(this.left!=NULL_NODE&&this.right!=NULL_NODE) //if both the left and right node are not the NULL_NODE than return true
			{
				return true;
			}
			
			return false; //otherwise it is not a full node so return false
		}

		//maxSiblingDiff within the BinaryNode class is a void method that just searches and modifies our maximum difference wrapper
		public void maxSiblingDiff(MaxDiffWrap wrapper) {
			// TODO Auto-generated method stub
			if(this==NULL_NODE) //breaks the recursion if NULL_NODE cause it can't search for any more max differences here
			{
				return;
			}
			
			//uses the isFullNode() to check if the node is a full node then calculate the difference of the right and left nodes of the current node to find the current difference
			if(this.isFullNode())
			{
				int currentDifference = this.right.data - this.left.data;
				if(currentDifference>wrapper.maxDifference) //if the current difference is greater than the maximum difference stored within the wrapper then set the max difference in the wrapper to the current difference
				{
					wrapper.maxDifference = currentDifference;
				}
			}
			
			//Since you have to go through the whole tree you have to recurse on the left and right subtrees
			this.left.maxSiblingDiff(wrapper);
			this.right.maxSiblingDiff(wrapper);
		}

		public BinaryNode insert(int e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e < data) {
				left = left.insert(e);
			} else if (e > data) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + this.data + right.toString();
		}

		public String toIndentString(String indent) {
			if (this == NULL_NODE) {
				return indent + "NULL\n";
			}
			String myInfo = indent + String.format("%c\n", this.data);
			return myInfo + this.left.toIndentString(indent + "  ") + this.right.toIndentString(indent + "  ");
		}
	}
}