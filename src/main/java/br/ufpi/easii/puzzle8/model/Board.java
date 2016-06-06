/**
 * 
 */
package br.ufpi.easii.puzzle8.model;

import java.util.ArrayList;
import java.util.Arrays;
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
	private Integer totalCost;
	private Board parent;
	
	public Board(int[][] inicial, Map<Integer, Point> goal, int cost, Board parent) {
		this.numbers = inicial;
		this.goal = goal;
		this.manhattan = calculeManhattan();
		this.cost = cost;
		if(this.manhattan > 0){
			this.isGoal = false;
		}else{
			this.isGoal = true;
		}
		this.totalCost = this.manhattan + this.cost;
		this.parent = parent;
	}
	
	public Board(Board board) {
		this.cost = board.cost;
		this.goal = board.goal;
		this.isGoal = board.isGoal;
		this.manhattan = board.manhattan;
		this.neighbors = board.neighbors;
		this.numbers = board.numbers;
		this.outNumbersSum = board.outNumbersSum;
		this.parent = board.parent;
		this.totalCost = board.totalCost;
	}
	
	public Board clone(){
		return new Board(this);
	}

	
	private int calculeManhattan(){
		int sum = 0;
		for (int x = 0; x < numbers.length; x++) {
			for (int y = 0; y < numbers[x].length; y++) {
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
	
	public List<Board> expandNeighbors(){
		List<Board> neighbors = new ArrayList<Board>();
		Point emptyPosition = getEmptyPosition();
		if(emptyPosition.x > 0){
			int[][] numbers = new int[this.numbers.length][]; 
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = this.numbers[i].clone();
			}
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x-1][emptyPosition.y];
			numbers[emptyPosition.x-1][emptyPosition.y] = 0;
			neighbors.add(new Board(numbers, this.goal, this.cost+1, this));
		}
		if(emptyPosition.x < 2){
			int[][] numbers = new int[this.numbers.length][]; 
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = this.numbers[i].clone();
			}
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x+1][emptyPosition.y];
			numbers[emptyPosition.x+1][emptyPosition.y] = 0;
			neighbors.add(new Board(numbers, this.goal, this.cost+1, this));
		}
		if(emptyPosition.y > 0){
			int[][] numbers = new int[this.numbers.length][]; 
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = this.numbers[i].clone();
			}
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x][emptyPosition.y-1];
			numbers[emptyPosition.x][emptyPosition.y-1] = 0;
			neighbors.add(new Board(numbers, this.goal, this.cost+1, this));
		}
		if(emptyPosition.y < 2){
			int[][] numbers = new int[this.numbers.length][]; 
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = this.numbers[i].clone();
			}
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x][emptyPosition.y+1];
			numbers[emptyPosition.x][emptyPosition.y+1] = 0;
			neighbors.add(new Board(numbers, this.goal, this.cost+1, this));
		}
		this.neighbors = neighbors;
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

	public int getCost() {
		return cost;
	}

	public Integer getTotalCost() {
		return totalCost;
	}
	
	public Board getParent() {
		return parent;
	}

	public void setParent(Board parent) {
		this.parent = parent;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(numbers);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.deepEquals(numbers, other.numbers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers[i].length; j++) {
				sb.append(numbers[i][j] + " ");
				if(i == 0 && j == 2){
					sb.append("outNumbersSum=" + outNumbersSum + ", manhattan="
				+ manhattan + ", cost=" + cost + ", totalCost=" + totalCost);
				}
			}
			sb.append("\n");
		}
		return sb.toString();
//		return "Board [numbers=" + Arrays.toString(numbers)
//				+ ", outNumbersSum=" + outNumbersSum + ", manhattan="
//				+ manhattan + ", cost=" + cost + ", totalCost=" + totalCost
//				+ "]";
	}
	
	
	
}
