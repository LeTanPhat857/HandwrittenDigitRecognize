package main;

import controller.Controller;
import controller.iController;
import model.Model;
import model.iModel;

public class HandwrritenDigitRecognitionApp {
	
	public static void main(String[] args) {
		iModel model = new Model();
		iController controller = new Controller(model);
	}
}
