package mainFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OptionPanel extends JPanel {

	// fields
//	JButton predict;
	JButton clearButton;

	// constructor
	public OptionPanel() {
		// set up this
		this.setLayout(new FlowLayout(0, 40, 0));

		// create component
//		predict = new JButton("predict");
		clearButton = new JButton("clear");

		// add component
//		this.add(predict);
		this.add(clearButton);
	}

	// methods
	public JButton getClearButton() {
		return this.clearButton;
	}

	// run test
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		OptionPanel optionPanel = new OptionPanel();

		frame.add(optionPanel);

		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

}
