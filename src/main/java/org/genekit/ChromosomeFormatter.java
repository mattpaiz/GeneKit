package org.genekit;

public interface ChromosomeFormatter<T extends Chromosome<T>> {
	public String format(T chromosome); 
}
