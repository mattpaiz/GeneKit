package org.genekit.impl.ga;

import java.math.*;
import java.util.*;

import org.genekit.*;

public class Solution implements Chromosome<Solution> {
	
	private final int[] dna;
	private final double[] value;
	private final Random random = new Random();
	private final int min, max;
	
	public Solution(int[] dna, int min, int max) {
		this.dna = dna;
		this.min = min;
		this.max = max;
		
		value = new double[dna.length];
		
		for(int i = 0; i < dna.length; i++) {
			BigDecimal data = BigDecimal.valueOf(Math.abs(dna[i]));
			BigDecimal themax = BigDecimal.valueOf(Integer.MAX_VALUE);
			BigDecimal themin = BigDecimal.valueOf(min);
			BigDecimal sub = BigDecimal.valueOf((double)(max - min));
		
			value[i] = data.divide(themax, 10, BigDecimal.ROUND_HALF_UP).multiply(sub).add(themin).doubleValue();
		}
	}
	
	public double param(int index) {
		return value[index];
	}
	
	public int dna(int index) {
		return dna[index];
	}
	
	@Override
	public String toString() {
		return Arrays.toString(value);
	}

	@Override
	public Solution copy() {
		return this;
	}
	
	public int getNumParams() {
		return dna.length;
	}

	@Override
	public Solution mutate() {
		int[] child = new int[dna.length];
		for(int i = 0; i < dna.length; i++) {
			for(int j = 0; j < 3; j++) {
				child[i] = dna[i] ^ ((int) (1 << random.nextInt(31)));
			}
		}
		
		return new Solution(child, min, max);
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Solution)) {
			return false;
		} else {
			return Arrays.equals(((Solution) o).dna, dna);
		}
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(dna);
	}
}
