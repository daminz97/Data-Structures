package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {

	/**
	 * {@inheritDoc}
	 */
	LLNode<T> head= null;
	@Override
	public T pop() throws StackUnderflowException {
		// TODO Auto-generated method stub
		T data = null;
		if (isEmpty())
			throw new StackUnderflowException("There is no element in this stack.");
		data = head.getData();
		head = head.getNext();
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException {
		// TODO Auto-generated method stub
		T data = null;
		if (isEmpty())
			throw new StackUnderflowException("There is no element in this stack");
		else
			data = head.getData();
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (head == null)
			return true;
		else
			return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		int size = 0;
		if(head == null)
			return size;
		LLNode<T> curr = head;
		while(curr != null){
			size++;
			curr = curr.getNext();
		}
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) {
		// TODO Auto-generated method stub
		LLNode<T> curr = new LLNode<T>(elem);
		LLNode<T> pointer = head;
		curr.setNext(pointer);
		head = curr;
	}

}
