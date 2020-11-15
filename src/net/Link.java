package net;

public class Link implements iLink {

	// fields
	private double weight;
	private double weightError;
	private double weightDelta;
	private iNeural sourceNeural;
	private iNeural desNeural;

	// constructor
	public Link(iNeural sourceNeural, iNeural desNeural) {
		this.weight = -0.5 + Math.random();
		this.weightDelta = 0;
		this.sourceNeural = sourceNeural;
		this.desNeural = desNeural;
	}

	// methods
	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double newWeight) {
		this.weight = newWeight;
	}

	@Override
	public double getOutputOfSourceNeural() {
		return this.sourceNeural.getOutput();
	}

	@Override
	public double getErrorOfDesNeural() {
		return this.desNeural.getError();
	}

	@Override
	public void updateWeightDelta() {
		this.weightDelta += this.weightError;
	}

	@Override
	public void computeWeightError() {
		this.weightError = this.getOutputOfSourceNeural() * this.getErrorOfDesNeural();
	}

	@Override
	public void learnWeight(double alpha, int numberExample) {
		this.weight -= (alpha / numberExample) * this.weightDelta;

		// reset weight delta
		this.weightDelta = 0;
	}
}
