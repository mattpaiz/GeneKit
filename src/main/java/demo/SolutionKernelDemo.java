package demo;

import org.genekit.impl.ga.AccuracyCondition;
import org.genekit.impl.ga.SolutionFitnessFunction;
import org.genekit.impl.ga.Solution;
import org.genekit.impl.ga.SolutionPool;
import org.genekit.kernel.BasicCondition;
import org.genekit.kernel.BasicKernel;

public class SolutionKernelDemo {
	
	public final static int NUM_PARAMS = 4;
	public final static int MIN = -50;
	public final static int MAX = 50;
	
	public final static int POPULATION_SIZE = 100;
	public final static int NUM_ITERATIONS = 200;
	
	public final static boolean USE_ACCURACY = false;
	public final static int PRECISION = 4;

	public static void main(String[] args) {
		
		SolutionPool pool = new SolutionPool(NUM_PARAMS, MIN, MAX);
		
		SolutionFitnessFunction fitness = new SolutionFitnessFunction() {
			@Override
			public String print(Solution c) {
				return c + " = " + left(c);
			}
			
			@Override
			public double right(Solution s) {
				return Math.PI;
			}
			
			@Override
			public double left(Solution s) {
				
				double a = s.param(0);
				double b = s.param(1);
				double c = s.param(2);
				double d = s.param(3);
				
				return 123.45 * d * (a  * (b * d) + b) * c / (321.0 * Math.sin(c + a));
			}
		};
		
		BasicKernel<Solution> kernel = new BasicKernel<Solution>(POPULATION_SIZE, pool, fitness);
		
		if(USE_ACCURACY) {
			kernel.run(new AccuracyCondition(PRECISION, fitness));
		} else {
			kernel.run(new BasicCondition<Solution>(NUM_ITERATIONS));
		}
	}
}
