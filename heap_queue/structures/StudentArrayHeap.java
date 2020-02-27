package structures;

import java.util.Comparator;


public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {

	public StudentArrayHeap(Comparator<P> comparator){
		super(comparator);
	}

	@Override
	protected int getLeftChildOf(int index){
		if(index < 0)
			throw new IndexOutOfBoundsException();
		return (index*2 + 1);
	}

	@Override
	protected int getRightChildOf(int index){
		if(index < 0)
			throw new IndexOutOfBoundsException();
		return (index*2 + 2);
	}

	@Override
	protected int getParentOf(int index){
		if(index <= 0)
			throw new IndexOutOfBoundsException();
		return (index-1) / 2;
	}

	@Override
	protected void bubbleUp(int index){
		int parent = (index-1)/2;
		if(parent < 0)
			throw new IndexOutOfBoundsException();
		if(getComparator().compare(heap.get(index).getPriority(), heap.get(parent).getPriority()) >0 ){
			swap (index, parent);
			bubbleUp (parent);
		}
	}

	@Override
	protected void bubbleDown(int index){

		int Lchild = index*2+1;
		if( Lchild < size() ){
			int Rchild = Lchild+1;
			int bigChild = Lchild;
			if( Rchild < size() && getComparator().compare(heap.get(bigChild).getPriority(), heap.get(Rchild).getPriority())<0){
				bigChild = Rchild ;
			}
			if(getComparator().compare(heap.get(index).getPriority(), heap.get(bigChild).getPriority()) <0){
				swap (bigChild, index);
				bubbleDown(bigChild);
			}
		}
	}
}
