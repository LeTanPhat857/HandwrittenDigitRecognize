package mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InputPanel extends JPanel {

	// fields
	final int size = 28;
	Point point = new Point();
	BufferedImage bufferedImage = new BufferedImage(size * 10, size * 10, BufferedImage.TYPE_BYTE_GRAY);

	// constructor
	public InputPanel() {
		// set default
		this.setPreferredSize(new Dimension(size * 10, size * 10));
		this.setBackground(Color.black);
		this.setBorder(
				BorderFactory.createTitledBorder(null, "Input", 2, 2, new Font("Arial", Font.ITALIC, 14), Color.BLUE));

		//
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg) {
				draw(arg.getX(), arg.getY());
			}
		});
	}

	public void draw(int x, int y) {
		point = new Point(x, y);

		paintBufferedImage(bufferedImage.getGraphics());

		paintComponents(getGraphics());
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.white);

		int x = (int) point.getX();
		int y = (int) point.getY();

		g.fillOval(x, y, 10, 10);
	}

	public void paintBufferedImage(Graphics g) {
		g.setColor(Color.white);

		int x = (int) point.getX();
		int y = (int) point.getY();

		g.fillOval(x, y, 30, 30);
	}

	public void clear() {
		this.repaint();
	}

	public BufferedImage getBufferedImage() {
		return this.bufferedImage;
	}

	public void resetBufferedImage() {
		this.bufferedImage = new BufferedImage(size * 10, size * 10, BufferedImage.TYPE_BYTE_GRAY);
	}

	// run test
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());

		InputPanel inputPanel = new InputPanel();
		frame.add(inputPanel);

		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

	}
}
