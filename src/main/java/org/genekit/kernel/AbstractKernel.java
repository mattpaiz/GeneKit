package org.genekit.kernel;

import java.util.*;

import org.genekit.Chromosome;
import org.genekit.FitnessFunction;
import org.genekit.GenePool;
import org.genekit.Individual;

public abstract class AbstractKernel<T extends Chromosome<T>> {
	
	protected final double DEFAULT_MUTATE_PROBABILITY = 0.25;
	
	protected final int size;
	protected final GenePool<T> pool;
	protected final FitnessFunction<T> function;
	protected final Random random = new Random();
	
	public AbstractKernel(int size, GenePool<T> pool, FitnessFunction<T> function) {
		this.size = size;
		this.pool = pool;
		this.function = function;
	}
	
	protected abstract void output(Generation<T> generation);
	
	protected Individual<T> select(List<Individual<T>> current) {
		
		double totalFitness = 0;
		
		for(Individual<T> i : current) {
			totalFitness += i.getFitness();
		}
		
		double sum = 0;
		double r = random.nextDouble();
		
		int j = 0;
		Individual<T> parent = null;
		while(j < current.size() && r > (sum += ((parent = current.get(j++)).getFitness() / totalFitness)));
		return parent;
	}
	
	public List<Generation<T>> run(TerminateCondition<T> indicator) {
		return run(indicator, DEFAULT_MUTATE_PROBABILITY);
	}
	
	public List<Generation<T>> run(TerminateCondition<T> indicator, double mutateProb) {
		
		List<Generation<T>> generations= new ArrayList<Generation<T>>();
		List<Individual<T>> current = new ArrayList<Individual<T>>();
		
		int generationIndex = 0;
		
		for(int i = 0; i < size; i++) {
			T c = pool.random();
			current.add(new Individual<T>(c, function.getStandardizedFitness(c)));
		}
		
		Individual<T> best = Collections.max(current);
		Generation<T> generation = new Generation<T>(generationIndex++, best);
		
		generations.add(generation);
			
		while(!indicator.terminate(generation)) {
			
			List<Individual<T>> next = new ArrayList<Individual<T>>();
			
			for(int c = 0; c < current.size() - 1; c++) {
				
				Individual<T> parent = select(current);
				
				if(c < (current.size() * mutateProb)) {
					T child = parent.getChromosome().mutate();
					next.add(new Individual<T>(child, function.getStandardizedFitness(child)));
				} else {
					next.add(parent.copy());
				}
			}
			next.add(best);
			
			current = next;
			
			best = Collections.max(current);
			generation = new Generation<T>(generationIndex++, best);
			generations.add(generation);
			output(generation);
		}
		
		return generations;
	}
}

