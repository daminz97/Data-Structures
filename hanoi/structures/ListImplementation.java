package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImplementation<T> implements ListInterface<T> {
	int size = 0;
	Node<T> head;
	Node<T> curr = head;

	public ListImplementation(){
	}

//	public int numofsize(){
//		size = 0;
//		Iterator<T> iter = this.iterator();
//		while(iter.hasNext()){
//			iter.next();
//			size++;
//		}
//		return size;
//	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return (head == null);
	}

	public T get(int n) throws NoSuchElementException{
		Node<T> end = head;
		int count = 0;
		if (n >= size || n < 0)
			throw new NoSuchElementException("No such element");
		else{
			while(end.getNext() != null && count != n){
				end = end.getNext();
				count++;
			}
			return end.getData();
		}

	}

	public ListInterface<T> append(T elem){
		
		if (elem == null)
			throw new NullPointerException("Input is null");
		else{
			Node<T> append = new Node<T>(elem, null);
			if (size() == 0){
				head = append;
				curr = append;
				size++;
			}
			else{
				curr.setNext(append);
				curr = append;
				size++;
			}
		}
		return this;
	}

	public Iterator<T> iterator(){
		return new NodeIterator<T>(this.head);
	}
}


