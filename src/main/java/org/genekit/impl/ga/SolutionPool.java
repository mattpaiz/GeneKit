package org.genekit.impl.ga;

import java.util.*;

import org.genekit.*;

public class SolutionPool implements GenePool<Solution> {
	
	private final Random random = new Random();
	
	private final int min;
	private final int max;
	private final int params;
	
	public SolutionPool(int params, int min, int max) {
		this.min = min;
		this.max = max;
		this.params = params;
	}
	
	@Override
	public Solution random() {
		
		int[] dna = new int[params];
		
		for(int i = 0; i < params; i++) {
			dna[i] = random.nextInt(Integer.MAX_VALUE);
		}
		return new Solution(dna, min, max);
	}
	
}
