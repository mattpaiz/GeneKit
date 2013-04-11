package org.genekit.impl.sr;

import java.util.*;

public class IndependentVariable extends AbstractFunction {
	
	private double value;
	private final static Map<String,IndependentVariable> map = new Hashtable<String,IndependentVariable>();

	private IndependentVariable(String name) {
		super(name, 0);
		value = 0.0;
	}
	
	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public double evaluate(double[] args) {
		return value;
	}
	
	public static IndependentVariable byName(String name) {
		if(!map.containsKey(name)) {
			map.put(name, new IndependentVariable(name));
		}
		return map.get(name);
	}
}
