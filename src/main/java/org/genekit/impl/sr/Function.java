package org.genekit.impl.sr;

public interface Function {
	public String getName();
	public int getNumParams();
	public double evaluate(double[] args); 
}
