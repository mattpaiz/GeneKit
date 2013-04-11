package org.genekit.impl.sr;

import java.util.*;

public class TreeNode {
	
	public static TreeNode randomTreeNode(ExpressionPool pool, int maxDepth) {
		if(maxDepth <= 1) {
			return new TreeNode(pool.getRandomTerminalFunction(), new ArrayList<TreeNode>());
		} else {
			Function function = pool.getRandomFunction();
			List<TreeNode> children = new ArrayList<TreeNode>();
			
			for(int i = 0; i < function.getNumParams(); i++) {
				children.add(randomTreeNode(pool, maxDepth - 1));
			}
			
			return new TreeNode(function, children);
		}
	}
	
	private final List<TreeNode> children;
	private final Function function;
	private final String identifier;
	
	public TreeNode(Function function, List<TreeNode> children) {
		if(function.getNumParams() != children.size()) {
			throw new IllegalArgumentException("Incorrect Number of Children Nodes");
		}
		this.function = function;
		this.children = new ArrayList<TreeNode>(children);
		this.identifier = function.getName() + Arrays.toString(children.toArray(new TreeNode[0])).replace('[', '(').replace(']', ')').replaceAll("\\s", "");
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof TreeNode) {
			return ((TreeNode)o).identifier.equals(identifier);
		} else {
			return false;
		}
	}
	
	public double evaluate() {
		double[] params = new double[function.getNumParams()];
		
		for(int i = 0; i < params.length; i++) {
			params[i] = getChild(i).evaluate();
		}
		
		return function.evaluate(params);
	}

	public TreeNode getChild(int index) {
		return children.get(index);
	}
	
	public int getDepth() {
		return getDepth(0);
	}
	
	private int getDepth(int depth) {
		int numParams = function.getNumParams();

		int maxDepth = depth + 1;
		for(int i = 0; i < numParams; i++) {
			maxDepth = Math.max(getChild(i).getDepth(depth + 1), maxDepth);
		}
		
		return maxDepth;
	}
	
	public Function getFunction() {
		return function;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public int getNumOfLeaves() {
		if(isLeaf()) {
			return 1;
		}
		
		int sum = 0;
		for(TreeNode child : children) {
			sum += child.getNumOfLeaves();
		}
		return sum;
	}
	
	public int getSize() {
		int numParams = function.getNumParams();

		int sum = 1;
		for(int i = 0; i < numParams; i++) {
			sum += getChild(i).getSize();
		}
		
		return sum;
	}
	
	@Override
	public int hashCode() {
		return identifier.hashCode();
	}
	
	public boolean isLeaf() {
		return (children.size() == 0);
	}
	
	@Override
	public String toString() {
		return identifier;
	}
}
