package demo;

import java.util.List;

import org.genekit.impl.sr.AbstractFunction;
import org.genekit.impl.sr.Expression;
import org.genekit.impl.sr.ExpressionMatcher;
import org.genekit.impl.sr.ExpressionPool;
import org.genekit.impl.sr.IndependentVariable;
import org.genekit.kernel.BasicCondition;
import org.genekit.kernel.Generation;
import org.genekit.kernel.BasicKernel;

public class ExpressionKernelDemo {
	
	public final static double X_MIN = -5.0;
	public final static double X_MAX = 5.0;
	public final static double X_INC = 0.05;
	
	public final static int RAND_MIN = -100; 
	public final static int RAND_MAX = 100; 
	
	public final static int DEPTH = 5;
	
	public static void main(String args[]) {
		
		ExpressionPool pool = new ExpressionPool(RAND_MIN, RAND_MAX, DEPTH)
				.addFunction(AbstractFunction.ADDITION)
				.addFunction(AbstractFunction.SUBTRACTION)
				.addFunction(AbstractFunction.MULTIPLICATION)
				.addFunction(AbstractFunction.DIVISION)
				.addFunction(AbstractFunction.SIN)
				.addFunction(IndependentVariable.byName("x"));
		
		ExpressionMatcher handler = new ExpressionMatcher(X_MIN, X_MAX, X_INC) {
			@Override
			public String print(Expression e) {
				return getError(e) + " " + e.getPrettyOutput();
			}
			
			@Override
			public double f(double x) {
				return Math.sin(3.912 * x) / 4.3 * x;
			}
		};
		
		BasicKernel<Expression> simulation = new BasicKernel<Expression>(300, pool, handler);
		List<Generation<Expression>> generations = simulation.run(new BasicCondition<Expression>(10000));
		System.out.println(generations.get(generations.size() - 1).getBest().getChromosome().getPrettyOutput());
	}
}
