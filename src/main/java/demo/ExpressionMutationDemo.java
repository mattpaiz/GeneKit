package demo;

import org.genekit.impl.sr.AbstractFunction;
import org.genekit.impl.sr.Expression;
import org.genekit.impl.sr.ExpressionPool;

public class ExpressionMutationDemo {
	
	public final static int MIN = -100;
	public final static int MAX = 100;
	
	public final static int DEPTH = 5;
	
	public static void main(String args[]) {
		
		ExpressionPool pool = new ExpressionPool(MIN, MAX, DEPTH);
		
		pool.addFunction(AbstractFunction.ADDITION)
			.addFunction(AbstractFunction.SUBTRACTION)
			.addFunction(AbstractFunction.MULTIPLICATION);
		
		Expression parent = pool.random();
		
		for(int i = 0; i < 100; i++) {
			System.out.println(parent);
			parent = parent.mutate();
		}
	}
}
