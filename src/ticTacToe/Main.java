package ticTacToe;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles program/user interaction and controls the game loop.
 * 
 * @author Ryan Smith
 *
 */
public class Main {

	/**
	 * Starts the program and controls the game loop.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean play = true;
		int choice;
		
		System.out.printf("Welcome to Tic-Tac-Toe! Choose your gamemode: %n"
				        + "1. Player vs. Computer%n"
				        + "2. Player vs. Player%n%n");
		choice = validate(input, 2);
		if(choice == 1) {
			System.out.printf("Choose difficulty level: %n"
							+ "1. Easy%n"
							+ "2. Normal%n"
							+ "3. Hard%n%n");
			Game game = new Game();
			Computer player2 = new Computer(validate(input, 3));
			do {
				game.resetBoard();
				game.advantage();
				//game.setAdvantage(1); //Test line to choose who goes first
				if(game.getAdvantage() == 1) {
					System.out.printf("Player 1 goes first%n%n");
					do {
						game.printBoard();
						game.updateBoard(validate2(input, game)-1, "X");
						if(game.outputVictory()) {
							game.printBoard();
							break;
						}
						game.updateBoard(player2.nextMove(game), "O");
						if(game.outputVictory()) {
							game.printBoard();
							break;
						}
					} while(true);
				}
				else {
					System.out.printf("Player 2 goes first%n%n");
					do {
						game.updateBoard(player2.nextMove(game), "O");
						if(game.outputVictory()) {
							game.printBoard();
							break;
						}
						game.printBoard();
						game.updateBoard(validate2(input, game)-1, "X");
						if(game.outputVictory()) {
							game.printBoard();
							break;
						}
					} while(true);
				}
				
				System.out.printf("Would you like to play again?%n"
								+ "1. Yes%n"
								+ "2. No%n%n");
				if(validate(input, 2) == 2)
					play = false;
				game.printScore();
			} while(play);
		} else if(choice == 2) {
			Game game = new Game();
			do {
				game.resetBoard();
				game.advantage();
				//game.setAdvantage(1); //Test line to choose who goes first
				if(game.getAdvantage() == 1) {
					System.out.printf("Player 1 goes first%n%n");
					do {
						game.printBoard();
						game.updateBoard(validate2(input, game)-1, "X");
						if(game.outputVictory()) {
							game.printBoard();
							break;
						}
						game.printBoard();
						game.updateBoard(validate2(input, game)-1, "O");
						if(game.outputVictory()) {
							game.printBoard();
							break;
						}
					} while(true);
				}
				else {
					System.out.printf("Player 2 goes first%n%n");
					do {
						game.printBoard();
						game.updateBoard(validate2(input, game)-1, "O");
						if(game.outputVictory()) {
							game.printBoard();
							break;
						}
						game.printBoard();
						game.updateBoard(validate2(input, game)-1, "O");
						if(game.outputVictory()) {
							game.printBoard();
							break;
						}
					} while(true);
				}
				
				System.out.printf("Would you like to play again?%n"
						+ "1. Yes%n"
						+ "2. No%n%n");
				if(validate(input, 2) == 2)
					play = false;
				game.printScore();
			} while(play);
		}
		
		input.close();
	}
	
	/**
	 * Validates user input for menu choices.
	 * 
	 * @param input scanner object
	 * @param max max integer value user can enter (range starts at 1)
	 * @return valid user input
	 */
	public static int validate(Scanner input, int max) {
		int choice;
		
		while(!input.hasNextInt()) {
			System.out.printf("Please enter a valid choice%n%n");
			input.next();
		}
		choice = input.nextInt();
		while(choice > max || choice < 1) {
			System.out.printf("Please enter a valid choice%n%n");
			choice = input.nextInt();
		}
		
		return choice;
	}
	
	/**
	 * Validates user input for adding a marker to the game board
	 * 
	 * @param input scanner object
	 * @param game game object to check current game board
	 * @return valid user input
	 */
	public static int validate2(Scanner input, Game game) {
		ArrayList<Integer> valid = new ArrayList<Integer>();
		int choice;
		
		for(int i = 0; i < 9; i++) {
			if(Character.isDigit(game.getBoard()[i].charAt(0))) {
				valid.add(Integer.parseInt(game.getBoard()[i]));
			}
		}
		
		while(!input.hasNextInt()) {
			System.out.printf("Please enter a valid choice%n%n");
			input.next();
		}
		choice = input.nextInt();
		
		boolean loop = true;
		for(int i = 0; i < valid.size(); i++) {
			if(choice == valid.get(i).intValue())
				loop = false;
		}
		while(loop) {
			System.out.printf("Please enter a valid choice%n%n");
			choice = input.nextInt();
			for(int i = 0; i < valid.size(); i++) {
				if(choice == valid.get(i).intValue())
					loop = false;
			}
		}
		
		return choice;
	}
	
}






















