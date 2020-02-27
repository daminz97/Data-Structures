package sets;

import java.util.Iterator;

public class LinkedSet<E> implements Set<E> {
	private LinkedNode<E> head = null;

	// Constructors
	public LinkedSet() {
	}

	public LinkedSet(E e) {
		this.head = new LinkedNode<E>(e, null);
	}

	private LinkedSet(LinkedNode<E> head) {
		this.head = head;
	}

	@Override
	public int size() {
		// TODO (1)
		int count = 0;
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()){
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		// TODO (2)
		boolean result = false;
		LinkedNode<E> pointer = this.head;
		if(size() == 0){
			result = true;
		}else if(pointer != null){
			result = false;
		}
		return result;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedNodeIterator<E>(this.head);
	}

	@Override
	public boolean contains(Object o) {
		// TODO (3)
		boolean result = false;
		Iterator<E> set = this.iterator();
		while(set.hasNext()){
			if(set.next() == o)
				result = true;
		}
		return result;
	}

	@Override
	public boolean isSubset(Set<E> that) {
		// TODO (4)
		boolean result = false;
		Iterator<E> iter = that.iterator();
		if(this.size() <= that.size()){
			while(iter.hasNext()){
				if(this.contains(iter.next()))
						result = true;
				else{
					result = false;
					break;
				}
			}
		}else{
			result = false;
		}
		return result;
	}

	@Override
	public boolean isSuperset(Set<E> that) {
		// TODO (5)
		boolean result = false;
		if(isSubset(that) == false){
			result = true;
		}
		return result;
	}

	@Override
	public Set<E> adjoin(E e) {
		// TODO (6)
		LinkedNode<E> head = new LinkedNode<E>(e, null);
		LinkedNode<E> link = this.head;
		LinkedSet<E> result;
		if(contains(e) == true){
			result = this;
		}else{
			link = head;
			this.head = link;
			result = this;
		}
		return result;
	}

	@Override
	public Set<E> union(Set<E> that) {
		// TODO (7)
		LinkedSet<E> newSet = new LinkedSet<E>(head);
		Iterator<E> iter = that.iterator();
		while(iter.hasNext()){
			newSet = (LinkedSet<E>)newSet.adjoin(iter.next());
		}
		return newSet;
	}

	@Override
	public Set<E> intersect(Set<E> that) {
		// TODO (8)
		LinkedSet<E> newSet = new LinkedSet<E>(head);
		Iterator<E> iter = that.iterator();
		Iterator<E> iter2 = this.iterator();
		while(iter.hasNext()){
			if(iter.next() == iter2.next())
				newSet = (LinkedSet<E>)newSet.adjoin(iter.next());
		}
		return newSet;
	}

	@Override
	public Set<E> subtract(Set<E> that) {
		// TODO (9)
		LinkedSet<E> newSet = (LinkedSet<E>)this.intersect(that);
		LinkedSet<E> newSet2 = new LinkedSet<E>(head);
		Iterator<E> thisiter = this.iterator();
		Iterator<E> thatiter = that.iterator();
		Iterator<E> newSetiter = newSet.iterator();
		while(thisiter.hasNext() || thatiter.hasNext()){
			if(thisiter.next() != newSetiter.next())
				newSet2 = (LinkedSet<E>)newSet2.adjoin(thisiter.next());
			newSet2 = (LinkedSet<E>)newSet2.adjoin(thatiter.next());
		}
		return newSet2;
	}

	@Override
	public Set<E> remove(E e) {
		// TODO (10)
		LinkedSet<E> newSet = new LinkedSet<E>(head);
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()){
			if(iter.next() != e)
				newSet = (LinkedSet<E>)newSet.adjoin(e);
		}
		return newSet;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (! (o instanceof Set)) {
			return false;
		}
		Set<E> that = (Set<E>)o;
		return this.isSubset(that) && that.isSubset(this);
	}

	@Override
	public int hashCode() {
		int result = 0;
		for (E e : this) {
			result += e.hashCode();
		}
		return result;
	}
}
