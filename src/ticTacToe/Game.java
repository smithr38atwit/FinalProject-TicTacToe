package ticTacToe;

/**
 * Controls the game board and score, and checks for victory.
 * 
 * @author Ryan Smith
 *
 */
public class Game {
	
	private String[] board = new String[9];
	private int[] score; //[Player 1, Player 2]
	private int advantage; //Determines who goes first, 1 for player one, 2 for player two
	
	/**
	 * Default contructor to initialize empty game board
	 */
	public Game() {
		this.resetBoard();
		score = new int[] {0, 0};
	}
	
	//**Test constructor**
	public Game(String[] arr, int advantage) {
		for(int i = 0; i < arr.length; i++)
			board[i] = arr[i];
		this.advantage = advantage;
		score = new int[] {0, 0};
	}
	
	/**
	 * Returns false/false if no player has won, true/false if player one has won, false/true if player two has won, and true/true for a tie
	 * 
	 * @return victory status for each player
	 */
	public boolean[] victory() {
		boolean[] victory = new boolean[] {false, false};
		
		if((board[0].equals("X") && board[1].equals("X") && board[2].equals("X")) ||
		   (board[3].equals("X") && board[4].equals("X") && board[5].equals("X")) ||
		   (board[6].equals("X") && board[7].equals("X") && board[8].equals("X")) ||
		   (board[0].equals("X") && board[3].equals("X") && board[6].equals("X")) ||
		   (board[1].equals("X") && board[4].equals("X") && board[7].equals("X")) ||
		   (board[2].equals("X") && board[5].equals("X") && board[8].equals("X")) || 
		   (board[0].equals("X") && board[4].equals("X") && board[8].equals("X")) ||
		   (board[2].equals("X") && board[4].equals("X") && board[6].equals("X"))) {
			victory[0] = true;	
		} else if((board[0].equals("O") && board[1].equals("O") && board[2].equals("O")) ||
				  (board[3].equals("O") && board[4].equals("O") && board[5].equals("O")) ||
				  (board[6].equals("O") && board[7].equals("O") && board[8].equals("O")) ||
				  (board[0].equals("O") && board[3].equals("O") && board[6].equals("O")) ||
				  (board[1].equals("O") && board[4].equals("O") && board[7].equals("O")) ||
				  (board[2].equals("O") && board[5].equals("O") && board[8].equals("O")) || 
				  (board[0].equals("O") && board[4].equals("O") && board[8].equals("O")) ||
				  (board[2].equals("O") && board[4].equals("O") && board[6].equals("O"))) {
			victory[1] = true;
		} else if(full(0) && full(1) && full(2) &&
				  full(3) && full(4) && full(5) &&
				  full(6) && full(7) && full(8)) { 
			victory[0] = true;
			victory[1] = true;
		}
		
		return victory;
	}
	
	private boolean full(int i) {
		if(board[i].equals("X") || board[i].equals("O"))
			return true;
		else
			return false;
	}
	
	/**
	 * Outputs a victory message for different conditions
	 * 
	 * @return true if message was output, false otherwise
	 */
	public boolean outputVictory() {
		boolean[] victory = victory();
		if(victory[0] && !victory[1]) {
			System.out.printf("Player 1 wins!%n%n");
			score[0]++;
			return true;
		}
		else if(!victory[0] && victory[1]) {
			System.out.printf("Player 2 wins!%n%n");
			score[1]++;
			return true;
		}
		else if(victory[0] && victory[1]) {
			System.out.printf("It's a tie!%n%n");
			return true;
		}
		return false;
	}
	
	/**
	 * Decides which player goes first, thus who has the advantage.
	 * 
	 * @return 1 for player 1, 2 for player 2
	 */
	public void advantage() {
		advantage = (int)(Math.random() * 2) + 1;
	}
	
	/**
	 * Gets which player has advantage
	 * 
	 * @return advantage
	 */
	public int getAdvantage() {
		return advantage;
	}
	
	//**Test Method**
	public void setAdvantage(int a) {
		advantage = a;
	}
	
	/**
	 * Prints the tic-tac-toe board
	 * 
	 * @param board string representation of each board spot
	 */
	public void printBoard() {
		System.out.printf(" %s | %s | %s%n" +
	                      "-----------%n" +
				          " %s | %s | %s%n" +
				          "-----------%n" +
				          " %s | %s | %s%n%n", board[0], board[1], board[2], board[3], board[4], board[5], board[6], board[7], board[8]);
	}
	
	/**
	 * Gets the game board
	 * 
	 * @return current game board
	 */
	public String[] getBoard() {
		return board;
	}
	
	/**
	 * Updates a spot on the game board
	 * 
	 * @param index spot to update
	 * @param marker "x" or "o"
	 */
	public void updateBoard(int index, String marker) {
		board[index] = marker;
	}
	
	/**
	 * Resets game board to empty spots
	 */
	public void resetBoard() {
		for(int i = 0; i < board.length; i++)
			board[i] = Integer.toString(i+1);
	}
	
	public void printScore() {
		System.out.printf("Player 1: %d, Player 2: %d%n%n", score[0], score[1]);
	}

}















