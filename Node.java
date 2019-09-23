import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Node {

	
	private Object state;
	
	private Node parentNode;
	
	private Integer pathCost;
	
	private int depth;
	
	private Integer stepCost;
	
	
	public Node(Object state) {
		this.state = state;
		this.pathCost = new Integer(0);
		this.depth = 0;
		this.stepCost = new Integer(0);
	}
	
	
	public Node(Object state, Node parentNode) {
		this.state = state;
		this.pathCost = new Integer(0);
		this.depth = parentNode.getDepth() + 1;
		this.stepCost = new Integer(0);
		this.parentNode = parentNode;
		
	}
	
	
	public Object getState() {
		return state;
	}
	
	
	public Node getParentNode() {
		return parentNode;
	}
	
	
	public int getPathCost() {
		return pathCost.intValue();
	}
	
	
	public int getDepth() {
		return depth;
	}
	
	
	public int getStepCost() {
		return stepCost.intValue();
	}
	
	public void setStepCost(Integer stepCost) {
		this.stepCost = stepCost;
	}
	
	
	public void addToPathCost(Integer stepCost) {
		this.pathCost = new Integer(parentNode.pathCost.intValue()
				+ stepCost.intValue());
	}
	
	public boolean isRootNode() {
		return parentNode == null;
	}
	
	
	public List<Node> getPathFromRoot() {
		Node current = this;
		LinkedList<Node> list = new LinkedList<Node>();
		while (!current.isRootNode()) {
			list.addFirst(current);
			current = current.getParentNode();
		}
		list.addFirst(current);
		return list;
	}
	
	public static List<Node> expand(Node node) {
		List<Node> nodes = new ArrayList<Node>();
		PuzzleBoard board = (PuzzleBoard) node.getState();
		if (board.canBlankMove("up")) {
			PuzzleBoard newBoard = new PuzzleBoard();
			newBoard.copy(board);
			newBoard.moveBlankUp();
			Node child = new Node(newBoard, node);
			child.setStepCost(new Integer(1));
			child.addToPathCost(new Integer(1));
			nodes.add(child);
		}
		if (board.canBlankMove("down")) {
			PuzzleBoard newBoard = new PuzzleBoard();
			newBoard.copy(board);
			newBoard.moveBlankDown();
			Node child = new Node(newBoard, node);
			child.setStepCost(new Integer(1));
			child.addToPathCost(new Integer(1));
			nodes.add(child);
		}
		if (board.canBlankMove("left")) {
			PuzzleBoard newBoard = new PuzzleBoard();
			newBoard.copy(board);
			newBoard.moveBlankLeft();
			Node child = new Node(newBoard, node);
			child.setStepCost(new Integer(1));
			child.addToPathCost(new Integer(1));
			nodes.add(child);
		}
		if (board.canBlankMove("right")) {
			PuzzleBoard newBoard = new PuzzleBoard();
			newBoard.copy(board);
			newBoard.moveBlankRight();
			Node child = new Node(newBoard, node);
			child.setStepCost(new Integer(1));
			child.addToPathCost(new Integer(1));
			nodes.add(child);
		}
		return nodes;
	}
	
}
