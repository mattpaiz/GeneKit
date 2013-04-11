package org.genekit.impl.sr;

public abstract class AbstractFunction implements Function {
	
	public static final AbstractFunction ADDITION = new AbstractFunction("+", 2) {
		@Override
		public double evaluate(double[] args) {
			return args[0] + args[1];
		}
	};
	
	public static final AbstractFunction SUBTRACTION = new AbstractFunction("-", 2) {
		@Override
		public double evaluate(double[] args) {
			return args[0] - args[1];
		}
	};
	
	public static final AbstractFunction MULTIPLICATION = new AbstractFunction("*", 2) {
		@Override
		public double evaluate(double[] args) {
			return args[0] * args[1];
		}
	};
	
	public static final AbstractFunction DIVISION = new AbstractFunction("/", 2) {
		@Override
		public double evaluate(double[] args) {
			if(args[1] == 0) {
				return args[0];
			}
			return args[0] / args[1];
		}
	};
	
	public static final AbstractFunction SIN = new AbstractFunction("sin", 1) {
		@Override
		public double evaluate(double[] args) {
			return Math.sin(args[0]);
		}
	};
	
	public static final AbstractFunction COS = new AbstractFunction("cos", 1) {
		@Override
		public double evaluate(double[] args) {
			return Math.cos(args[0]);
		}
	};
	
	private final String name;
	private final int numParams;
	
	public AbstractFunction(String name, int numParams) {
		this.name = name;
		this.numParams = numParams;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof AbstractFunction))
			return false;
		
		return ((AbstractFunction) o).getName().equals(getName());
	}

	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getNumParams() {
		return numParams;
	}
	
	@Override
	public int hashCode() {
		return getName().hashCode();
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
