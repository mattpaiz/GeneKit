package org.genekit.kernel;

import org.genekit.Chromosome;

public interface TerminateCondition<T extends Chromosome<T>> {
	public boolean terminate(Generation<T> context);
}
