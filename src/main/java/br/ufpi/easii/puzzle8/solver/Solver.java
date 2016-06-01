package br.ufpi.easii.puzzle8.solver;

import java.util.List;

import br.ufpi.easii.puzzle8.model.Board;

public class Solver {
	private Board boardInicial;
	private List<Board> solution;
	
	public Solver(Board inicial) {
		this.boardInicial = inicial;
	}
	
	public void solv(){
		
	}
	
	public List<Board> getSolution(){
		return this.solution;
	}
	
	public Board getBoardInicial() {
		return boardInicial;
	}
}
