package sorters;

import java.util.Comparator;

import structures.SwapList;

public class HeapSorter<T> extends AbstractSorter<T> {

	public HeapSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO
		heapSort();
		return list;
	}

	private SwapList<T> heapSort(){
		int size = list.size();
		for (int i=size/2-1; i>=0; i--) {
			bubbleDown(i, size-1, list);
		}
		for (int i=size-1; i>=1; i--) {
			list.swap(0, i);
			bubbleDown(0, i-1, list);
		}
		return list;
	}

	private SwapList<T> bubbleDown(int start, int end, SwapList<T> newList){
		SwapList<T> list = newList;
		// base case
		if(start == end){
			return list;
		}
		else{
			int indexofLeft = start *2+1;
			// if left child exists
			if(indexofLeft <= end){
				int indexofRight = indexofLeft +1;
				int i_bigChild = (indexofLeft);
				if( indexofRight <= end &&  list.compare(i_bigChild, indexofRight, comparator)<0){
					i_bigChild = indexofRight ;
				}
				if(list.compare(start, i_bigChild, comparator)<0){
					list.swap(i_bigChild, start);
					bubbleDown ( i_bigChild , end, list);
				}
			}
		}
		return list;
	}
}