package org.genekit.impl.ga;

import org.genekit.FitnessFunction;

public abstract class SolutionFitnessFunction implements FitnessFunction<Solution> {
	
	public abstract double left(Solution s);
	public abstract double right(Solution s);
	
	@Override
	public double getError(Solution s) {
		return Math.abs(left(s) - right(s));
	}
	
	@Override
	public double getStandardizedFitness(Solution s) {
		return 1.0 / (1.0 + getError(s));
	}
}
