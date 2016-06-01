package br.ufpi.easii.puzzle8.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpi.easii.puzzle8.model.Board;
import br.ufpi.easii.puzzle8.model.Point;
import br.ufpi.easii.puzzle8.solver.Solver;

public class Main {
	public static void main(String[] args) {
		int[][] inicial = {{2,7,0},{3,1,8},{4,6,5}};
		Map<Integer, Point> goal = new HashMap<Integer, Point>();
		goal.put(1, new Point(0, 0));
		goal.put(2, new Point(0, 1));
		goal.put(3, new Point(0, 2));
		goal.put(4, new Point(1, 0));
		goal.put(5, new Point(1, 1));
		goal.put(6, new Point(1, 2));
		goal.put(7, new Point(2, 0));
		goal.put(8, new Point(2, 1));
		goal.put(0, new Point(2, 2));
		
		Board boardInicial = new Board(inicial, goal, 0, null);
		Solver solver = new Solver(boardInicial);
		
		List<Board> solution = solver.solv();
		System.out.println(Arrays.toString(solution.toArray()));
	}
}
