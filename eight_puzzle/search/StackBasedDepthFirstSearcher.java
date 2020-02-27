package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {


	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {
		if (solution != null) {
			return solution;
		}
		List<T> path = new ArrayList<T>();
		Stack<T> stack = new Stack<T>();
		T state = searchProblem.getInitialState();
		stack.push(state);
		while (!stack.isEmpty()) {
			state = stack.peek();
			visited.add(state);
			if (searchProblem.isGoal(state)){
				break;
			}
			List<T> successors = searchProblem.getSuccessors(state);
			if (successors.size() == 0){
				stack.pop();
			}
			else{
				int count = 0;
				for (T successor : successors){
					if(!visited.contains(successor)){
						stack.push(successor);
					}else{
						count++;
					}
				}
				if(count == successors.size())
					stack.pop();
			}
		}
		while (!stack.isEmpty()){
			path.add(0, stack.pop());
		}
		int psize = path.size();
		int count = 0;
		for (int i=0; i<psize; i++) {
			T temp = path.get(count);
			T pre = path.get(count+1);
			if (!searchProblem.getSuccessors(pre).contains(temp)) {
				path.remove(pre);
			}else{
				count++;
			}
		}
		return path;
	}
}




