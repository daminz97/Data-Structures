package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {
	Node<T> head;
	Node<T> temp = head;
	int size = 0;

	public RecursiveList(){
		head = new Node<T>(null, null);
	}
	
	public int size(){
		return size;
	}
	
	public ListInterface<T> insertFirst(T elem){
		if(elem == null)
			throw new NullPointerException("Elem is null");
		Node<T> top = new Node<T>(elem, null);
		if(size == 0){
			head = top;
			temp = head;
			size++;
			return this;
		}
		top.setNext(head);
		head = top;
		temp = head;
		size++;
		return this;
	}
	
	public ListInterface<T> insertLast(T elem){
		Node<T> tail = new Node<T>(elem, null);
		if(elem == null)
			throw new NullPointerException("Elem is null");
		if(temp == null){
			head = tail;
			temp = head;
			size++;
			return this;
		}
		if(temp.getNext() == null){
			temp.setNext(tail);
			size++;
			temp = head;
			return this;
		}
		temp = temp.getNext();
		return insertLast(elem);
	}
	
	int count = 0;
	public ListInterface<T> insertAt(int index, T elem){
		if(elem == null)
			throw new NullPointerException("Elem is null");
		if(index<0 || index>size){
			throw new IndexOutOfBoundsException();
		}
		if(index==0){
			insertFirst(elem);
			return this;
		}
		Node<T> curr = IndexToNode(index-1);
		Node<T> insert = new Node<T>(elem, curr.getNext());
		curr.setNext(insert);
		size++;
		temp = head;
		count = 0;
		return this;
		

	}
	
	public T removeFirst(){
		if(this.size() == 0)
			throw new IllegalStateException("Empty");
		Node<T> oldTop = temp;
		temp = temp.getNext();
		head = temp;
		oldTop.setNext(null);
		size--;
		return oldTop.getData();
	}
	
	
	public T removeLast(){
		if(this.size() == 0)
			throw new IllegalStateException("Empty");
		if(size == 1){
			T data1 = temp.getData();
			head = null;
			temp = head;
			size--;
			return data1;
		}
		Node<T> curr = IndexToNode(size-2);
		T data = curr.getNext().getData();
		curr.setNext(null);
		size--;
		temp = head;
		count = 0;
		return data;

	}
	
	public T removeAt(int i){
		if(i<0 || i>=size)
			throw new IndexOutOfBoundsException("Invalid size");
		Node<T> remove = temp.getNext();
		if(i == 0)
			return removeFirst();
//		if(i == size-2 && size>2){
//			temp.setNext(null);
//			size--;
//			temp = head;
//			count = 0;
//			return remove.getData();
//		}
		
		Node<T> curr = IndexToNode(i-1);
		T data = curr.getNext().getData();
		curr.setNext(curr.getNext().getNext());
		size--;
		temp = head;
		count = 0;
		return data;
	}
	
	public T getFirst(){
		if(this.size() == 0)
			throw new IllegalStateException("Empty");
		return head.getData();
	}
	
	public T getLast(){
		if(this.size() == 0)
			throw new IllegalStateException("Empty");
		return get(size-1, temp, count);
	}
	
	public T get(int i){
		if(i<0 || i>=size)
			throw new IndexOutOfBoundsException("Invalid size");
		return get(i, temp, count);
	}
	
	public boolean remove(T elem){
		if(elem == null)
			throw new NullPointerException("Elem is null");
		if(head.getData().equals(elem)){
			removeFirst();
			return true;
		}
		Node<T> curr = ElemToNode(elem);
		if(curr == null){
			temp = head;
			return false;
		}
		if(size==1)
			head = null;
		else{
			curr.setNext(curr.getNext().getNext());
		}
		size--;
		temp = head;
		return true;
	}
	
	public int indexOf(T elem){
		temp = head;
		if(elem == null)
			throw new NullPointerException("Elem is null");
		return indexOf(elem, count);
	}
	
	public boolean isEmpty(){
		return (size == 0);
	}
	
	private Node<T> IndexToNode(int toFind){
		if(toFind == 0)
			return temp;
		temp = temp.getNext();
		return IndexToNode(toFind - 1);
	}
	
	private Node<T> ElemToNode(T elem){
		if(temp.getNext() == null)
			return null;
		if(size==1){
			if(temp.getData().equals(elem))
				return temp;
			return null;
		}else{
//			if(temp.getData().equals(elem))
//				return temp;
			if(temp.getNext().getData().equals(elem)){
				return temp;
			}
		}
		temp = temp.getNext();
		return ElemToNode(elem);
	}
	
	private T get(int toFind, Node<T> toCheck, int currentIndex){
		if(currentIndex == toFind){
			return toCheck.getData();
		}
		toCheck = toCheck.getNext();
		currentIndex++;
		count = 0;
		return get(toFind, toCheck, currentIndex);
	}
	
	private int indexOf(T toFind, int index){
		if(temp == null)
			return -1;
		if(temp.getData().equals(toFind)){
			temp = head;
			count = 0;
			return index;
		}
		index++;
		temp = temp.getNext();
		return indexOf(toFind, index);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
