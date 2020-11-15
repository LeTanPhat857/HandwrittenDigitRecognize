package model;

import java.awt.image.BufferedImage;

public interface iModel {

	public void init();

	public String getInfo();

	public boolean isReady();

	public double[] predict(BufferedImage bufferedImage) throws Exception;

	public String loadTrainingData(String dataSetPath, String labelSetPath) throws Exception;

	public String train() throws Exception;

	public String loadTestingData(String dataTestSetPath, String labelTestSetPath) throws Exception;

	public String test() throws Exception;

	public void save(String savingPath);
	
	public void load(String loadingPath);

	public int findIndexOfMax(double array[]);

	public String[] convertDoubleArrayToStringArray(double[] doubleArray);
}
