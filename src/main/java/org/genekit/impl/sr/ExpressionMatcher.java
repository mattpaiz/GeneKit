package org.genekit.impl.sr;

public abstract class ExpressionMatcher extends ExpressionFitnessFunction {
	
	protected final double minX;
	protected final double maxX;
	
	protected final double incX; 
	
	public ExpressionMatcher(double minX, double maxX, double incX) {
		this.minX = minX;
		this.maxX = maxX;
		this.incX = incX;
	}
	
	public abstract double f(double x);
	
	@Override
	public double getError(Expression e) {
		double error = 0;
		for(double x = minX; x < maxX; x+=incX) {
			IndependentVariable.byName("x").setValue(x);
			double y1 = f(x);
			double y2 = e.evaluate();
			error += Math.abs(y1*y1 - y2*y2);
		}
		
		error *= (1.0 + (e.getRoot().getDepth() / 100.0));
		
		return error;
	}
}
