package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	LLNode<T> head;
	LLNode<T> tail;

	public Queue() {
		// TODO 1
		head = null;
		tail = null;
	}

	public Queue(Queue<T> other) {
		// TODO 2
		// Hint: Maybe save this one until after you finish enqueue()/dequeue()
		head = other.head;
		tail = other.tail;
		size = other.size;
	}

	int size;

	@Override
	public boolean isEmpty() {
		if (size() == 0)
			return true;
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void enqueue(T element) {
		LLNode<T> curr = new LLNode<T>(element, null);
		if (size() == 0){
			head = curr;
			tail = curr;
			size++;
		}else{
			tail.setNext(curr);
			tail = curr;
			size++;
		}
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("No elements");
		size--;
		T first = head.getData();
		head = head.getNext();
		return first;
	}

	@Override
	public T peek() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException("No elements");
		return head.getData();
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		// TODO 8
		// Hint: Maybe save this one until after you finish enqueue()/dequeue()
		Queue<T> copy = new Queue<T>();
		LLNode<T> curr = this.head;
		for(int i=0; i<this.size;i++){
			copy.enqueue(curr.getData());
			curr = curr.getNext();
		}		
		UnboundedQueueInterface<T> re = new Queue<T>();
		LinkedStack<T> stack = new LinkedStack<T>();
		while(copy.size() != 0){
			stack.push(copy.dequeue());
		}while(stack.getSize() != 0){
			re.enqueue(stack.pop());
		}
		return re;
	}
}
