
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class AStarSearch extends Search {

	Set<Object> explored = new HashSet<Object>();
	
	public AStarSearch() {
		
	}
	

	private class PathCostComparator implements Comparator<Node> {

		@Override
		public int compare(Node nodeA, Node nodeB) {
			Heuristic heuristic = new Heuristic();
			PuzzleBoard boardA = (PuzzleBoard) nodeA.getState();
			PuzzleBoard boardB = (PuzzleBoard) nodeB.getState();			
			int h1_n = heuristic.value(boardA, Global.goalConfiguration);
			int h2_n = heuristic.value(boardB, Global.goalConfiguration);
			int f1_n = h1_n + nodeA.getPathCost() ;
			int f2_n = h2_n + nodeB.getPathCost() ;
			if (f1_n < f2_n) {
				return -1;
			}
			if (f1_n > f2_n) {
				return 1;
			}
			return 0;
		}
		
	}
	
	public boolean alreadyExplored(Node node) {
		return explored.contains(node.getState());
	}
	
	public List<Node> execute(Problem problem) {
		Heuristic heuristic = new Heuristic();
		Comparator<Node> eval = new PathCostComparator();	
		PriorityQueue<Node> frontier =	new PriorityQueue<Node>(181440, eval);
		Node root = new Node(problem.getInitialState());
		frontier.add(root);
		do {
			if(frontier.isEmpty()){
				return new ArrayList<Node>();
			}
			Node node = frontier.remove();
			if (problem.isGoalState(node.getState())) {
				Global.MAXIMUM_LENGTH = frontier.size();
				return node.getPathFromRoot();
			}
			explored.add(node); Global.EXPANSIONOFNODES++;
			List<Node> children = Node.expand(node);
			for (int i = 0; i < children.size(); i++) {
				Node child = children.get(i);
				if (!alreadyExplored(child) || !frontier.contains(child)) {
					frontier.add(children.get(i));
				} else if (frontier.contains(child) && (eval.compare(child, node) == 1)) {
					node = child;
				}
			}
		} while (!frontier.isEmpty());
		return new ArrayList<Node>();
	}
	
	
	
}
