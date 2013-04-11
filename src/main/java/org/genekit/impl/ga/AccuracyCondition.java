package org.genekit.impl.ga;

import org.genekit.kernel.Generation;
import org.genekit.kernel.TerminateCondition;

public class AccuracyCondition implements TerminateCondition<Solution> {
	
	private final int precision;
	private final SolutionFitnessFunction equation;
	
	public AccuracyCondition(int precision, SolutionFitnessFunction equation) {
		this.precision = precision;
		this.equation = equation;
	}

	@Override
	public boolean terminate(Generation<Solution> context) {
		int scaleFactor = (int) Math.pow(10, precision);
		Solution best = context.getBest().getChromosome();
		return best != null && (((int)(equation.left(best) * scaleFactor)) == ((int)(equation.right(best) * scaleFactor)));
	}
}
