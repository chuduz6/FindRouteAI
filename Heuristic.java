public class Heuristic {
	public static int value(PuzzleBoard board, PuzzleBoard goal) {
		int sumDifference = 0;
		int x, y, xGoal, yGoal;
		for (int i = 1; i < 9; i++) {
			x = board.getPosition(i)[0];
			xGoal = goal.getPosition(i)[0];
			y = board.getPosition(i)[1];			
			yGoal = goal.getPosition(i)[1];
			sumDifference = sumDifference + Math.abs(yGoal - y) + (Math.abs(xGoal - x) );
		}
		return sumDifference;
	}
	
}