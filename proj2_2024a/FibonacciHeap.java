import java.awt.geom.NoninvertibleTransformException;

/**
 * FibonacciHeap
 *
 * An implementation of Fibonacci heap over positive integers.
 *
 */

public class FibonacciHeap
{
	public HeapNode min;
	public HeapNode first;
	public int size;
	public int numTrees;
	/**
	 *
	 * Constructor to initialize an empty heap.
	 *
	 */
	public FibonacciHeap()
	{
		this.min = null;
		this.size = 0;
		this.numTrees = 0;
		// should be replaced by student code
	}

	/**
	 * 
	 * pre: key > 0
	 *
	 * Insert (key,info) into the heap and return the newly generated HeapNode.
	 *
	 */
	public HeapNode insert(int key, String info) 
	{
		HeapNode newNode = new HeapNode(key, info);
		/* the heap is empty */
		if (this.min == null) {
			this.min = newNode;
			newNode.next = newNode;
			newNode.prev = newNode;
		}

		/* the newNode is the new min */
		else if(key < this.min.key){
			HeapNode nextTemp = this.min;
			HeapNode prevTemp = this.min.prev;

			this.min = newNode;

			newNode.next = nextTemp;
			newNode.prev = prevTemp;

			nextTemp.prev = newNode;
			prevTemp.next = newNode;

		}
		else{
			HeapNode prevTemp = this.min.prev;
			this.min.prev = newNode;
			newNode.next = this.min;

			newNode.prev = prevTemp;
			prevTemp.next = newNode;

		}

		this.numTrees++;
		this.size++;
		return newNode;

	}

	/**
	 * 
	 * Return the minimal HeapNode, null if empty.
	 *
	 */
	public HeapNode findMin()
	{
		return this.min; // should be replaced by student code
	}

	/**
	 * 
	 * Delete the minimal item
	 *
	 */
	public void deleteMin()
	{
		HeapNode minNode = this.min;
		HeapNode minChild = this.min.child;

		// Updating pointers of the root level
		if(minChild!=null){
			minChild.parent = null; // Detach child from parent
			HeapNode minPrev = minNode.prev;
			HeapNode minNext = minNode.next;
			minPrev.next = minChild;
			minChild.prev.next = minNext;
			minNext.prev = minChild.prev;
			minChild.prev = minPrev;
			minChild = minChild.next;
		}
		minChild = minChild.next;

		/* removing parent pointer from each minNode child and updating numTrees */
		while (minChild != this.min.child)
		{
			minChild.parent = null; // Detach child from parent
			minChild = minChild.next;
			numTrees++;
		}

		/* find the new min in the heap */
		this.min = findNewMin(minChild);
		this.size--;
	}

	public HeapNode findNewMin(HeapNode minChild){
		HeapNode tempMin = minChild;
		HeapNode tempNode = minChild.next;

		while (tempNode != minChild){
			if (tempNode.key < tempMin.key){
				tempMin = tempNode;
			}
		}
		return tempMin;
	}
	/**
	 * 
	 * pre: 0<diff<x.key
	 * 
	 * Decrease the key of x by diff and fix the heap. 
	 *
	 */
	public void decreaseKey(HeapNode x, int diff) 
	{
		x.key = x.key - diff;
		/* heap rule is violated */
		while(x.key>x.parent.key){
			heapifyUp(x);
		}
		return; // should be replaced by student code
	}
	public void heapifyUp(HeapNode x){
		HeapNode parentNode = x.parent;
		HeapNode parentPrev = parentNode.prev;
		HeapNode parentNext = parentNode.next;
		HeapNode xChild = x.child;
		HeapNode xPrev = x.prev;
		HeapNode xNext = x.next;

		parentPrev.next = x;
		parentNext.prev = x;

		xPrev.next = parentNode;
		xNext.prev = parentNode;

		parentNode.next = xNext;
		parentNode.prev = xPrev;

		x.prev = parentPrev;
		x.next = parentNext;

		x.child = parentNode;
		parentNode.parent = x;

		parentNode.child = xChild;
		xChild.parent = parentNode;

		HeapNode newChildNode = parentNode.child;
		HeapNode tempNode = newChildNode.next;

		while(tempNode!=newChildNode){
			tempNode.parent = parentNode;
			tempNode = tempNode.next;
		}
	}
	/**
	 * 
	 * Delete the x from the heap.
	 *
	 */
	public void delete(HeapNode x) 
	{    
		return; // should be replaced by student code
	}


	/**
	 * 
	 * Return the total number of links.
	 * 
	 */
	public int totalLinks()
	{
		return 0; // should be replaced by student code
	}


	/**
	 * 
	 * Return the total number of cuts.
	 * 
	 */
	public int totalCuts()
	{
		return 0; // should be replaced by student code
	}


	/**
	 * 
	 * Meld the heap with heap2
	 *
	 */
	public void meld(FibonacciHeap heap2)
	{
		return; // should be replaced by student code   		
	}

	/**
	 * 
	 * Return the number of elements in the heap
	 *   
	 */
	public int size()
	{
		return this.size; // should be replaced by student code
	}


	/**
	 * 
	 * Return the number of trees in the heap.
	 * 
	 */
	public int numTrees()
	{
		return this.numTrees; // should be replaced by student code
	}

	/**
	 * Class implementing a node in a Fibonacci Heap.
	 *  
	 */
	public static class HeapNode{
		public int key;
		public String info;
		public HeapNode child;
		public HeapNode next;
		public HeapNode prev;
		public HeapNode parent;
		public int rank;
		public boolean mark;

		public HeapNode(int key,String info){
			this.key = key;
			this.info = info;
			this.child = null;
			this.next = null;
			this.prev = null;
			this.rank = 0;
			this.mark = false;
		}

	}
}
