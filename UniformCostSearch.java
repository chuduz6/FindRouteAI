import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;



public class UniformCostSearch extends Search {

	
	Set<Object> explored = new HashSet<Object>();
	
	
	public UniformCostSearch() {
		
	}
	
	//inner class
		private class PathCostComparator implements Comparator<Node> {

			@Override
			public int compare(Node nodeA, Node nodeB) {
				
				if (Integer.valueOf(nodeA.getPathCost()) > Integer.valueOf(nodeB.getPathCost())) {
					return 1;
				}
				
				if (Integer.valueOf(nodeA.getPathCost()) < Integer.valueOf(nodeB.getPathCost())) {
					return -1;
				}
				
				return 0;
			}
			
		}
		
	public boolean alreadyExplored(Node node) {
		return explored.contains(node);
	}
	
	
	public List<Node> execute(Problem problem) {
		Comparator<Node> eval = new PathCostComparator();		
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(181440, eval);
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
			explored.add(node); 
			Global.EXPANSIONOFNODES++;
			List<Node> children = Node.expand(node);
			for (int i = 0; i < children.size(); i++) {
				Node child = children.get(i);
				if (!alreadyExplored(child) || !frontier.contains(child)) {
					frontier.add(child);
				} else if (frontier.contains(child) && (eval.compare(child, node) == 1)) {
					node = child;
				}
			}
		} while (!frontier.isEmpty());
		return new ArrayList<Node>();
	}	
}
