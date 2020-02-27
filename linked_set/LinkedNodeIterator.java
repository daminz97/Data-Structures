package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
	// TODO (1) choose appropriate attributes
	/** attribute that takes value of the head of linked list*/
	LinkedNode<E> head;

	// Constructors
	public LinkedNodeIterator(LinkedNode<E> head) {
		// TODO (2) choose appropriate parameters and do the initialization
		LinkedNode<E> pointer = this.head;
		while(pointer == null){
			pointer = head;
			this.head = head;
		}
		pointer = pointer.getNext();
	}

	@Override
	public boolean hasNext() {
		// TODO (3)
		boolean result = false;
		LinkedNode<E> pointer = this.head;
		if(pointer == null){
			result = false;
		}else{
			result = true;
			pointer = pointer.getNext();
		}
		return result;
	}

	@Override
	public E next() {
		// TODO (4)
		E next = null;
		LinkedNode<E> pointer = this.head;
		if(pointer == null){
			throw new NoSuchElementException();
		}else{
			while(pointer != null){
				next = pointer.getNext().getData();
			}
		}
		return next;
	}

	// Leave this one alone.
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
