package org.genekit.impl.sr;

public class PrimitiveFunction implements Function {
	
	private final double value;
	
	public PrimitiveFunction(double value) {
		this.value = value;
	}

	@Override
	public String getName() {
		return String.format("%.4f", value);
	}

	@Override
	public int getNumParams() {
		return 0;
	}
	
	public double getValue() {
		return value;
	}

	@Override
	public double evaluate(double[] args) {
		return value;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof PrimitiveFunction) {
			return (Double.compare(((PrimitiveFunction) o).value, value) == 0);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
