package dataReader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataReader implements iDataReader {

	@Override
	public double[][] readDataSet(String path) throws Exception {
		File file = new File(path);

		if (file.exists() != true) {
			return null;
		}

		FileInputStream fileInputStream = new FileInputStream(file);
		DataInputStream dataInputStream = new DataInputStream(fileInputStream);

		// test magic number images
		int magicNumber = dataInputStream.readInt();
		if (magicNumber != 0x803) {
			dataInputStream.close();
			return null;
		}

		int numberOfExample = dataInputStream.readInt();

		double[][] result = new double[numberOfExample][];

		int numRows = dataInputStream.readInt();
		int numCols = dataInputStream.readInt();

		//
		for (int n = 0; n < numberOfExample; n++) {
			byte data[] = new byte[numRows * numCols];
			this.read(dataInputStream, data);
			result[n] = convertImageToBinaryImage(data);
		}

		dataInputStream.close();

		return result;
	}

	@Override
	public double[] readLabelSet(String path) throws Exception {
		File file = new File(path);

		if (file.exists() != true) {
			return null;
		}

		FileInputStream fileInputStream = new FileInputStream(file);
		DataInputStream dataInputStream = new DataInputStream(fileInputStream);

		// test magic number images
		int magicNumber = dataInputStream.readInt();
		if (magicNumber != 0x801) {
			dataInputStream.close();
			return null;
		}

		int numberOfExample = dataInputStream.readInt();

		double[] result = new double[numberOfExample];

		//
		for (int n = 0; n < numberOfExample; n++) {
			byte label = dataInputStream.readByte();
			result[n] = label;
		}

		dataInputStream.close();

		return result;
	}

	//
	@Override
	public double[][] convertLabelSet(String path, int numberCol) throws Exception {
		double[] labelSet = this.readLabelSet(path);

		if (labelSet == null) {
			return null;
		}

		int numberRow = labelSet.length;
		double[][] twoDimLabelSet = new double[numberRow][numberCol];

		for (int i = 0; i < numberRow; i++) {
			for (int j = 0; j < numberCol; j++) {
				if (j == labelSet[i]) {
					twoDimLabelSet[i][j] = 1;
				}
			}
		}

		return twoDimLabelSet;
	}

	//
	private double[] convertImageToBinaryImage(byte[] image) {
		int length = image.length;
		double[] result = new double[length];

		for (int i = 0; i < length; i++) {
			if (image[i] < 0) {
				result[i] = 1;
			} else {
				result[i] = 0;
			}
		}

		return result;
	}

	//
	private void read(InputStream inputStream, byte data[]) throws IOException {
		int offset = 0;

		while (true) {
			int read = inputStream.read(data, offset, data.length - offset);

			if (read < 0) {
				break;
			}

			offset += read;

			if (offset == data.length) {
				return;
			}
		}

		throw new IOException("Tried to read " + data.length + " bytes, but only found " + offset);
	}

	// run test
	public static void main(String[] args) throws Exception {
//		new DataReader().readDataSet("data/t10k-images.idx3-ubyte");
//		System.out.println(new DataReader().readLabelSet("data/t10k-labels.idx1-ubyt"));
		System.out.println(new DataReader().convertLabelSet("data/t10k-labels.idx1-ubyt", 10));
//		System.out.println(new File("data/t10k-images.idx3-ubyte").exists());
	}
}
