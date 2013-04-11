package demo;

import org.genekit.impl.ga.Solution;
import org.genekit.impl.ga.SolutionFormatter;
import org.genekit.impl.ga.SolutionPool;

public class SolutionMutationDemo {
	
	public final static int MIN = -100;
	public final static int MAX = 100;
	
	public final static int NUM_PARAMS = 1;
	
	public static void main(String args[]) {
		
		SolutionPool pool = new SolutionPool(NUM_PARAMS, MIN, MAX);
		
		Solution parent = pool.random();
		
		SolutionFormatter binary = new SolutionFormatter();
		
		for(int i = 0; i < 100; i++) {
			System.out.format("%s -> %.4f\n", binary.format(parent), parent.param(0));
			parent = parent.mutate();
		}
	}
}
