package mainFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	// fields
	HomePanel homePanel;
	SettingPanel settingPanel;
	MenuPanel menuPanel;

	// constructor
	public MainFrame() {
		// set up
		this.setLayout(new BorderLayout(5, 5));

		// create component
		homePanel = new HomePanel();
		settingPanel = new SettingPanel();
		menuPanel = new MenuPanel();

		// add component
		this.add(homePanel, BorderLayout.CENTER);
		this.add(menuPanel, BorderLayout.SOUTH);

		// set default
		this.pack();
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// set action
		this.setActionForMenuButton();
		this.setActionClearForInputPanel();
	}

	// methods
	public void setActionForMenuButton() {
		menuPanel.getHomeButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchHomePanel();
			}
		});

		menuPanel.getSettingButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchSettingPanel();
			}
		});

		menuPanel.getExitButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

	public void setActionClearForInputPanel() {
		homePanel.getClearButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				homePanel.getInputPanel().clear();
				homePanel.getInputPanel().resetBufferedImage();
				homePanel.getOutputPanel().clear();
				homePanel.getAccuracyPanel().resetAccuracyList();
			}
		});
	}

	// menuPanel
	// homePanel
	public JTextArea getInfoTextArea() {
		return this.homePanel.getInfoTextArea();
	}

	public InputPanel getInputPanel() {
		return this.homePanel.getInputPanel();
	}

	public OutputPanel getOutputPanel() {
		return this.homePanel.getOutputPanel();
	}

	public AccuracyPanel getAccuracyPanel() {
		return this.homePanel.getAccuracyPanel();
	}

	// settingPanel
	public JButton getTrainButton() {
		return this.settingPanel.getTrainButton();
	}

	public JTextField getDataSetPathField() {
		return this.settingPanel.getDataSetPathField();
	}

	public JTextField getLabelSetPathField() {
		return this.settingPanel.getLabelSetPathField();
	}

	public JTextField getDataTestSetPathField() {
		return this.settingPanel.getDataTestSetPathField();
	}

	public JTextField getLabelTestSetPathField() {
		return this.settingPanel.getLabelTestSetPathField();
	}

	public JTextArea getStatusTextArea() {
		return this.settingPanel.getStatusTextArea();
	}

	// switch method
	public void switchHomePanel() {
		this.add(homePanel, BorderLayout.CENTER);
		homePanel.setVisible(true);
		settingPanel.setVisible(false);

		homePanel.getInputPanel().resetBufferedImage();
		homePanel.getAccuracyPanel().resetAccuracyList();
	}

	public void switchSettingPanel() {
		this.add(settingPanel, BorderLayout.CENTER);
		settingPanel.setVisible(true);
		homePanel.setVisible(false);
	}

	public JButton getSaveButton() {
		return this.settingPanel.getSaveButton();
	}

	public JButton getLoadButton() {
		return this.settingPanel.getLoadButton();
	}

	public JButton getTestButton() {
		return this.settingPanel.getTestButton();
	}

	public JTextField getSavingPath() {
		return this.settingPanel.getSavingPath();
	}

	public JTextField getLoadingPath() {
		return this.settingPanel.getLoadingPath();
	}

	// run tests
	public static void main(String[] args) throws InterruptedException {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
}
