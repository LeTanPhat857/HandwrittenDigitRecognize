package net;

public interface iLink {

	//
	public double getWeight();

	public void setWeight(double newWeight);

	public double getOutputOfSourceNeural();

	public double getErrorOfDesNeural();

	public void computeWeightError();

	public void updateWeightDelta();

	public void learnWeight(double alpha, int numberExample);
}
