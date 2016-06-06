package br.ufpi.easii.puzzle8.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseBoard {
	private int[][] numbers;
	private int outNumbersSum;
	private Map<Integer, Point> goal;
	private List<BaseBoard> neighbors;
	private boolean isGoal;
	private int cost;
	private BaseBoard parent;
	
	public BaseBoard(int[][] inicial, Map<Integer, Point> goal, int cost, BaseBoard parent) {
		this.numbers = inicial;
		this.goal = goal;
		this.cost = cost;
		if(calculeManhattan() > 0){
			this.isGoal = false;
		}else{
			this.isGoal = true;
		}
		this.parent = parent;
	}
	
	public BaseBoard(BaseBoard board) {
		this.cost = board.cost;
		this.goal = new HashMap<Integer, Point>(board.goal);
		this.isGoal = board.isGoal;
		if (board.neighbors != null)
			this.neighbors = new ArrayList<BaseBoard>(board.neighbors);
		this.numbers = board.numbers;
		this.outNumbersSum = board.outNumbersSum;
		if (board.parent != null)
			this.parent = board.parent.clone();
	}
	
	public BaseBoard clone(){
		return new BaseBoard(this);
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
	
	public List<BaseBoard> expandNeighbors(){
		List<BaseBoard> neighbors = new ArrayList<BaseBoard>();
		Point emptyPosition = getEmptyPosition();
		if(emptyPosition.x > 0){
			int[][] numbers = new int[this.numbers.length][]; 
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = this.numbers[i].clone();
			}
//			numbers = this.numbers.clone();
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x-1][emptyPosition.y];
			numbers[emptyPosition.x-1][emptyPosition.y] = 0;
			neighbors.add(new BaseBoard(numbers, this.goal, this.cost+1, this));
		}
		if(emptyPosition.x < 2){
			int[][] numbers = new int[this.numbers.length][]; 
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = this.numbers[i].clone();
			}
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x+1][emptyPosition.y];
			numbers[emptyPosition.x+1][emptyPosition.y] = 0;
			neighbors.add(new BaseBoard(numbers, this.goal, this.cost+1, this));
		}
		if(emptyPosition.y > 0){
			int[][] numbers = new int[this.numbers.length][]; 
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = this.numbers[i].clone();
			}
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x][emptyPosition.y-1];
			numbers[emptyPosition.x][emptyPosition.y-1] = 0;
			neighbors.add(new BaseBoard(numbers, this.goal, this.cost+1, this));
		}
		if(emptyPosition.y < 2){
			int[][] numbers = new int[this.numbers.length][]; 
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = this.numbers[i].clone();
			}
			numbers[emptyPosition.x][emptyPosition.y] = numbers[emptyPosition.x][emptyPosition.y+1];
			numbers[emptyPosition.x][emptyPosition.y+1] = 0;
			neighbors.add(new BaseBoard(numbers, this.goal, this.cost+1, this));
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
	
	public List<BaseBoard> getNeighbors() {
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
	
	public BaseBoard getParent() {
		return parent;
	}

	public void setParent(BaseBoard parent) {
		this.parent = parent;
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
		BaseBoard other = (BaseBoard) obj;
		if (!Arrays.deepEquals(numbers, other.numbers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Board [numbers=" + Arrays.toString(numbers)
				+ ", outNumbersSum=" + outNumbersSum + ", cost=" + cost + "]";
	}
	
}
