package net.test;

import net.Neural;
import net.iNeural;

public class NeuralTest {

	// test constructor
	public static void test1() {
		iNeural inputNeural = new Neural();
		iNeural hiddenNeural = new Neural();
		iNeural outputNeural = new Neural();

		inputNeural.setOutput(10);
		
		outputNeural.setError(5);
		
		hiddenNeural.computeOutput();
		outputNeural.computeOutput();

		System.out.println("this is input neural: " + inputNeural);
		System.out.println("this is hidden neural: " + hiddenNeural.getOutput());
		System.out.println("this is output neural: " + outputNeural.getOutput());
	}

	// run test
	public static void main(String[] args) {
		test1();
	}
}
