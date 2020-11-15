package mainFrame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccuracyPanel extends JPanel {

	// fields
	final int numberClass = 10;
	List<JTextField> textFields;

	// constructor
	public AccuracyPanel() {
		// set up this
		this.setLayout(new GridLayout(10, 2, 5, 10));
		this.textFields = new ArrayList<JTextField>();

		// add component
		for (int i = 0; i < numberClass; i++) {
			// label
			JLabel label = new JLabel("num " + i + ":");

			// add text field
			JTextField textField = new JTextField();
			textField.setColumns(2);
			textField.setFont(new Font("Arial", Font.BOLD, 14));
			textField.setEditable(false);

			//
			this.add(label);
			this.add(textField);
			// add in textFields
			textFields.add(textField);
		}

		// set default
	}

	// methods
	public void resetAccuracyList() {
		for (int i = 0; i < textFields.size(); i++) {
			textFields.get(i).setText("");
		}
	}

	public void setAccuracyList(String[] accuracyStringArray) {
		for (int i = 0; i < accuracyStringArray.length; i++) {
			textFields.get(i).setText(accuracyStringArray[i] + "%");
		}
	}

	// run test
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		AccuracyPanel accuracyPanel = new AccuracyPanel();

		frame.add(accuracyPanel);

		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
}
