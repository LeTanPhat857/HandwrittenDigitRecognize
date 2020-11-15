package net;

import java.io.BufferedReader;
import java.io.IOException;

public interface iLayer {

	// for layer
	public void setNumberNeural(int number);

	public int getNumberNeural();

	public iNeural getNeuralAt(int index);

	public void connectTo(iLayer nextLayer);

	// for layer
	public void setOutput(double[] output); // input layer

	public double[] getOutput(); // output layer

	public void setBiasError(double[] error); // output layer

	// for saving and loading
	public String writeWeightAndBiasString();

	public void readWeightAndBiasString(BufferedReader bufferedReader) throws IOException;

	// for neural
	public void setActivationFunction(iActivationFunction activationFunction);

	public void computeOutput();

	public void computeBiasError();

	public void updateBiasDelta();

	public void learnBias(double alpha, int numberExample);

	public void computeWeightError();

	public void updateWeightDelta();

	public void learnWeight(double alpha, int numberExample);

}
