package br.ufpi.easii.puzzle8.solver;

import java.util.ArrayList;
import java.util.List;

import br.ufpi.easii.puzzle8.model.BaseBoard;

public class BlindSolver {
	private BaseBoard startBoard;
	private List<BaseBoard> solution;
	
	public BlindSolver(BaseBoard startBoard) {
		this.startBoard = startBoard;
		this.solution = new ArrayList<BaseBoard>();
	}
	
	public List<BaseBoard> breadthSearch(){
		List<BaseBoard> visited = new ArrayList<BaseBoard>();
		List<BaseBoard> front = new ArrayList<BaseBoard>();
		BaseBoard current = startBoard.clone();
		visited.add(current);
		while(!current.isGoal()){
			for (BaseBoard neighbor : current.expandNeighbors()) {
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
	
	public List<BaseBoard> depthSearch(){
		List<BaseBoard> visited = new ArrayList<BaseBoard>();
		List<BaseBoard> front = new ArrayList<BaseBoard>();
		BaseBoard current = startBoard.clone();
		visited.add(current);
		while(!current.isGoal()){
			for (BaseBoard neighbor : current.expandNeighbors()) {
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
	
	public List<BaseBoard> getSolution(){
		return this.solution;
	}
	
	public BaseBoard getBoardInicial() {
		return startBoard;
	}
	
}
