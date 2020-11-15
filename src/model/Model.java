package model;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import dataReader.DataReader;
import dataReader.iDataReader;
import net.Net;
import net.iNet;

public class Model implements iModel {

	// fields
	double[][] dataSet;
	double[][] labelSet;
	double[][] dataTestSet;
	double[] labelTestSet;

	iNet net;
	iDataReader dataReader;

	// constructor
	public Model() {
		net = new Net();
		dataReader = new DataReader();
	}

	// methods
	@Override
	public void init() {
		this.net.addLayer(784);
		this.net.addLayer(32);
		this.net.addLayer(10);
		this.net.createNet();
	}

	@Override
	public String getInfo() {
		String info = "Lê Tấn Phát - 18130173\nMai Lê Chí Thành - 18130218\nNhận diện từng chữ số viết tay\n---------------------------------\nArtificial neural networks\n";
		int numberLayer = net.getNumberLayer();
		info += "Number layer: " + numberLayer + "\n";

		for (int i = 0; i < numberLayer; i++) {
			info += "Layer " + (i + 1) + " has " + net.getLayerAt(i).getNumberNeural() + " neurals\n";
		}

		return info;
	}

	@Override
	public boolean isReady() {
		return (this.dataSet != null) && (this.labelSet != null);
	}

	@Override
	public double[] predict(BufferedImage bufferedImage) throws Exception {
		double[] imageArray = this.convertImageToArray(bufferedImage);
		double[] predict = net.predict(imageArray);

		// test
		for (int i = 0; i < 28; i++) {
			for (int j = 0; j < 28; j++) {
				if (j == 0 || j == 28 - 1) {
					System.out.print("|");
				}

				if (imageArray[i * 28 + j] == 0) {
					System.out.print("  ");
				} else {
					System.out.print((int) imageArray[i * 28 + j] + " ");
				}
			}
			System.out.println();
		}

		System.out.println("\n");

		int maxIndex = this.findIndexOfMax(predict);

		String accuracy = "";
		for (int i = 0; i < 10; i++) {
			accuracy += "number " + i + " = " + (int) (predict[i] * 100) + "%,  ";
		}

		System.out.println(accuracy);
		System.out.println("this is number: " + maxIndex);

		//
		return predict;
	}

	@Override
	public String loadTrainingData(String dataSetPath, String labelSetPath) throws Exception {
		this.dataSet = this.dataReader.readDataSet(dataSetPath);
		this.labelSet = this.dataReader.convertLabelSet(labelSetPath, 10);

		if (this.dataSet != null && this.labelSet != null) {
			return "Load training data...completed! DataTrain: " + dataSet.length + " examples";
		} else {
			return "Can not load data! Please check dataSetPath and labelSetPath!";
		}
	}

	@Override
	public String train() throws Exception {
		this.net.train(dataSet, labelSet, 1, 10, 1);
		return "Training...completed! ";
	}

	@Override
	public String loadTestingData(String dataTestSetPath, String labelTestSetPath) throws Exception {
		this.dataTestSet = this.dataReader.readDataSet(dataTestSetPath);
		this.labelTestSet = this.dataReader.readLabelSet(labelTestSetPath);

		if (this.dataTestSet != null && this.labelTestSet != null) {
			return "Load testing data...completed! DataTest: " + dataTestSet.length + " examples";
		} else {
			return "Can not load data! Please check dataTestSetPath and labelTestSetPath!";
		}
	}

	@Override
	public String test() throws Exception {
		int correctAnswer = this.net.test(dataTestSet, labelTestSet);
		return "Correct answer in 10000 test: " + correctAnswer;
	}

	@Override
	public void save(String savingPath) {
		try {
			this.net.saveCurrentNet(savingPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String loadingPath) {
		try {
			this.net.loadTrainedNet(loadingPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] convertDoubleArrayToStringArray(double[] doubleArray) {
		String[] stringArray = new String[doubleArray.length];

		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = String.valueOf((int) (doubleArray[i] * 100));

		}

		return stringArray;
	}

	@Override
	public int findIndexOfMax(double[] array) {
		int maxIndex = 0;
		double max = array[maxIndex];

		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
				maxIndex = i;
			}
		}

		return maxIndex;
	}

	public double[] convertImageToArray(BufferedImage bufferedImage) {
		// scale image
		BufferedImage originImage = new BufferedImage(28, 28, BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D g2d = originImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(bufferedImage, 0, 0, 28, 28, 0, 0, 28 * 10, 28 * 10, null);
		g2d.dispose();

		// convert
		double[] result = new double[28 * 28];

		for (int i = 0; i < result.length; i++) {
			if (originImage.getData().getDataBuffer().getElem(i) != 0) {
				result[i] = 1;
			} else {
				result[i] = 0;
			}
		}

		return result;
	}
}
