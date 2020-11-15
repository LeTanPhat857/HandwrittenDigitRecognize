package mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfoPanel extends JPanel {

	// fields
	JTextArea infoTextArea;
//	BufferedImage image;

	// constructor
	public InfoPanel() {
		// set up
		this.setLayout(new GridLayout(1, 2, 10, 10));

		// add
		this.infoTextArea = new JTextArea();
		this.add(infoTextArea);

		// set up infoTextArea
		this.infoTextArea.setEditable(false);
		this.infoTextArea.setBorder(
				BorderFactory.createTitledBorder(null, "Info", 1, 2, new Font("Arial", Font.ITALIC, 14), Color.BLUE));
		this.infoTextArea.setFont(new Font("Arial", Font.BOLD, 16));
		this.infoTextArea.setAlignmentX(LEFT_ALIGNMENT);
		this.infoTextArea.setRows(9);
		this.infoTextArea.setColumns(18);

		// label
		JLabel label = new JLabel();
		this.add(label);

		// set image
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("data/IMG1v1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image scaleImage = image.getScaledInstance(320, 180, image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(scaleImage));
	}

	// methods
	public JTextArea getInfoTextArea() {
		return this.infoTextArea;
	}

	// run test
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		InfoPanel infoPanel = new InfoPanel();

		frame.add(infoPanel);

		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

	}
}
