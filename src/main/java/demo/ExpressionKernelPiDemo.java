package demo;

import org.genekit.impl.sr.AbstractFunction;
import org.genekit.impl.sr.Expression;
import org.genekit.impl.sr.ExpressionMatcher;
import org.genekit.impl.sr.ExpressionPool;
import org.genekit.impl.sr.IndependentVariable;
import org.genekit.kernel.AbstractKernel;
import org.genekit.kernel.BasicCondition;
import org.genekit.kernel.Generation;

public class ExpressionKernelPiDemo {
	
	public final static double X_MIN = -5.0;
	public final static double X_MAX = 5.0;
	public final static double X_INC = 1;
	
	public final static int RAND_MIN = -100; 
	public final static int RAND_MAX = 100; 
	
	public final static int DEPTH = 6;
	
	public final static int POPULATION_SIZE = 300;
	
	public static void main(String args[]) {
		
		ExpressionPool pool = new ExpressionPool(RAND_MIN, RAND_MAX, DEPTH, false)
				.addFunction(AbstractFunction.ADDITION)
				.addFunction(AbstractFunction.DIVISION)
				.addFunction(IndependentVariable.byName("x"));
		
		final ExpressionMatcher handler = new ExpressionMatcher(X_MIN, X_MAX, X_INC) {
			@Override
			public double f(double x) {
				return Math.PI;
			}
		};
		
		
		AbstractKernel<Expression> simulation = new AbstractKernel<Expression>(POPULATION_SIZE, pool, handler) {
			@Override
			protected void output(Generation<Expression> generation) {
				Expression e = generation.getBest().getChromosome();
				System.out.println(handler.getError(e) + ":  " + e);
			}
		};
		
		simulation.run(new BasicCondition<Expression>(10000));
	}
}
