package br.ufpi.easii.puzzle8.solver;

import java.util.ArrayList;
import java.util.List;

import br.ufpi.easii.puzzle8.model.Board;

public class BlindSolver {
	private Board startBoard;
	private List<Board> solution;
	
	public BlindSolver(Board startBoard) {
		this.startBoard = startBoard;
		this.solution = new ArrayList<Board>();
	}
	
	public List<Board> breadthSearch(){
		List<Board> visited = new ArrayList<Board>();
		List<Board> front = new ArrayList<Board>();
		Board current = startBoard.clone();
		visited.add(current);
		while(!current.isGoal()){
			for (Board neighbor : current.expandNeighbors()) {
				if (!visited.contains(neighbor) && !front.contains(neighbor))
					front.add(neighbor);
			}
			current = front.get(0).clone();
			front.remove(0);
			visited.add(current);
		}
		
		while(current.getParent() != null){
			this.solution.add(current);
			current = current.getParent();
		}
		solution.add(startBoard);
		return solution;
	}
	
	public List<Board> depthSearch(){
		List<Board> visited = new ArrayList<Board>();
		List<Board> front = new ArrayList<Board>();
		Board current = startBoard.clone();
		visited.add(current);
		while(!current.isGoal()){
			for (Board neighbor : current.expandNeighbors()) {
				if (!visited.contains(neighbor) && !front.contains(neighbor))
					front.add(neighbor);
			}
			current = front.get(front.size()-1).clone();
			front.remove(front.size()-1);
			visited.add(current);
		}
		
		while(current.getParent() != null){
			this.solution.add(current);
			current = current.getParent();
		}
		solution.add(startBoard);
		return solution;
	}
	
	public List<Board> getSolution(){
		return this.solution;
	}
	
	public Board getBoardInicial() {
		return startBoard;
	}
	
}
