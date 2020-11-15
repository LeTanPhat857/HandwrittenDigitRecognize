package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.iController;
import mainFrame.AccuracyPanel;
import mainFrame.InputPanel;
import mainFrame.MainFrame;
import mainFrame.OutputPanel;

public class View {

	// fields
	MainFrame mainFrame;
	iController controller;

	// constructor
	public View(iController controller) {
		this.mainFrame = new MainFrame();
		this.controller = controller;

		// set listenner
		setActionForTrainButton();
		setActionForInputPanel();
		setActionForSaveButton();
		setActionForLoadButton();
		setActionForTestButton();
	}


	// methods
	public void setActionForTrainButton() {
		getTrainButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.startTraining();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setActionForInputPanel() {
		getInputPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				controller.startPredicting();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				getOutputPanel().clear();
			}
		});
	}

	public void setActionForSaveButton() {
		getSaveButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.startSaving(getSavingPath().getText());
			}
		});
	}

	public void setActionForLoadButton() {
		getLoadButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.startLoading(getLoadingPath().getText());
			}
		});
	}


	public void setActionForTestButton() {
		getTestButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.startTesting();
			}
		});
	}
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public JTextArea getInfoArea() {
		return this.mainFrame.getInfoTextArea();
	}

	public InputPanel getInputPanel() {
		return this.mainFrame.getInputPanel();
	}

	public OutputPanel getOutputPanel() {
		return this.mainFrame.getOutputPanel();
	}

	public AccuracyPanel getAccuracyPanel() {
		return mainFrame.getAccuracyPanel();
	}

	public JButton getTrainButton() {
		return this.mainFrame.getTrainButton();
	}

	public JTextField getDataSetPathField() {
		return this.mainFrame.getDataSetPathField();
	}

	public JTextField getLabelSetPathField() {
		return this.mainFrame.getLabelSetPathField();
	}

	public JTextField getDataTestSetPathField() {
		return this.mainFrame.getDataTestSetPathField();
	}

	public JTextField getLabelTestSetPathField() {
		return this.mainFrame.getLabelTestSetPathField();
	}

	public JTextArea getStatusTextArea() {
		return this.mainFrame.getStatusTextArea();
	}

	public JButton getSaveButton() {
		return this.mainFrame.getSaveButton();
	}

	public JButton getLoadButton() {
		return this.mainFrame.getLoadButton();
	}

	public JButton getTestButton() {
		return this.mainFrame.getTestButton();
	}

	public JTextField getSavingPath() {
		return this.mainFrame.getSavingPath();
	}

	public JTextField getLoadingPath() {
		return this.mainFrame.getLoadingPath();
	}

	// run test
	public static void main(String[] args) {
		new View(null).mainFrame.setVisible(true);
	}
}
