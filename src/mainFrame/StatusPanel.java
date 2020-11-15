package mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Scrollbar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class StatusPanel extends JPanel {

	// fields
	JTextArea statusArea;

	// constructor
	public StatusPanel() {
		// set layout
		this.setLayout(new BorderLayout());

		// create component
		statusArea = new JTextArea();

		// set up statusArea
		this.statusArea.setEditable(false);
		this.statusArea.setBorder(
				BorderFactory.createTitledBorder(null, "Status", 1, 2, new Font("Arial", Font.ITALIC, 14), Color.BLUE));
		this.statusArea.setFont(new Font("Arial", Font.BOLD, 16));
		this.statusArea.setAlignmentX(LEFT_ALIGNMENT);
		this.statusArea.setRows(15);
		this.statusArea.setColumns(15);

		// add
		this.add(statusArea, BorderLayout.CENTER);
	}

	// methods
	public JTextArea getStatusTextArea() {
		return this.statusArea;
	}

	// run test
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		StatusPanel statusPanel = new StatusPanel();

		frame.add(statusPanel);

		statusPanel.statusArea.setText(
				"alkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkklklklkkmlkml\nalkjfffffffffffffffffffffffffffffffffffffffff");
		System.out.println(statusPanel.statusArea.getText());
		statusPanel.statusArea.setText("alkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkklklklkkmlkml\n");
		frame.setVisible(true);
		frame.pack();
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}
