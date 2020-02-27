package structures;

import java.util.NoSuchElementException;

public class LinkedStack<T> {

	private LLNode<T> top;
	private int size = 0;


	public T pop() {
		if (top == null) {
			throw new NoSuchElementException();
		}
		LLNode<T> oldTop = top;
		top = top.getNext();
		size -= 1;
		return oldTop.getData();
	}


	public T peek() {
		if (top == null) {
			throw new NoSuchElementException();
		}
		return top.getData();
	}


	public boolean isEmpty() {
		return top == null;
	}


	public int getSize() {
		return size;
	}


	public void push(T elem) {
		top = new LLNode<T>(elem, top);
		size += 1;
	}

}
