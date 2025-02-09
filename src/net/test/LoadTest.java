package net.test;

import net.Net;
import net.iNet;

public class LoadTest {

	// test1
	public static void test1() throws Exception {
		iNet net = new Net();
		
		net.loadTrainedNet("trained/saveTest");
		
		String layer1 = net.getLayerAt(0).writeWeightAndBiasString();
		String layer2 = net.getLayerAt(1).writeWeightAndBiasString();
		String layer3 = net.getLayerAt(2).writeWeightAndBiasString();
		
		System.out.println(layer1);
		System.out.println(layer2);
		System.out.println(layer3);
	}
	
	// run test
	public static void main(String[] args) throws Exception {
		test1();
	}
}
