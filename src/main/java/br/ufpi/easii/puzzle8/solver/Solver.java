package br.ufpi.easii.puzzle8.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.ufpi.easii.puzzle8.model.Board;

public class Solver {
	private Board boardInicial;
	private List<Board> solution;
	public int count;
	public int maxFront;

	
	public Solver(Board inicial) {
		this.boardInicial = inicial;
		this.solution = new ArrayList<Board>();
		this.count = 0;
		this.maxFront = 0;
	}
	
	public List<Board> solv(){
		List<Board> visited = new ArrayList<Board>();
		List<Board> front = new ArrayList<Board>();
		Board actual = boardInicial.clone();
		visited.add(actual);
		this.count = 0;
		this.maxFront = 0;
		while(!actual.isGoal()){
			for (Board neighbor : actual.expandNeighbors()) {
				if (!visited.contains(neighbor) && !front.contains(neighbor)) {
					front.add(neighbor);
				}
			}
			sort(front);
			actual = front.get(0).clone();
			front.remove(0);
			visited.add(actual);
			count++;
			if (front.size() > maxFront) {
				maxFront = front.size();
			}
		}
		
		if(actual.isGoal()){
			while(actual.getParent() != null){
				this.solution.add(actual);
				actual = actual.getParent();
			}
			solution.add(boardInicial);
			return solution;
		}
		return null;
	}
	
	public List<Board> greedy(){
		List<Board> visited = new ArrayList<Board>();
		List<Board> front = new ArrayList<Board>();
		Board actual = boardInicial.clone();
		actual.setTotalCost(actual.getManhattan() * actual.getOutNumbersSum());
		visited.add(actual);
		this.count = 0;
		this.maxFront = 0;
		while(!actual.isGoal()){
			for (Board neighbor : actual.expandNeighbors()) {
				if (!visited.contains(neighbor) && !front.contains(neighbor)) {
					neighbor.setTotalCost(neighbor.getManhattan() * neighbor.getOutNumbersSum());
					front.add(neighbor);
				}
			}
			sort(front);
			actual = front.get(0).clone();
			front.remove(0);
			visited.add(actual);
			count++;
			if (front.size() > maxFront) {
				maxFront = front.size();
			}
		}
		
		if(actual.isGoal()){
			while(actual.getParent() != null){
				this.solution.add(actual);
				actual = actual.getParent();
			}
			solution.add(boardInicial);
			return solution;
		}
		
		return null;
	}
	
	public List<Board> breadthSearch(){
		List<Board> visited = new ArrayList<Board>();
		List<Board> front = new ArrayList<Board>();
		Board actual = boardInicial.clone();
		actual.setTotalCost(actual.getManhattan() * actual.getOutNumbersSum());
		visited.add(actual);
		this.count = 0;
		this.maxFront = 0;
		while(!actual.isGoal()){
			for (Board neighbor : actual.expandNeighbors()) {
				if (!visited.contains(neighbor) && !front.contains(neighbor)) {
					front.add(neighbor);
				}
			}
			actual = front.get(0).clone();
			front.remove(0);
			visited.add(actual);
			count++;
			if (front.size() > maxFront) {
				maxFront = front.size();
			}
		}
		
		if(actual.isGoal()){
			while(actual.getParent() != null){
				this.solution.add(actual);
				actual = actual.getParent();
			}
			solution.add(boardInicial);
			return solution;
		}
		
		return null;
	}
	
	public List<Board> depthSearch(){
		List<Board> visited = new ArrayList<Board>();
		List<Board> front = new ArrayList<Board>();
		Board actual = boardInicial.clone();
		actual.setTotalCost(actual.getManhattan() * actual.getOutNumbersSum());
		visited.add(actual);
		this.count = 0;
		this.maxFront = 0;
		while(!actual.isGoal()){
			for (Board neighbor : actual.expandNeighbors()) {
				if (!visited.contains(neighbor) && !front.contains(neighbor)) {
					front.add(neighbor);
				}
			}
			actual = front.get(front.size()-1).clone();
			front.remove(front.size()-1);
			visited.add(actual);
			count++;
			if (front.size() > maxFront) {
				maxFront = front.size();
			}
		}
		
		if(actual.isGoal()){
			while(actual.getParent() != null){
				this.solution.add(actual);
				actual = actual.getParent();
			}
			solution.add(boardInicial);
			return solution;
		}
		
		return null;
	}
	
	public void sort(List<Board> boards){
		Collections.sort(boards, new Comparator<Board>() {
			@Override
			public int compare(Board o1, Board o2) {
				return o1.getTotalCost().compareTo(o2.getTotalCost());
			}
		});
	}
	
	public List<Board> getSolution(){
		return this.solution;
	}
	
	public Board getBoardInicial() {
		return boardInicial;
	}
}
