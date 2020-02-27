package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {
		if (solution != null) {
			return solution;
		}
		List<T> path = new ArrayList<T>();
		Queue<T> queue = new LinkedList<T>();
		T state = searchProblem.getInitialState();
		queue.add(state);
		while (!queue.isEmpty()) {
			state = queue.remove();
			path.add(state);
			visited.add(state);
			if (searchProblem.isGoal(state)){
				break;
			}
			List<T> successors = searchProblem.getSuccessors(state);
			if (successors.size() == 0){
				path.remove(path.size()-1);
				queue.remove();
			}else{
				for (T successor : successors){
					if(!visited.contains(successor)){
					queue.add(successor);
					}
				}
			}
		}
		int psize = path.size();
		for (int i=psize-1; i>0; i--) {
			T temp = path.get(i);
			T pre = path.get(i-1);
			if (!searchProblem.getSuccessors(pre).contains(temp)) {
				path.remove(pre);
			}
		}
		return path;
	}
}