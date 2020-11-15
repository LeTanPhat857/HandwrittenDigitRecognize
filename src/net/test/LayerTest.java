package net.test;

import net.Layer;
import net.iLayer;

public class LayerTest {

	// test1
	public static void test1() {
		iLayer inputLayer = new Layer();
		iLayer hiddenLayer = new Layer();
		iLayer outputLayer = new Layer();

		inputLayer.setNumberNeural(10);
		
		outputLayer.connectTo(hiddenLayer);
		
		System.out.println("this is input layer: " + inputLayer.getNumberNeural());
		System.out.println("this is hidden layer: " + hiddenLayer.getNumberNeural());
		System.out.println("this is output layer: " + outputLayer.getNumberNeural());
	}

	// run test
	public static void main(String[] args) {
		test1();
	}
}
