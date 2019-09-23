import java.util.List;
import java.util.ArrayList;

public class IterativeDeepeningSearch extends Search {

	private final int infinity;	
	
	public IterativeDeepeningSearch() {
		infinity = 999999999;
	}
	
	
	// inner class
		private class DepthLimitedSearch {
			
		
			
			public DepthLimitedSearch() {
				
			}
			
			
			public List<Node> execute(Problem problem, int limit) {
				return recursiveDLS(new Node(problem.getInitialState()), problem, limit);
			}
			
			
			public List<Node> recursiveDLS(Node node, Problem problem, int limit) {
				boolean cutoff = false; 
				Global.MAXIMUM_LENGTH++;
				if (problem.isGoalState(node.getState())) {
					return node.getPathFromRoot();
				} else if (node.getDepth() == limit) {
					return createCutOffResult();
				} else {
					List<Node> children = Node.expand(node);
					for (int i = 0; i < children.size(); i++) {
						Node child = children.get(i);
						List<Node> result = recursiveDLS(child, problem, limit);
						if (cutOffResult(result)) {
							cutoff = true;
						} else if (!(result.size()==0)) {
							return result;
						}
					}
					if (cutoff) {
						return createCutOffResult();
					} else {
						return new ArrayList<Node>();
					}
				}
	 		}
			
			
			public List<Node> createCutOffResult() {
				List<Node> result = new ArrayList<Node>();
				Node node = new Node("cutoff");
				result.add(node);
				return result;
			}
			
			private boolean cutOffResult(List<Node> result) {
				return result.size() == 1 && result.get(0).getState().toString() == "cutoff";
			}
		}
	
	public List<Node> execute(Problem problem) {
		for (int depth = 0; depth <= infinity; depth++) {
			DepthLimitedSearch dls = new DepthLimitedSearch();
			List<Node> result = dls.execute(problem, depth);
			if (!cutOffResult(result)) {
				Global.EXPANSIONOFNODES = depth;
				return result;
			}
		}
		return new ArrayList<Node>();
	}
	
	
	private boolean cutOffResult(List<Node> result) {
		return result.size() == 1 && result.get(0).getState().toString().equals("cutoff");
	}
	
	
	
		
}