package br.ufpi.easii.puzzle8.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.ufpi.easii.puzzle8.model.Board;

public class Solver {
	private Board boardInicial;
	private List<Board> solution;
	
	public Solver(Board inicial) {
		this.boardInicial = inicial;
	}
	
	public List<Board> solv(){
		List<Board> visited = new ArrayList<Board>();
		List<Board> front = new ArrayList<Board>();
		Board actual = boardInicial.clone();
		while(!actual.isGoal()){
			for (Board neighbor : actual.expandNeighbors()) {
				if (!visited.contains(neighbor) || !front.contains(neighbor)) {
					front.add(neighbor);
				}
			}
			sort(front);
			actual = front.get(0).clone();
			front.remove(0);
			visited.add(actual);
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
