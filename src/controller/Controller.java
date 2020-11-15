package controller;

import java.awt.image.BufferedImage;
import java.io.File;

import model.iModel;
import view.View;

public class Controller implements iController {

	// fields
	iModel model;
	View view;

	// constructor
	public Controller(iModel model) {
		this.model = model;
		this.view = new View(this);

		//
		model.init();
		view.getInfoArea().setText(model.getInfo());
		view.getMainFrame().setVisible(true);
	}

	// methods
	@Override
	public void startTraining() throws Exception {
		view.getTrainButton().setText("training...");
		view.getTrainButton().setEnabled(false);

		//
		String statusString = "Start training\n";
		view.getStatusTextArea().setText(statusString);

//		//
		String dataSetPath = view.getDataSetPathField().getText();
		String labelSetPath = view.getLabelSetPathField().getText();

		//
		statusString += model.loadTrainingData(dataSetPath, labelSetPath) + "\n";
		view.getStatusTextArea().setText(statusString);

		if (model.isReady()) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					String statusString = view.getStatusTextArea().getText();

					for (int i = 0; i < 5; i++) {
						statusString += "epoch " + (i + 1) + "...";
						view.getStatusTextArea().setText(statusString);

						try {
							statusString += model.train() + "\n";
							view.getStatusTextArea().setText(statusString);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					statusString += "end training";
					view.getStatusTextArea().setText(statusString);

					view.getTrainButton().setText("train");
					view.getTrainButton().setEnabled(true);
				}
			});

			thread.setDaemon(true);
			thread.start();
		} else {
			view.getTrainButton().setText("train");
			view.getTrainButton().setEnabled(true);
		}
	}

	@Override
	public void startPredicting() {
		BufferedImage bufferedImage = view.getInputPanel().getBufferedImage();
		double[] doublePredictArray = null;

		try {
			doublePredictArray = model.predict(bufferedImage);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		int maxIndex = model.findIndexOfMax(doublePredictArray);
		view.getOutputPanel().drawNumber(maxIndex);

		String[] accuracyStringArray = model.convertDoubleArrayToStringArray(doublePredictArray);
		view.getAccuracyPanel().setAccuracyList(accuracyStringArray);
	}

	@Override
	public void startSaving(String savingPath) {
		view.getSaveButton().setText("saving...");
		view.getSaveButton().setEnabled(false);

		String statusString = "saved";
		view.getStatusTextArea().setText(statusString);

		if (view.getSavingPath().getText() != null) {
			model.save(view.getSavingPath().getText());
		}

		view.getSaveButton().setText("save");
		view.getSaveButton().setEnabled(true);
	}

	@Override
	public void startLoading(String loadingPath) {
		view.getLoadButton().setText("saving...");
		view.getLoadButton().setEnabled(false);

		String statusString = "loaded";
		view.getStatusTextArea().setText(statusString);

		File savedNet = new File(loadingPath);

		if (savedNet.exists()) {
			model.save(view.getLoadingPath().getText());
		} else {
			view.getStatusTextArea().setText("file not founed");
		}

		view.getLoadButton().setText("load");
		view.getLoadButton().setEnabled(true);
	}

	@Override
	public void startTesting() {
		view.getTestButton().setText("testing...");
		view.getTestButton().setEnabled(false);

		String statusString = "Start testing\n";
		view.getStatusTextArea().setText(statusString);

		String dataTestSetPath = view.getDataTestSetPathField().getText();
		String labelTestSetPath = view.getLabelTestSetPathField().getText();

		try {
			statusString += model.loadTestingData(dataTestSetPath, labelTestSetPath) + "\n";
			view.getStatusTextArea().setText(statusString);

			statusString += model.test() + "\n";
			view.getStatusTextArea().setText(statusString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		statusString += "end testing";
		view.getStatusTextArea().setText(statusString);

		view.getTestButton().setText("test");
		view.getTestButton().setEnabled(true);
	}
}
