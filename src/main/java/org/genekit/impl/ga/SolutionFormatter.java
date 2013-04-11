package org.genekit.impl.ga;

import org.genekit.ChromosomeFormatter;

public class SolutionFormatter implements ChromosomeFormatter<Solution> {
	
	private String prependZeros(String message) {
		
		StringBuilder builder = new StringBuilder(32);
		
		for(int i = 0; i < 32 - message.length(); i++) {
			builder.append("0");
		}
		
		builder.append(message);
		
		return builder.toString();
	}

	@Override
	public String format(Solution chromosome) {
		
		StringBuilder output = new StringBuilder();
		
		for(int i = 0; i < chromosome.getNumParams(); i++) {
			output.append("[" + prependZeros(Integer.toBinaryString(chromosome.dna(i))) + "]");
			if(i < chromosome.getNumParams() - 1) {
				output.append(" ");
			}
		}
		
		return output.toString();
	}
}
