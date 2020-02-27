package structures;

import java.util.Comparator;
import java.util.Iterator;

import comparators.ReverseIntegerComparator;

public class MinQueue<V> implements PriorityQueue<Integer, V> {
	private StudentArrayHeap<Integer, V> heap;
	private ReverseIntegerComparator comp;
	
	public MinQueue(){
		comp = new ReverseIntegerComparator();
		heap = new StudentArrayHeap<Integer, V>(comp);
	}
	
	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value){
		if(priority == null || value == null)
			throw new NullPointerException();
		heap.add(priority, value);
		return this;
	}

	@Override
	public V dequeue(){
		if(isEmpty())
			throw new IllegalStateException();
		return heap.remove();
	}

	@Override
	public V peek(){
		if(isEmpty())
			throw new IllegalStateException();
		return heap.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator(){
		return heap.asList().iterator();
	}

	@Override
	public Comparator<Integer> getComparator(){
		return comp;
	}

	@Override
	public int size(){
		return heap.size();
	}

	@Override
	public boolean isEmpty(){
		return size()==0;
	}
}

