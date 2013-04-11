package org.genekit;

public interface Chromosome<T> {
	public T copy();
	public T mutate(); 
}
