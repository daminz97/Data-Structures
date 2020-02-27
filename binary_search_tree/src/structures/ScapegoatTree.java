package structures;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {

	private int upperBound;
	BSTNode<T> curr = root;
	
	/**
	 * Adds an element to the tree.
	 * 
	 * The modified tree must still obey the BST rule, though it might not be
	 * balanced.
	 * 
	 * In addition to obeying the BST rule, the resulting tree must also obey
	 * the scapegoat rule. 
	 * 
	 * This method must only perform rebalancing of subtrees when indicated
	 * by the scapegoat rule; do not unconditionally call balance() 
	 * after adding, or you will receive no credit. 
	 * See the project writeup for details.
	 * 
	 * @param element
	 * @throws NullPointerException if element is null
	 */
	@Override
	public void add(T element) {
		// TODO	
		if(element == null)
			throw new NullPointerException();
		root = addToSubtree(element, curr);
		curr = root;
		BSTNode<T> goat = findGoat(curr);
		if(curr.getData().compareTo(goat.getData()) <= 0){
			curr.setRight(goat.getRight());
			goat.getRight().setLeft(goat);
		}else{
			curr.setLeft(goat.getLeft());
			goat.getLeft().setRight(goat);
		}
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
	private BSTNode<T> findGoat(BSTNode<T> node){
		double result;
		if(node == null)
			return null;
		if(node.getLeft() == null){
			result = this.subtreeSize(node.getRight()) / this.subtreeSize(node);
			if(result > 2/3)
				return node;
			node = node.getRight();
			return findGoat(node);
		}
		else{
			result = this.subtreeSize(node.getLeft()) / this.subtreeSize(node);
			if(result > 2/3)
				return node;
			node = node.getLeft();
			return findGoat(node);
		}
	}
	private int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
			+ subtreeSize(node.getRight());
		}
	}
	/**
	 * Attempts to remove one copy of an element from the tree, returning true
	 * if and only if such a copy was found and removed.
	 * 
	 * The modified tree must still obey the BST rule, though it might not be
	 * balanced.
	 * 
	 * In addition to obeying the BST rule, the resulting tree must also obey
	 * the scapegoat rule.
	 * 
	 * This method must only perform rebalancing of subtrees when indicated
	 * by the scapegoat rule; do not unconditionally call balance() 
	 * after removing, or you will receive no credit. 
	 * See the project writeup for details.

	 * @param element
	 * @return true if and only if an element removed
	 * @throws NullPointerException if element is null
	 */
	@Override
	public boolean remove(T element) {
		// TODO
		if(element == null)
			throw new NullPointerException();
		return false;
	}

}
