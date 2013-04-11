package org.genekit;

public interface GenePool<T extends Chromosome<T>> {
	public T random();
}
