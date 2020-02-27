package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NodeIterator<T> implements Iterator<T> {
	Node<T> curr;
	
	public NodeIterator(Node<T> head){
		curr = head;
	}
	
	@Override
	public boolean hasNext() {
		// TODO (3)
		if(curr == null)
			return false;
		else
			return true;
	}

	
	@Override	
	public T next() {
		// TODO (4)
		T next = null;
		if(curr == null){
			throw new NoSuchElementException();
		}else{
				next = curr.getData();
				curr = curr.getNext();
		}
		return next;
	}
}