package net;

public class SigmoidFunction implements iActivationFunction {

	@Override
	public double active(double sum) {
		return 1 / (1 + Math.pow(Math.E, -sum));
	}

	@Override
	public double computeDerived(double output) {
		return output * (1 - output);
	}

}
