package org.genekit.kernel;

import org.genekit.Chromosome;
import org.genekit.Individual;

public class Generation<T extends Chromosome<T>> {
	
	private final Individual<T> best;
	private final int index;
	
	public Generation(int index, Individual<T> best) {
		this.best = best;
		this.index = index;
	}
	
	public Individual<T> getBest() {
		return best;
	}
	
	public int getIndex() {
		return index;
	}
}
