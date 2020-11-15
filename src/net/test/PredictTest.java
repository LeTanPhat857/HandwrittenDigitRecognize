package net.test;

import java.util.Arrays;

import net.Net;
import net.iNet;

public class PredictTest {

	// test1
	public static void test1() throws Exception {
		iNet net = new Net();
		net.addLayer(3);
		net.createNet();

		double[] data = { 1, 0, 1 };

		String result = Arrays.toString(net.predict(data));

		System.out.println("predit with 1 layer: " + result);
	}

	// test2
	public static void test2() throws Exception {
		iNet net = new Net();
		net.addLayer(3);
		net.addLayer(3);
		net.createNet();

		double[] data = { 1, 0, 1 };

		String layer1 = net.getLayerAt(0).writeWeightAndBiasString();
		String layer2 = net.getLayerAt(1).writeWeightAndBiasString();

		System.out.println(layer1);
		System.out.println(layer2);

		String result = Arrays.toString(net.predict(data));

		System.out.println("predit with 2 layer: " + result);
	}

	// run test
	public static void main(String[] args) throws Exception {
		test2();
	}
}
