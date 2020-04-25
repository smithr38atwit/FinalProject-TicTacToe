package ticTacToe;

import java.util.ArrayList;

/**
 * Controls the decision made by the computer and stores the difficulty level.
 * 
 * @author Ryan Smith
 *
 */
public class Computer {
	
	private int difficulty; // 1 - Easy, 2 - Normal, 3 - Hard
	
	/**
	 * Default constructor to set difficulty
	 */
	public Computer() {
		difficulty = 2;
	}
	
	/**
	 * Creates computer object with custom difficulty level
	 * 
	 * @param difficulty
	 */
	public Computer(int difficulty) {
		this.difficulty = difficulty;
	}
	
	/**
	 * Determines how many points each spot has given current game board
	 * 
	 * @param game game object
	 * @return number of points per spot
	 */
	private int[] points(Game game) {
		int[] points = new int[9];
		
		//Set points for each board spot based on location. Corners get +5, center gets +10 if computer does not have advantage
		for(int i = 0; i < 9; i++) {
			if(game.getBoard()[i].equals(Integer.toString(i+1))) {
				points[i] += 5;
				if(i==0 || i==2 || i==6 || i==8)
					points[i] += 5;
				if(i==4 && game.getAdvantage()==1)
					points[i] += 10;
			}
		}
		
		//Give each spot +50 points if it will prevent player from winning, +100 points if it will cause computer to win
		for(int i = 0; i < 9; i++) {
			if(game.getBoard()[i].equals(Integer.toString(i+1))) {
				game.getBoard()[i] = "X";
				if(game.victory()[0])
					points[i] += 50;
				game.getBoard()[i] = "O";
				if(game.victory()[1])
					points[i] += 100;
				game.getBoard()[i] = Integer.toString(i+1);
			}
		}
		
		return points;
	}
	
	/**
	 * Calculates the next move based on given current game board and difficulty level
	 * 
	 * @param game game object
	 * @return position on game board for next move, -1 if difficulty is invalid
	 */
	public int nextMove(Game game) {
		int[] points = points(game);
		ArrayList<Integer> possible = new ArrayList<Integer>();
		
		if(difficulty == 1) {
			for(int i = 0; i < points.length; i++) {
				if(points[i] > 100)
					possible.add(i);
			}
			if(possible.size() > 0)
				return possible.get((int)(Math.random() * possible.size()));
			for(int i = 0; i < points.length; i++) {
				if(points[i] >= 5)
					possible.add(i);
			}
			return possible.get((int)(Math.random() * possible.size()));
		}
		
		if(difficulty == 2) {
			for(int i = 0; i < points.length; i++) {
				if(points[i] > 100)
					possible.add(i);
			}
			if(possible.size() > 0)
				return possible.get((int)(Math.random() * possible.size()));
			for(int i = 0; i < points.length; i++) {
				if(points[i] > 50)
					possible.add(i);
			}
			if(possible.size() > 0)
				return possible.get((int)(Math.random() * possible.size()));
			for(int i = 0; i < points.length; i++) {
				if(points[i] >= 5)
					possible.add(i);
			}
			return possible.get((int)(Math.random() * possible.size()));
		}
		
		if(difficulty == 3) {
			int max = points[0];
			int index = 0;
			for(int i = 0; i < points.length; i++) {
				if(points[i] > max) {
					max = points[i];
					index = i;
				}
			}
			return index;
		}
		
		return -1;
	}
	
	/**
	 * Set method to change the computer's difficulty level
	 * 
	 * @param diff difficulty level (1-3)
	 */
	public void setDifficulty(int diff) {
		difficulty = diff;
	}

}
