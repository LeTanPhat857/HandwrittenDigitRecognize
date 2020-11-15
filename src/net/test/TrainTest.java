package net.test;

import dataReader.DataReader;
import net.Net;
import net.iNet;

public class TrainTest {

	// test1
	public static void test1() throws Exception {
		iNet net = new Net();

		net.addLayer(28 * 28);
		net.addLayer(64);
		net.addLayer(10);

		net.createNet();

		System.out.println("reading data...");
		DataReader dataReader = new DataReader();
		double[][] dataSet = dataReader.readDataSet("data/train-images.idx3-ubyte");
		double[][] labelSet = dataReader.convertLabelSet("data/train-labels.idx1-ubyte", 10);

		System.out.println("training...");
		net.train(dataSet, labelSet, 3, 10, 1);

		System.out.println("saving...");
		net.saveCurrentNet("trained/trainTest");

		System.out.println("testing...");
		DataReader dataReader1 = new DataReader();
		double[][] dataTestSet = dataReader1.readDataSet("data/t10k-images.idx3-ubyte");
		double[] labelTestSet = dataReader1.readLabelSet("data/t10k-labels.idx1-ubyte");

		int acr = net.test(dataTestSet, labelTestSet);

		System.out.println("Correct answer in 10000 test: " + acr);
	}

	// run test
	public static void main(String[] args) throws Exception {
		test1();
	}
}
