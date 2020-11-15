package net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class Net implements iNet {

	// fields
	private List<iLayer> layers;

	// constructor
	public Net() {
		this.layers = new ArrayList<iLayer>();
	}

	// methods
	@Override
	public int getNumberLayer() {
		return this.layers.size();
	}

	@Override
	public iLayer getLayerAt(int index) {
		return this.layers.get(index);
	}

	
	@Override
	public void setActiveMethod(iActivationFunction activationFunction) {
		for (int i = 1; i < layers.size() - 1; i++) {
			this.getLayerAt(i).setActivationFunction(activationFunction);
		}
	}

	@Override
	public void addLayer(int numberNeural) {
		iLayer newLayer = new Layer();
		newLayer.setNumberNeural(numberNeural);
		this.layers.add(newLayer);
	}

	@Override
	public void createNet() {
		this.connect();
	}

	private void connect() {
		for (int i = 0; i < this.getNumberLayer() - 1; i++) {
			this.getLayerAt(i).connectTo(this.getLayerAt(i + 1));
		}
	}

	//
	@Override
	public double[] predict(double[] data) throws Exception {
		this.setInputData(data);
		this.computeOutput();
		return this.getOutput();
	}

	private void setInputData(double[] data) {
		iLayer inputLayer = this.getLayerAt(0);
		inputLayer.setOutput(data);
	}

	private void computeOutput() {
		for (int i = 1; i < this.getNumberLayer(); i++) {
			this.getLayerAt(i).computeOutput();
		}
	}

	private double[] getOutput() {
		iLayer outputLayer = this.getLayerAt(this.getNumberLayer() - 1);
		return outputLayer.getOutput();
	}

	@Override
	public void train(double[][] dataSet, double[][] labelSet, int epoch, int miniBatch, double alpha)
			throws Exception {
		// for 1 epoch
		for (int i = 0; i < epoch; i++) {
			System.out.println("epoch: " + i);

			// shuffle training data
			this.shuffleTrainingData(dataSet, labelSet);

			// for 1 miniBatch
			for (int j = 0; j < dataSet.length; j += miniBatch) {
				// creating miniBatch
				double[][] dataBatch = Arrays.copyOfRange(dataSet, j, j + miniBatch);
				double[][] labelBatch = Arrays.copyOfRange(labelSet, j, j + miniBatch);

				// training miniBatch
				trainMiniBatch(dataBatch, labelBatch, alpha);
			}
		}
	}

	private void shuffleTrainingData(double[][] dataSet, double[][] labelSet) {
		int len = dataSet.length;

		for (int i = 0; i < len; i++) {
			int randNum = new Random().nextInt(len);

			double[] tempData = dataSet[i];
			dataSet[i] = dataSet[randNum];
			dataSet[randNum] = tempData;

			double[] tempLabel = labelSet[i];
			labelSet[i] = labelSet[randNum];
			labelSet[randNum] = tempLabel;
		}
	}

	private void trainMiniBatch(double[][] dataBatch, double[][] labelBatch, double alpha) throws Exception {
		// length of miniBatch
		int numberExample = dataBatch.length;
		int numberLayer = this.getNumberLayer();
		iLayer outputLayer = this.getLayerAt(numberLayer - 1);

		// for 1 data and 1 label in miniBatch
		for (int i = 0; i < numberExample; i++) {
			// step 1. forward propagation
			double[] predictVec = predict(dataBatch[i]);
			double[] desiredVec = labelBatch[i];

//			System.out.println("cost: " + this.computeCost(predictVec, desiredVec));

			// step 2. back propagation
			// step 2.1. set errorVec for outputLayer
			double[] errorVec = this.computeBiasErrorForOuput(predictVec, desiredVec);
			outputLayer.setBiasError(errorVec);
			outputLayer.updateBiasDelta();
			outputLayer.computeWeightError();
			outputLayer.updateWeightDelta();

			// step 2.2. compute delta for all layer (not last layer and first layer)
			for (int j = numberLayer - 2; j > 0; j--) {
				this.getLayerAt(j).computeBiasError();
				this.getLayerAt(j).updateBiasDelta();
				this.getLayerAt(j).computeWeightError();
				this.getLayerAt(j).updateWeightDelta();
			}
		}

		// step 3. gradient descent
		for (int j = numberLayer - 1; j > 0; j--) {
			this.getLayerAt(j).learnBias(alpha, numberExample);
			this.getLayerAt(j).learnWeight(alpha, numberExample);
		}
	}

	private double computeCost(double[] predictVec, double[] desiredVec) {
		double result = 0;

		for (int i = 0; i < desiredVec.length; i++) {
			result += -desiredVec[i] * Math.log10(predictVec[i]) - (1 - desiredVec[i]) * Math.log10(1 - predictVec[i]);
		}

		return result;
	}

	private double[] computeBiasErrorForOuput(double[] predictVec, double[] desiredVec) {
		int length = predictVec.length;
		double[] result = new double[length];

		for (int i = 0; i < length; i++) {
			result[i] = (predictVec[i] - desiredVec[i]) * predictVec[i] * (1 - predictVec[i]);
		}

		return result;
	}

	@Override
	public void saveCurrentNet(String pathname) throws IOException {
		File file = new File(pathname);
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		this.writeLayer(bufferedWriter);
		this.writeWeightAndBias(bufferedWriter);

		bufferedWriter.close();
	}

	private void writeLayer(BufferedWriter bufferedWriter) throws IOException {
		for (iLayer iLayer : this.layers) {
			int numberNeural = iLayer.getNumberNeural();

			bufferedWriter.write(String.valueOf(numberNeural));
			bufferedWriter.write(' ');
		}
		bufferedWriter.write('\n');
	}

	private void writeWeightAndBias(BufferedWriter bufferedWriter) throws IOException {
		int numberLayer = this.getNumberLayer();

		for (int i = 0; i < numberLayer; i++) {
			iLayer layer = this.getLayerAt(i);
			String info = layer.writeWeightAndBiasString();
			bufferedWriter.write(info);
		}
	}

	@Override
	public void loadTrainedNet(String trainedNetPath) throws Exception {
		File file = new File(trainedNetPath);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		this.readLayer(bufferedReader);
		this.createNet();
		this.readWeightAndBias(bufferedReader);

		bufferedReader.close();
	}

	private void readLayer(BufferedReader bufferedReader) throws IOException {
		String line = bufferedReader.readLine();
		StringTokenizer tokenizer = new StringTokenizer(line);

		while (tokenizer.hasMoreTokens()) {
			String numberLayerString = tokenizer.nextToken();
			int numberNeural = Integer.parseInt(numberLayerString);
			this.addLayer(numberNeural);
		}
	}

	private void readWeightAndBias(BufferedReader bufferedReader) throws IOException {
		for (iLayer iLayer : layers) {
			iLayer.readWeightAndBiasString(bufferedReader);
		}
	}

	@Override
	public int test(double[][] dataTestSet, double[] labelTestSet) throws Exception {
		int counter = 0;

		for (int i = 0; i < dataTestSet.length; i++) {

//			// find max
			double[] predict = this.predict(dataTestSet[i]);
			int maxIndex = 0;
			double maxValue = predict[0];

			for (int j = 1; j < 10; j++) {
				if (maxValue < predict[j]) {
					maxIndex = j;
					maxValue = predict[j];
				}
			}

			// compare
			if (maxIndex == (int) labelTestSet[i]) {
				counter++;
			}
		}
		return counter;
	}

}
