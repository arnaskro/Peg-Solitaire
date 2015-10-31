/**
 * @title	Peg Solitaire solver
 * @author	Arnas Kromelis
 * @date	10/31/2015
 */
package ark.viauc.ads.peg;

public class PegSolitaireSolver {

	public static void main(String[] args) {
		new PegSolitaireSolver().run();
	}

	private char[][] board = 
	{
			{' ', ' ', 'o', 'o', 'o', ' ', ' '},
			{' ', ' ', 'o', 'o', 'o', ' ', ' '},
			{'o', 'o', 'o', 'o', 'o', 'o', 'o'},
			{'o', 'o', 'o', '.', 'o', 'o', 'o'},
			{'o', 'o', 'o', 'o', 'o', 'o', 'o'},
			{' ', ' ', 'o', 'o', 'o', ' ', ' '},
			{' ', ' ', 'o', 'o', 'o', ' ', ' '}
	};
	
	private int height = board.length;
	private int length = board[height-1].length;
	private int pegs = 32;
	private final String[] DIRECTIONS = {"UP", "RIGHT", "DOWN", "LEFT"};
	
	private void run() {
		printBoard();

		System.out.println("Solving... ");
					
		if (!findSolution())
			System.out.println("\nSolution was not found!");
		
	}

	private boolean findSolution() {
		
		if (pegs == 1 && board[3][3] == 'o') {
			System.out.println("Done!");
			printBoard();
			return true;
		} else {
			for (int i = 0; i < length; i++)
			    for (int j = 0; j < height; j++)
			        for (int k = 0; k < DIRECTIONS.length; k++) {
			        	int[][] move = setMove(j, i, DIRECTIONS[k]);
			            if (legalMove(move)) {
			            	makeMove(move);
			            	
			            	//printBoard();
			            	
			            	if (findSolution())
			            		return true;
			            	
			            	undo(move);
			            }
			        }

			return false;
		}
	}
	
	private int[][] setMove(int row, int col, String direction) {
		int[][] move = new int[3][2];
		move[0][0] = row;
		move[0][1] = col;
		
		switch (direction) {

		case "UP":
			move[1][0] = row+1;
			move[1][1] = col;
			move[2][0] = row+2;
			move[2][1] = col;
			break;
			
		case "LEFT":
			move[1][0] = row;
			move[1][1] = col-1;
			move[2][0] = row;
			move[2][1] = col-2;
			break;
			
		case "RIGHT":
			move[1][0] = row;
			move[1][1] = col+1;
			move[2][0] = row;
			move[2][1] = col+2;
			break;
			
		case "DOWN":
			move[1][0] = row-1;
			move[1][1] = col;
			move[2][0] = row-2;
			move[2][1] = col;
			break;

		default:
			
			break;
		}
		
		return move;
	}

	private void makeMove(int[][] move) {
		board[move[0][0]][move[0][1]] = '.';
		board[move[1][0]][move[1][1]] = '.';
		board[move[2][0]][move[2][1]] = 'o';
		
		pegs--;
	}

	private void undo (int[][] move) {
		//System.out.println("undo");
		
		board[move[0][0]][move[0][1]] = 'o';
		board[move[1][0]][move[1][1]] = 'o';
		board[move[2][0]][move[2][1]] = '.';
		
		pegs++;
	}
	
	private boolean legalMove(int[][] move) {
		
		if (move[2][0] >= 7 || move[2][1] >= 7 || move[2][0] < 0 || move[2][1] < 0)
			return false;
		
		return 	(board[move[0][0]][move[0][1]] == 'o') &&
				(board[move[1][0]][move[1][1]] == 'o') &&
				(board[move[2][0]][move[2][1]] == '.') ;
	}
	
	private void printBoard() {
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				System.out.print(board[i][j] + " ");
			}
			
			System.out.println();
		}
		
		System.out.println();
		
	}
	
}
