package org.genekit.impl.sr;

import java.util.*;

import org.genekit.GenePool;

public class ExpressionPool implements GenePool<Expression>{
	
	private final List<Function> allFunctions = new ArrayList<Function>();
	private final List<Function> terminalFunctions = new ArrayList<Function>();
	private final Random random = new Random();
	
	private final int min;
	private final int max;
	private int maxDepth;
	
	public ExpressionPool(int min, int max, int maxDepth) {
		this.min = min;
		this.max = max;
		this.maxDepth = maxDepth;
	}
	
	public ExpressionPool addFunction(Function function) {
		
		if(allFunctions.contains(function)) {
			throw new IllegalArgumentException(""); //TODO: Fix Error Message
		}
		
		allFunctions.add(function);
		if(function.getNumParams() == 0) {
			terminalFunctions.add(function);
		}
		return this;
	}
	
	private Function getFromList(List<Function> list) {
		int index = (list.size() > 0) ? random.nextInt(list.size() + 1) : 0;
		if(index >= list.size()) {
			return new PrimitiveFunction(min + (random.nextDouble() * (max - min)));
		} else {
			return list.get(index);
		}
	}
	
	public int getMaxDepth() {
		return maxDepth;
	}
	
	public Function getRandomFunction() {
		return getFromList(allFunctions);
	}
	
	public Function getRandomTerminalFunction() {
		return getFromList(terminalFunctions);
	}

	@Override
	public Expression random() {
		return new Expression(TreeNode.randomTreeNode(this, maxDepth), this);
	}
	
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
}
