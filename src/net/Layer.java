package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Layer implements iLayer {

	// fields
	private List<iNeural> neurals;

	// constructor
	public Layer() {
		this.neurals = new ArrayList<iNeural>();
	}

	// methods
	@Override
	public void setNumberNeural(int number) {
		for (int i = 0; i < number; i++) {
			this.neurals.add(new Neural());
		}
	}

	@Override
	public int getNumberNeural() {
		return this.neurals.size();
	}

	@Override
	public iNeural getNeuralAt(int index) {
		return this.neurals.get(index);
	}

	@Override
	public void connectTo(iLayer nextLayer) {
		int numNeuralOfThis = this.getNumberNeural();
		int numNeuralOfNextLayer = nextLayer.getNumberNeural();

		for (int i = 0; i < numNeuralOfThis; i++) {
			iNeural neuralOfThis = this.getNeuralAt(i);

			for (int j = 0; j < numNeuralOfNextLayer; j++) {
				iNeural neuralOfNextLayer = nextLayer.getNeuralAt(j);

				iLink link = new Link(neuralOfThis, neuralOfNextLayer);

				neuralOfThis.addOutputLink(link);
				neuralOfNextLayer.addInputLink(link);
			}
		}
	}

	//
	@Override
	public double[] getOutput() {
		double[] output = new double[this.getNumberNeural()];

		for (int i = 0; i < output.length; i++) {
			output[i] = this.getNeuralAt(i).getOutput();
		}

		return output;
	}

	@Override
	public void setOutput(double[] output) {
		for (int i = 0; i < output.length; i++) {
			this.getNeuralAt(i).setOutput(output[i]);
		}
	}

	@Override
	public void setBiasError(double[] error) {
		for (int i = 0; i < error.length; i++) {
			this.getNeuralAt(i).setError(error[i]);
		}
	}

	@Override
	public void setActivationFunction(iActivationFunction activationFunction) {
		for (iNeural iNeural : neurals) {
			iNeural.setActivationFunction(activationFunction);
		}
	}

	@Override
	public void computeOutput() {
		for (iNeural iNeural : neurals) {
			iNeural.computeOutput();
		}
	}

	@Override
	public void computeBiasError() {
		for (iNeural iNeural : neurals) {
			iNeural.computeBiasError();
		}
	}

	@Override
	public void computeWeightError() {
		for (iNeural iNeural : neurals) {
			iNeural.computeWeightError();
		}
	}

	@Override
	public String writeWeightAndBiasString() {
		String result = "";

		for (iNeural iNeural : neurals) {
			result += iNeural.writeWeightAndBiasString();
		}

		return result;
	}

	@Override
	public void readWeightAndBiasString(BufferedReader bufferedReader) throws IOException {
		for (iNeural iNeural : neurals) {
			iNeural.readWeightAndBiasString(bufferedReader);
		}
	}

	@Override
	public void updateBiasDelta() {
		for (iNeural iNeural : neurals) {
			iNeural.updateBiasDelta();
		}
	}

	@Override
	public void updateWeightDelta() {
		for (iNeural iNeural : neurals) {
			iNeural.updateWeightDelta();
		}
	}

	@Override
	public void learnBias(double alpha, int numberExample) {
		for (iNeural iNeural : neurals) {
			iNeural.learnBias(alpha, numberExample);
		}
	}

	@Override
	public void learnWeight(double alpha, int numberExample) {
		for (iNeural iNeural : neurals) {
			iNeural.learnWeight(alpha, numberExample);
		}
	}
}
