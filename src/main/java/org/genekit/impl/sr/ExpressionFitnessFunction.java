package org.genekit.impl.sr;

import org.genekit.FitnessFunction;

public abstract class ExpressionFitnessFunction implements FitnessFunction<Expression> {

	@Override
	public abstract double getError(Expression e);

	@Override
	public double getStandardizedFitness(Expression e) {
		return 1.0 / (1.0 + getError(e));
	}
}
