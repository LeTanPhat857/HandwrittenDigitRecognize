package dataReader;

public interface iDataReader {

	public double[][] readDataSet(String path) throws Exception;

	public double[] readLabelSet(String path) throws Exception;

	public double[][] convertLabelSet(String path, int numberCol) throws Exception;
}
