package org.genekit.impl.sr;

import java.util.*;

import org.genekit.*;

public class Expression implements Chromosome<Expression> {
	
	private final TreeNode root;
	private final Random random = new Random();
	private final int maxDepth;
	private final ExpressionPool pool;
	
	private boolean first = true;
	private double evaluate;
	
	public Expression(TreeNode root, ExpressionPool pool) {
		this.root = root;
		this.pool = pool;
		this.maxDepth = pool.getMaxDepth();
	}
	
	public double evaluate() {
		if(first) {
			//first = false;
			return (evaluate = root.evaluate());
		} else {
			return evaluate;
		}
	}
	
	@Override
	public Expression copy() {
		return this;
	}
	
	private TreeNode mutate(TreeNode node, TreeMarker marker, int index, int depth) { 
		
		marker.index++;
		if(marker.index - 1 == index) {
			TreeNode n = TreeNode.randomTreeNode(pool, maxDepth - depth);
			return n;
		}
		if(marker.index - 1 > index) {
			return node;
		}
		
		List<TreeNode> temp = new ArrayList<TreeNode>();
		
		for(int i = 0; i < node.getFunction().getNumParams(); i++) {
			temp.add(mutate(node.getChild(i), marker, index, depth + 1));
		}
		
		if(marker.index > index) {
			return new TreeNode(node.getFunction(), temp);
		}
		
		return node;
	}

	@Override
	public Expression mutate() {
		int mutateIndex = random.nextInt(root.getSize());
		return new Expression(mutate(root,  new TreeMarker(), mutateIndex, 0), pool);
	}
	
	@Override
	public String toString() {
		return String.format("%.2f : %s", root.evaluate(),  getPrettyOutput().replaceAll(" ", ""));
	}

	public TreeNode getRoot() {
		return root;
	}
	
	//TODO: Make Commutative and Associative Interface
	private String getPrettyOutput(TreeNode node, Function parent) {
		String output = null;
		
		if(node.getFunction().getName().equals("+") || node.getFunction().getName().equals("*") || node.getFunction().getName().equals("/") || node.getFunction().getName().equals("-")) {
			output = getPrettyOutput(node.getChild(0), node.getFunction()) + " " + node.getFunction().getName() +  " " + getPrettyOutput(node.getChild(1), node.getFunction());
			if(!node.getFunction().equals(parent) || node.getFunction().getName().equals("/") || node.getFunction().getName().equals("-")) {
				output = "(" + output + ")";
			}
		} else if(node.getFunction() instanceof PrimitiveFunction) {
			return String.format("%.2f", ((PrimitiveFunction) node.getFunction()).getValue());
		} else if(node.getFunction() instanceof IndependentVariable) {
			return node.getFunction().getName();
		} else {
			output = node.getFunction().getName() + "(";
			for(int i = 0; i < node.getFunction().getNumParams(); i++) {
				output += getPrettyOutput(node.getChild(i), node.getFunction());
				if(i < node.getFunction().getNumParams() - 1) {
					output += ",";
				}
			}
			output += ")";
		}
		
		return output;
	}
	
	public String getPrettyOutput() {
		
		String output = getPrettyOutput(root, null);
		if(output.startsWith("(")) {
			output = output.substring(1);
			if(output.endsWith(")")) {
				output = output.substring(0, output.length() - 1);
			}
		}
		return output;
	}
	
	private static class TreeMarker {
		int index = 0;
	}
}
