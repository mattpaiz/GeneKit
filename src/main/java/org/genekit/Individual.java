package org.genekit;

public class Individual<T extends Chromosome<T>> implements Comparable<Individual<T>> {
	
	private final T chromosome;
	private final double fitness;
	
	public Individual(T chromosome, double fitness) {
		this.chromosome = chromosome;
		this.fitness = fitness;
	}
	
	@Override
	public int compareTo(Individual<T> individual) {
		return Double.valueOf(fitness).compareTo(individual.fitness);
	}
	
	public Individual<T> copy() {
		return new Individual<T>(chromosome, fitness);
	}
	
	public T getChromosome() {
		return chromosome;
	}
	
	public double getFitness() {
		return fitness;
	}

	@Override
	public String toString() {
		return chromosome.toString(); 
	}
}