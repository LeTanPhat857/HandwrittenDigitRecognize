package net.test;

import net.Net;
import net.iNet;

public class NetTest {

	// test 1
	public static void test1() {
		iNet net = new Net();

		System.out.println("this is net: " + net);

		net.addLayer(3);
		net.addLayer(3);
		net.addLayer(3);

		net.createNet();

		int numberLayer = net.getNumberLayer();
		System.out.println("this is net: " + net);
		System.out.println("number layer of net: " + numberLayer);
		
		for (int i = 0; i < numberLayer; i++) {
			System.out.println("numer neural of layer " + (i + 1) + ": " + net.getLayerAt(i).getNumberNeural());
		}
	}

	// run test
	public static void main(String[] args) {
		test1();
	}
}
