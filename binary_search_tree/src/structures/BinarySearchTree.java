package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class BinarySearchTree<T extends Comparable<T>> implements
BSTInterface<T> {
	protected BSTNode<T> root;
	private BSTNode<T> curr = root;
	int rightheight = 0;
	int leftheight = 0;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(curr);
	}

	private int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
			+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		// TODO
		if(t == null)
			throw new NullPointerException();
		if(get(t) == t)
			return true;
		return false;
	}

	public boolean remove(T t) {
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
			curr = root;
		}
		return result;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		// TODO
		if(t == null)
			throw new NullPointerException();
		if(size() == 0)
			return null;
		if(t.compareTo(curr.getData()) == 0){
			T data = curr.getData();
			curr = root;
			return data;
		}
		if(t.compareTo(curr.getData()) < 0){
			curr = curr.getLeft();
			return get(t);
		}
		if(t.compareTo(curr.getData()) > 0){
			curr = curr.getRight();
			return get(t);
		}
		return null;
	}

	public void add(T t) {
		root = addToSubtree(t, root);
		curr = root;
	}

	private BSTNode<T> addToSubtree(T t, BSTNode<T> node) {
		if (node == null) {
			return new BSTNode<T>(t, null, null);
		}
		if (t.compareTo(node.getData()) <= 0) {
			node.setLeft(addToSubtree(t, node.getLeft()));
		} else {
			node.setRight(addToSubtree(t, node.getRight()));
		}
		return node;
	}

	@Override
	public T getMinimum() {
		// TODO
		if(size() == 0)
			return null;
		if(size() == 1)
			return curr.getData();
		else if(curr.getLeft() == null){
			T data = curr.getData();
			curr = root;
			return data;
		}
		curr = curr.getLeft();
		return getMinimum();
	}

	@Override
	public T getMaximum() {
		// TODO
		if(size() == 0)
			return null;
		if(size() == 1)
			return curr.getData();
		else if(curr.getRight() == null){
			T data = curr.getData();
			curr = root;
			return data;
		}
		curr = curr.getRight();
		return getMaximum();
	}

	@Override
	public int height() {
		// TODO
		return heightHelper(curr);
	}
	private int heightHelper(BSTNode<T> node){
		if(node == null)
			return -1;
		BSTNode<T> left = node.getLeft();
		BSTNode<T> right = node.getRight();
		leftheight = 1+heightHelper(left);
		rightheight = 1+heightHelper(right);
		curr = root;
		return Math.max(leftheight, rightheight);
	}
	@Override
	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, curr);
		return queue.iterator();
	}

	private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}

	@Override
	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, curr);
		return queue.iterator();
	}

	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	@Override
	public Iterator<T> postorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, curr);
		return queue.iterator();
	}

	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}

	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODO
		if(other == null)
			throw new NullPointerException();
		if(size() != other.size())
			return false;
		BSTNode<T> oroot = other.getRoot();
		return equalsHelper(curr, oroot);
	}
	private boolean equalsHelper(BSTNode<T> node, BSTNode<T> anode){
		if(node == null && anode == null){
			return true;
		}
		if(!node.getData().equals(anode.getData()))
			return false;
		else
		{
			BSTNode<T> nleft = node.getLeft();
			BSTNode<T> nright = node.getRight();
			BSTNode<T> aleft = anode.getLeft();
			BSTNode<T> aright = anode.getRight();
			return (equalsHelper(nleft, aleft) && equalsHelper(nright, aright));
		}
	}
	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODO
		T[] array = (T[]) new Comparable[size()];
		if(other == null)
			throw new NullPointerException();
		T[] sarray = (T[]) new Comparable[other.size()];
		if(size() == 0 && other.size() == 0)
			return true;
		if(size() != other.size()){
			curr = root;
			return false;
		}
		for(int i=0; i<array.length; i++){
			array[i] = this.getMinimum();
			this.remove(getMinimum());
		}
		for(int j=0; j<sarray.length; j++){
			sarray[j] = other.getMinimum();
			other.remove(other.getMinimum());
		}
		return sameValuesHelper(array, sarray);
	}
	private boolean sameValuesHelper(T[] array, T[] sarray){
		boolean result = false;
		for(int i=0; i<Math.max(array.length, sarray.length); i++){
			if(array[i] == sarray[i])
				result = true;
		}
		return result;
	}

	@Override
	public boolean isBalanced() {
		// TODO
		int size = size();
		int height = height();
		if(size == 0)
			return true;
		if(size >= Math.pow(2, height) && size < Math.pow(2, height+1))
			return true;
		return false;
	}


	@Override
	public void balance() {
		// TODO
		T[] array = (T[]) new Comparable[size()];
		balanceHelper(array);
		insertTree(0, array.length-1, array);
		curr = root;
	}
	private void balanceHelper(T[] array){
		for(int i=0; i<array.length; i++){
			array[i] = this.getMinimum();
			this.remove(getMinimum());
		}
		Arrays.sort(array);
	}	
	private void insertTree (int lower, int	upper, T[] array)	{
		if (lower == upper)
			this.add(array[lower]);
		else	if (lower + 1 == upper)	{
			this.add(array[lower]);
			this.add(array[upper]);
		} else {
			int mid =	(lower + upper)/2;
			this.add(array[mid]);
			insertTree (lower, mid - 1, array);
			insertTree (mid + 1, upper, array);
		}
	}


	@Override
	public BSTNode<T> getRoot() {
		// DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// DO NOT MODIFY
		// see project description for explanation

		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}
}