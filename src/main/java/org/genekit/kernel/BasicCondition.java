package org.genekit.kernel;

import org.genekit.Chromosome;

public class BasicCondition<T extends Chromosome<T>> implements TerminateCondition<T> {
	
	private final int maxGenerations;
	
	public BasicCondition(int maxGenerations) {
		this.maxGenerations = maxGenerations;
	}

	@Override
	public boolean terminate(Generation<T> context) {
		return context.getIndex() >= maxGenerations;
	}
}
