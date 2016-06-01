/**
 * 
 */
package br.ufpi.easii.puzzle8.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ronyerison
 *
 */
public class Board {
	private int[][] numbers;
	private int outNumbersSum;
	private int manhattan;
	private Map<Integer, Point> goal;
	private List<Board> neighbors;
	private boolean isGoal;
	private int cost;
	
	public Board(int[][] inicial, Map<Integer, Point> goal, int cost) {
		this.numbers = inicial;
		this.goal = goal;
		this.manhattan = calculeManhattan();
		this.cost = cost;
		if(this.manhattan > 0){
			this.isGoal = false;
		}else{
			this.isGoal = true;
		}
		if(!isGoal){
			this.neighbors = expandNeighbors();
		}
	}
	
	private int calculeManhattan(){
		int sum = 0;
		for (int x = 0; x < numbers.length; x++) {
			for (int y = 0; y < numbers.length; y++) {
				Point p = goal.get(numbers[x][y]);
				int distance = Math.abs(x - p.x) + Math.abs(y - p.y);
				sum += distance;
				if(distance > 0){
					outNumbersSum++;
				}
			}
		}
		return sum;
	}
	
	private List<Board> expandNeighbors(){
		List<Board> neighbors = new ArrayList<Board>();
		Point emptyPosition = getEmptyPosition();
		if(emptyPosition.x > 0){
			int[][] numbers = this.numbers;
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x-1][emptyPosition.y];
			numbers[emptyPosition.x-1][emptyPosition.y] = 0;
			neighbors.add(new Board(numbers, this.goal, this.cost+1));
		}
		if(emptyPosition.x < 2){
			int[][] numbers = this.numbers;
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x+1][emptyPosition.y];
			numbers[emptyPosition.x+1][emptyPosition.y] = 0;
			neighbors.add(new Board(numbers, this.goal, this.cost+1));
		}
		if(emptyPosition.y > 0){
			int[][] numbers = this.numbers;
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x][emptyPosition.y-1];
			numbers[emptyPosition.x][emptyPosition.y-1] = 0;
			neighbors.add(new Board(numbers, this.goal, this.cost+1));
		}
		if(emptyPosition.x < 2){
			int[][] numbers = this.numbers;
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x][emptyPosition.y+1];
			numbers[emptyPosition.x][emptyPosition.y+1] = 0;
			neighbors.add(new Board(numbers, this.goal, this.cost+1));
		}
		
		return neighbors;
	}
	
	private Point getEmptyPosition(){
		for (int x = 0; x < numbers.length; x++) {
			for (int y = 0; y < numbers.length; y++) {
				if (numbers[x][y] == 0) {
					return new Point(x, y);
				}
			}
		}
		return null;
	}
	
	public int[][] getNumbers() {
		return numbers;
	}
	
	public int getOutNumbersSum() {
		return outNumbersSum;
	}
	
	public int getManhattan() {
		return manhattan;
	}
	
	public List<Board> getNeighbors() {
		return neighbors;
	}

	public Map<Integer, Point> getGoal() {
		return goal;
	}

	public boolean isGoal() {
		return isGoal;
	}
	
	
}
