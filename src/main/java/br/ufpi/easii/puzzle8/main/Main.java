package br.ufpi.easii.puzzle8.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpi.easii.puzzle8.model.Board;
import br.ufpi.easii.puzzle8.model.Point;
import br.ufpi.easii.puzzle8.solver.Solver;

public class Main {
	private static final long KILOBYTE = 1024L;

	public static long bytesToMegabytes(long bytes) {
		return bytes / KILOBYTE;
	}

	public static void main(String[] args) {
		int[][] inicial = {{1, 8, 2}, {0, 4, 3}, {7, 6, 5}};
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
		List<Board> solution = null;
		long sum = 0;
		for (int i = 0; i < 10; i++) {
			Solver solver = new Solver(boardInicial);
			long start = System.currentTimeMillis();
			solution = solver.solv();
			long end = System.currentTimeMillis();
			sum += end - start;
		}
		System.out.println("Tempo médio de Execução: " + (sum / 10)
				+ " milisegundos");
		// Get the Java runtime
	    Runtime runtime = Runtime.getRuntime();
	    // Run the garbage collector
	    runtime.gc();
	    // Calculate the used memory
	    long memory = runtime.totalMemory() - runtime.freeMemory();
	    System.out.println("Used memory is bytes: " + memory);
	    System.out.println("Used memory is kilobytes: "
	        + bytesToMegabytes(memory));
	  

		for (int i = solution.size() - 1; i >= 0; i--) {
			System.out.println(solution.get(i).toString());
			System.out.println("");
		}

	}
}
