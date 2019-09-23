import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class puzzle8 {
	
	private static double startTime=0.0;
	private static double endTime=0.0;
	
	public static void display(List<Node> result, String method){
		int j;
		for (int i = 0; i < result.size(); i++) {
			j=i+1;
			System.out.println("STEP " + j);
			System.out.println(convertStringToInt((PuzzleBoard) result.get(i).getState()));
		}
		System.out.println("Passed: " + method + " Search");
		viewResults(result.get(result.size() - 1), method);
	}
	
	public static void replaceWithZero(ArrayList<String> layout){
		for (int i = 0; i < layout.size(); i++) {
			if (layout.get(i).equals("_")) {
				layout.set(i, "0");
			}
		}
	}
	
	public static void main(String args[]) {		
		
		Scanner argInput, fileInputGoal, fileInputStart;		
		String method, goalFile, startFile, line1 ;
		Problem problem = new Problem();
		ArrayList<String> finalLayout = new ArrayList<String>();
		ArrayList<String> initialLayout = new ArrayList<String>();	
		argInput = new Scanner(System.in);		
		startFile = args[0];
		goalFile = args[1];
		method = args[2].toLowerCase();
			
		try {
			fileInputGoal = new Scanner(new File(goalFile));
			while (fileInputGoal.hasNextLine()) {
				line1 = fileInputGoal.nextLine();
				for (int i = 0; i < line1.length(); i++) {
					finalLayout.add(Character.toString(line1.charAt(i)));
				}
			
			}
		}
		catch (FileNotFoundException e) {
			fileInputGoal = null;
			System.out.println("\nThe Goal file is not found!");
		}
		fileInputGoal.close();	
				
		try {
			fileInputStart = new Scanner(new File(startFile));
			while (fileInputStart.hasNextLine()) {
				line1 = fileInputStart.nextLine();
				for (int i = 0; i < line1.length(); i++) {
					initialLayout.add(Character.toString(line1.charAt(i)));
				}
			
			}
		}
		catch (FileNotFoundException e) {
			fileInputStart = null;
			System.out.println("\nThe Start file is not found!");
		}
		
		fileInputStart.close();			
		
		replaceWithZero(finalLayout);
		replaceWithZero(initialLayout);
		
		
		PuzzleBoard finalBoard = new PuzzleBoard(finalLayout);
		Global.goalConfiguration = finalBoard;
		problem.setGoalState(finalBoard);
		
		PuzzleBoard startBoard = new PuzzleBoard(initialLayout);
		problem.setInitialState(startBoard);	
		
		chooseMethod(method, problem);			
			
	}
	
	public static void viewResults(Node node, String method) {
		double time = endTime - startTime;
		System.out.println("The time of executiing " + method + " method is :  " + time + " nano seconds");
		System.out.println("Number of nodes expanded is:   " + Global.EXPANSIONOFNODES);		
		System.out.println("The Maximum Length is:  " + Global.MAXIMUM_LENGTH);
		System.out.println("Length of the " + method + " solution is: " + node.getDepth());
	}
	
	public static String convertStringToInt(PuzzleBoard puzzleBoard) {
		int boardInt[][] = puzzleBoard.getBoard();
		String boardString[][] = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (boardInt[i][j] == 0) {
					boardString[i][j] = "_"; 
				} else {
					boardString[i][j] = Integer.toString(boardInt[i][j]);
				}
			}
		}
		String fullString = boardString[0][0] + " " + boardString[0][1] + " " + boardString[0][2] + "\n" + boardString[1][0] + " " + boardString[1][1] + " " + boardString[1][2] + "\n" + boardString[2][0] + " " + boardString[2][1] + " " + boardString[2][2] + "\n";
		return fullString;
	}
	
	public static void chooseMethod(String method, Problem problem){
			if(method.equalsIgnoreCase("ucs"))
			{
				startTime = System.nanoTime();
				UniformCostSearch ucs = new UniformCostSearch();
				List<Node> result = ucs.execute(problem);
				endTime = System.nanoTime();				
				display(result, method);
			}
			
			else if(method.equalsIgnoreCase("idastar"))
			{
				startTime = System.nanoTime();
				IterativeDeepeningAStarSearch idastar = new IterativeDeepeningAStarSearch();				
				List<Node> result = idastar.execute(problem);
				endTime = System.nanoTime();
				display(result, method);
			}
			
			else if(method.equalsIgnoreCase("ids"))
			{
				startTime = System.nanoTime();
				IterativeDeepeningSearch ids = new IterativeDeepeningSearch();				
				List<Node> result = ids.execute(problem);
				endTime = System.nanoTime();
				display(result, method);
			}
			
			else if(method.equalsIgnoreCase("astar"))
			{
				startTime = System.nanoTime();
				AStarSearch astar = new AStarSearch();				
				List<Node> result = astar.execute(problem);
				endTime = System.nanoTime();
				display(result, method);
			}
			
			else
			{
				System.out.println("The " + method + " doesn't exist!");
			}
	}
	
	
	
}