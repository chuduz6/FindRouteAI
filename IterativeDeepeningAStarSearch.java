import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class IterativeDeepeningAStarSearch extends Search {
	
	Set<Object> explored = new HashSet<Object>();	
	
	public IterativeDeepeningAStarSearch() {
		
	}
	
	
	public List<Node> execute(Problem problem) {
		Node startNode = new Node(problem.getInitialState());
		int threshold = Heuristic.value((PuzzleBoard) startNode.getState(), Global.goalConfiguration);
		Node result = null;
		while (result == null) {
			int currentCost = threshold;
			result = depthLimitedSearch(startNode, problem, currentCost);
			threshold = threshold + 1;
		}
		return result.getPathFromRoot();
	}
	
	
	public Node depthLimitedSearch(Node node, Problem problem, int threshold) {
		
		if (problem.isGoalState(node.getState())) {
			return node;
		}
		Global.MAXIMUM_LENGTH++;
		explored.add(node.getState()); 
		Global.EXPANSIONOFNODES++;
		List<Node> children = Node.expand(node);
		for (int i = 0; i < children.size(); i++) {
			Node child = children.get(i);
			int currentCost = child.getPathCost() + Heuristic.value((PuzzleBoard) child.getState(), Global.goalConfiguration);			
			if (currentCost <= threshold) {
				Node result = depthLimitedSearch(child, problem, threshold);
				if (result != null) {
					return result;
				}
			}	
		}
		return null;
	}

}