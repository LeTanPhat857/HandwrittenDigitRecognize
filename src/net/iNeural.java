package net;

import java.io.BufferedReader;
import java.io.IOException;

public interface iNeural {

	// for neural
	public void setActivationFunction(iActivationFunction activationFunction);

	public void addOutputLink(iLink link);

	public void addInputLink(iLink link);

	// for neural
	public double getBias();

	public double getOutput();

	public double getError();

	public void setBias(double bias);

	public void setOutput(double output); // input neural

	public void setError(double error); // output neural

	// for neural
	public void computeOutput();

	public void computeBiasError();

	public void updateBiasDelta();

	public void learnBias(double alpha, int numberExample);

	// for saving and loading
	public String writeWeightAndBiasString();

	public void readWeightAndBiasString(BufferedReader bufferedReader) throws IOException;

	// for link
	public void updateWeightDelta();

	public void computeWeightError();

	public void learnWeight(double alpha, int numberExample);
}
