package org.genekit;

public interface FitnessFunction<T extends Chromosome<T>> {
	public double getError(T c);
	public double getStandardizedFitness(T c);
}
