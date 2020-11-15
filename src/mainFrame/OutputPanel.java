package mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OutputPanel extends JPanel {

	// fields
	final int size = 28;
	String outputString;

	// constructor
	public OutputPanel() {
		// set default
		this.setPreferredSize(new Dimension(size * 10, size * 10));
		this.setBackground(Color.black);
		this.setBorder(
				BorderFactory.createTitledBorder(null, "Output", 2, 2, new Font("Arial", Font.ITALIC, 14), Color.BLUE));
	}

	// methods
	public void drawNumber(int number) {
		this.outputString = String.valueOf(number);
		this.paintComponents(getGraphics());
		this.outputString = "";
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, this.getHeight()));
		g.drawString(outputString, 9 * this.getInsets().left, this.getHeight() - 2 * this.getInsets().top);
	}

	// run test
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		OutputPanel outputPanel = new OutputPanel();
		frame.add(outputPanel);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void clear() {
		this.repaint();
	}
}
