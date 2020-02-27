package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {
	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	List<Integer> list = new ArrayList<Integer>();
	public EightPuzzle(List<Integer> startingValues) {
		// TODO
		if (startingValues == null)
			throw new IllegalArgumentException();
		list = startingValues;
	}

	@Override
	public List<Integer> getInitialState() {
		// TODO
		return list;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		// TODO
		List<List<Integer>> neighbors = new ArrayList<List<Integer>>();
		int index = currentState.indexOf(0);
		int size = currentState.size();
		if((index-3) >= 0)
			neighbors.add(swap(index, index-3, currentState));
		if((index+3) < size)
			neighbors.add(swap(index, index+3, currentState));
		if(index!=0){
			if(index!=3){
				if(index!=6)
					neighbors.add(swap(index, index-1, currentState));
			}
		}
		if(index!=2){
			if(index!=5){
				if(index!=8)
					neighbors.add(swap(index, index+1, currentState));
			}
		}
		return neighbors;
	}

	private List<Integer> swap(int start, int end, List<Integer> state){
		List<Integer> curr = new ArrayList<Integer>();
		for(Integer i : state){
			curr.add(i);
		}
		int data = curr.get(start);
		curr.set(start, curr.get(end));
		curr.set(end, data);
		return curr;
	}
	
	@Override
	public boolean isGoal(List<Integer> state) {
		// TODO
		for(int i=0; i<state.size()-1;i++){
			if(!state.get(i).equals(i+1))
				return false;
		}
		if (!state.get(state.size()-1).equals(0))
			return false;
		return true;
	}

	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
				4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}
}
