package sorters;

import java.util.Comparator;

import structures.SwapList;

public class QuickSorter<T> extends AbstractSorter<T> {

	public QuickSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		// TODO

		/*
		 * Note: When choosing a pivot, choose the element in the middle of
		 * the sub-array. That is,
		 * 
		 * pivotIndex = (firstIndex + lastIndex) / 2;
		 */
		quickSort(0, list.size()-1, list);
		return list;
	}

	private SwapList<T> quickSort(int low, int high, SwapList<T> newlist) {
		if (low < high) {
			int p = partition(low, high, newlist);
			quickSort(low, p-1, newlist);
			quickSort(p+1, high, newlist);
		}
		return newlist;
	}
	
	private int partition(int low, int high, SwapList<T> alist) {
		int middle = (low + high)/2;
		list.swap(middle, high);
		int pivot = high;
		int storeIndex = low;
		for (int i=low; i<=high-1;i++) {
			if(alist.compare(i, pivot, comparator) <= 0) {
				alist.swap(i, storeIndex);
				storeIndex++;
			}
		}
		alist.swap(storeIndex, high);
		return storeIndex;
	}
}