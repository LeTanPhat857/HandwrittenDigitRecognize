package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Neural implements iNeural {

	// fields
	// bias
	private double bias;

	// value after active
	private double output;

	// error
	private double biasError;

	// delta is sum of error
	private double biasDelta;

	// strategy activation
	private iActivationFunction activationFunction;

	// list connection to neural
	private List<iLink> inputLinks;

	// list connection from neural
	private List<iLink> outputLinks;

	// constructor
	public Neural() {
		this.bias = -0.5 + Math.random();
		this.activationFunction = new SigmoidFunction();
		this.inputLinks = new ArrayList<iLink>();
		this.outputLinks = new ArrayList<iLink>();
	}

	// methods
	@Override
	public void setActivationFunction(iActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

	@Override
	public void addOutputLink(iLink link) {
		this.outputLinks.add(link);
	}

	@Override
	public void addInputLink(iLink link) {
		this.inputLinks.add(link);
	}

	@Override
	public double getBias() {
		return this.bias;
	}

	@Override
	public double getOutput() {
		return this.output;
	}

	@Override
	public double getError() {
		return this.biasError;
	}

	@Override
	public void setBias(double bias) {
		this.bias = bias;
	}

	@Override
	public void setOutput(double output) {
		this.output = output;
	}

	@Override
	public void setError(double error) {
		this.biasError = error;
	}

	@Override
	public void computeOutput() {
		this.output = this.activationFunction.active(computeSum());
	}

	private double computeSum() {
		double sum = 0;

		for (iLink iLink : inputLinks) {
			sum += iLink.getWeight() * iLink.getOutputOfSourceNeural();
		}

		return sum + this.bias;
	}

	@Override
	public void computeBiasError() {
		double sumError = 0;

		for (iLink iLink : outputLinks) {
			sumError += iLink.getWeight() * iLink.getErrorOfDesNeural();
		}

		double derived = this.activationFunction.computeDerived(this.getOutput());

		this.biasError = derived * sumError;
	}

	@Override
	public void computeWeightError() {
		for (iLink iLink : inputLinks) {
			iLink.computeWeightError();
		}
	}

	@Override
	public String writeWeightAndBiasString() {
		String result = this.bias + " ";

		for (iLink iLink : outputLinks) {
			result += iLink.getWeight() + " ";
		}

		return result + "\n";
	}

	@Override
	public void readWeightAndBiasString(BufferedReader bufferedReader) throws IOException {
		String line = bufferedReader.readLine();
		StringTokenizer token = new StringTokenizer(line);

		// set bias
		if (token.hasMoreTokens()) {
			String biasString = token.nextToken();
			double bias = Double.parseDouble(biasString);
			this.bias = bias;
		}

		// set weight
		int i = 0;
		while (token.hasMoreTokens()) {
			String weightString = token.nextToken();
			double weight = Double.parseDouble(weightString);
			this.outputLinks.get(i).setWeight(weight);
			i++;
		}
	}

	@Override
	public void updateBiasDelta() {
		this.biasDelta += this.biasError;
	}

	@Override
	public void updateWeightDelta() {
		for (iLink iLink : inputLinks) {
			iLink.updateWeightDelta();
		}
	}

	@Override
	public void learnBias(double alpha, int numberExample) {
		this.bias -= (alpha / numberExample) * this.biasDelta;

		// reset bias delta
		this.biasDelta = 0;
	}

	@Override
	public void learnWeight(double alpha, int numberExample) {
		for (iLink iLink : inputLinks) {
			iLink.learnWeight(alpha, numberExample);
		}
	}
}
