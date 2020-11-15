package mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	// fields
	JButton homeButton;
	JButton settingButton;
	JButton exitButton;

	// constructor
	public MenuPanel() {
		// set up option panel
		this.setLayout(new FlowLayout(2, 30, 5));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2, false), "Menu", 3,
				2, new Font("Arial", Font.ITALIC, 14), Color.BLUE));

		// create button
		this.homeButton = new JButton("home");
		this.settingButton = new JButton("setting");
		this.exitButton = new JButton("exit");

		// add button
		this.add(homeButton);
		this.add(settingButton);
		this.add(exitButton);
	}

	// methods
	public void setHomeButton(JButton homeButton) {
		this.homeButton = homeButton;
	}

	public void setSettingButton(JButton settingButton) {
		this.settingButton = settingButton;
	}

	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}

	// run test
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		MenuPanel optionPanel = new MenuPanel();

		frame.add(optionPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
		frame.pack();
	}

	// methods
	public JButton getHomeButton() {
		return this.homeButton;
	}

	public JButton getSettingButton() {
		return this.settingButton;
	}

	public JButton getExitButton() {
		return this.exitButton;
	}
	
}
