import java.util.ArrayList;

public class PuzzleBoard {

	private int puzzleBoard[][] = new int[3][3];
	
	private int space;	
	
	public PuzzleBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				puzzleBoard[i][j] = 10;
			}
		}
	}
	
	
	public PuzzleBoard(int[] board) {
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				puzzleBoard[i][j] = board[k++];
				if (board[k - 1] == 0) {
					space = k - 1;
				}
			}
		}
	}
	
	
	public PuzzleBoard(ArrayList<String> board) {
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				puzzleBoard[i][j] = Integer.parseInt(board.get(k++));
				if (board.get(k - 1).equals("0")) {
					space = k - 1;
				}
			}
		}
	}
		
	
	public int[][] getBoard() {
		return puzzleBoard;
	}
	
	
	public int getBlankBox() {
		return space;
	}
	
	
	public int[] getPosition(int val) {
		int[] getXandY = new int[] { -1, -1 };
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (puzzleBoard[i][j] == val) {
					getXandY[0] = i; getXandY[1] = j;
				}
			}
		}
		return getXandY;
	}
	
	
	public void copy(PuzzleBoard board) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.puzzleBoard[i][j] = board.puzzleBoard[i][j];
			}
		}
		this.space = board.space;
 	}	
	
	public int[] moves() {
		
		int x = space / 3;
		int y = space % 3;
		int[] arrPosition = new int[4];
		
		// RIGHT
		if (y < 2) {
			arrPosition[0] = x * 3 + y + 1;
		} else {
			arrPosition[0] = -1;
		}
		
		// LEFT
		if (y > 0) {
			arrPosition[1] = x * 3 + y - 1;
		} else {
			arrPosition[1] = -1;
		}
		
		// DOWN
		if (x < 2) {
			arrPosition[2] = ((x + 1) * 3) + y;
		} else {
			arrPosition[2] = -1;
		}		
		
		// TOP
		if (x > 0) {
			arrPosition[3] = ((x - 1) * 3) + y;
		} else {
			arrPosition[3] = -1;
		}
		
		
		return arrPosition;
	}
	
	public void updatePuzzleBoard(int current){
		int previous, currentX, currentY, previousX, previousY, swapTemp;
		previous = space;
		space = current;
		currentX = current / 3;
		currentY = current % 3;
		previousX = previous / 3;
		previousY = previous % 3;
		swapTemp = this.puzzleBoard[currentX][currentY];
		this.puzzleBoard[currentX][currentY] = this.puzzleBoard[previousX][previousY];
		this.puzzleBoard[previousX][previousY] = swapTemp;
		
	}
	
	public void moveBlankRight() {
		int configure=moves()[0]; 	
		if (configure != -1) {
			updatePuzzleBoard(configure);			
		}
	}
	
	public void moveBlankLeft() {
		int configure=moves()[1]; 	
		if (configure != -1) {
			updatePuzzleBoard(configure);			
		}
	}
	
	public void moveBlankDown() {
		int configure=moves()[2]; 	
		if (configure != -1) {
			updatePuzzleBoard(configure);			
		}
	}

	
	public void moveBlankUp() {
		int configure=moves()[3]; 	
		if (configure != -1) {
			updatePuzzleBoard(configure);			
		}
	}
	
	
	public boolean canBlankMove(String where) {
		
		if (where.equals("right")) {
			if (moves()[0] == -1) {
				return false;
			}
		}
		
		if (where.equals("left")) {
			if (moves()[1] == -1) {
				return false;
			}
		}
		
		if (where.equals("down")) {
			if (moves()[2] == -1) {
				return false;
			}
		}
		
		if (where.equals("up")) {
			if (moves()[3] == -1) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
	
	@Override
	public String toString() {
		String fullString = puzzleBoard[0][0] + " " + puzzleBoard[0][1] + " " + puzzleBoard[0][2] + "\n" + puzzleBoard[1][0] + " " + puzzleBoard[1][1] + " " + puzzleBoard[1][2] + "\n" + puzzleBoard[2][0] + " " + puzzleBoard[2][1] + " " + puzzleBoard[2][2] + "\n";
		return fullString;
	}

}
