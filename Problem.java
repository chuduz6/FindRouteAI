public class Problem {
	
	private Object initialState;
	
	private Object goalState;
	
	private Integer pathCost;
	
	public Problem() {
		
	}
	
	
	public Problem(Object initial, Object goal, int cost) {
		this.initialState = initial;
		this.goalState = goal;
		this.pathCost = Integer.valueOf(cost);
	}
	
	public Object getInitialState() {
		return initialState;
	}
	
	public void setInitialState(Object initial) {
		this.initialState = initial;
	}
	
	public Object getGoalState() {
		return goalState;
	}
	
	public void setGoalState(Object goal) {
		this.goalState = goal;
	}
	
	
	public int getPathCost() {
		return pathCost.intValue();
	}
	
	public void setPathCost(int cost) {
		this.pathCost = Integer.valueOf(cost);
	}
	
	
	public void addPathCost(int stepCost) {
		pathCost = Integer.valueOf(pathCost.intValue() + stepCost);
	}	
	
	public boolean isGoalState(Object state) {
		return state.toString().equals(this.goalState.toString());
	}
	
}