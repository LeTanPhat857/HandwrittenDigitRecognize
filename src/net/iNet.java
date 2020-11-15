package net;

public interface iNet {

	// get information about net
	public int getNumberLayer();

	public iLayer getLayerAt(int index);

	// create a neural net
	public void setActiveMethod(iActivationFunction activationFunction);

	public void addLayer(int numberNeural);

	public void createNet();

	// working methods
	public double[] predict(double[] data) throws Exception;

	public void train(double[][] dataSet, double[][] labelSet, int epoch, int miniBatch, double alpha) throws Exception;

	public void saveCurrentNet(String path) throws Exception;

	public void loadTrainedNet(String trainedNetPath) throws Exception;

	public int test(double[][] dataTestSet, double[] labelTestSet) throws Exception;
}
