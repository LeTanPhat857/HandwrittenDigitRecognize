package mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DataPanel extends JPanel {

	// fields
	JTextField dataSetPath;
	JTextField labelSetPath;
	JTextField dataTestSetPath;
	JTextField labelTestSetPath;

	// constructor
	public DataPanel() {
		// set up this
		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(BorderFactory.createTitledBorder(null, "Data for training and testing", 1, 2,
				new Font("Arial", Font.ITALIC, 14), Color.BLUE));

		// create component
		dataSetPath = new JTextField("data/train-images.idx3-ubyte");
		labelSetPath = new JTextField("data/train-labels.idx1-ubyte");
		dataTestSetPath = new JTextField("data/t10k-images.idx3-ubyte");
		labelTestSetPath = new JTextField("data/t10k-labels.idx1-ubyte");

		// labelPanel contains labels
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(4, 1, 10, 10));
		labelPanel.add(new JLabel("DataSet path: "));
		labelPanel.add(new JLabel("LabelSet path: "));
		labelPanel.add(new JLabel("DataTestSet path: "));
		labelPanel.add(new JLabel("LabelTestSet path: "));

		// textFieldPanel contains textFields
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setLayout(new GridLayout(4, 1, 10, 10));
		textFieldPanel.add(dataSetPath);
		textFieldPanel.add(labelSetPath);
		textFieldPanel.add(dataTestSetPath);
		textFieldPanel.add(labelTestSetPath);

		// add in this
		this.add(labelPanel, BorderLayout.WEST);
		this.add(textFieldPanel, BorderLayout.CENTER);
	}

	// methods
	public JTextField getDataSetPathField() {
		return this.dataSetPath;
	}

	public JTextField getLabelSetPathField() {
		return this.labelSetPath;
	}

	public JTextField getDataTestSetPathField() {
		return this.dataTestSetPath;
	}

	public JTextField getLabelTestSetPathField() {
		return this.labelTestSetPath;
	}

	// run test
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		DataPanel dataPanel = new DataPanel();

		frame.add(dataPanel);

		frame.setVisible(true);
		frame.pack();
//		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}
