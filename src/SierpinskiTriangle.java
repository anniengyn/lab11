import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SierpinskiTriangle extends JPanel {

	// resolution of computer screen
	int displayHeight = 2880;
	int displayWidth = 1800;

	// Color Array for go over when filling the triangles.
		private static Color[] colours = { 
				new Color(0xfff100), // yellow 
				new Color(0xff8C00), // orange
				new Color(0xe81123), // red
				new Color(0xec008c), // pink
				new Color(0x68217a), // purple
				new Color(0x00188f), // navy
				new Color(0x00bcf2), // blue
				new Color(0x00b294), // turquoise
				new Color(0x029658), // green
				new Color(0xbad80a)};// chartreuse};

	private static int maxDepth = 9;

	SierpinskiTriangle(Dimension d) {
		setSize(d);
	}

	/*
	 * Set up a triangle
	 */
	public SierpinskiTriangle() {
		JFrame frame = new JFrame();
		// Set frame size to maximum display screen
		frame.setSize(displayWidth, displayHeight);
		// Adding triangle
		frame.add(new SierpinskiTriangle(frame.getSize()));
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SierpinskiTriangle t = new SierpinskiTriangle();

	}

	/**
	 * Draws a simple triangle 
	 * //Sources: https://stackoverflow.com/questions/25835468/draw-a-triangle-from-scratch-given-three-points
	 *            https://www.delftstack.com/de/howto/java/java-draw-triangle/
	 */

	public void drawTriangle(Point a, Point b, Point c, Graphics triangle) {

		triangle.drawLine(a.x, a.y, c.x, c.y); // left line
		triangle.drawLine(b.x, b.y, a.x, a.y); // right line
		triangle.drawLine(c.x, c.y, b.x, b.y); // bottom line

	}

	public void paint(Graphics triangle) {

		// draws triangle on maximum display screen
		Dimension d = getSize();
		int width = d.width;
		int height = d.height;

		int center = width / 2;
		int side = (int) ((2 * height) / Math.sqrt(3.0));
		int sidewidth = side / 2;

		Point a = new Point(center - sidewidth, height); // left
		Point b = new Point(center + sidewidth, height); // right
		Point c = new Point(center, 0); // top

		drawTriangle(c, a, b, triangle);
		drawRecursively(triangle, maxDepth, a, b, c);

	}
	
	/*
	 * Draws recursive triangles by using midpoint calculation 
	 * Sets / fills colour from given colour array
	 */

	public void drawRecursively(Graphics triangle, int maxDepth, Point a, Point b, Point c) {

		Polygon p = new Polygon();
		p.addPoint(a.x, a.y);
		p.addPoint(b.x, b.y);
		p.addPoint(c.x, c.y);

		Point ab = midpoint(a, b);
		Point bc = midpoint(b, c);
		Point ca = midpoint(c, a);
		triangle.setColor(colours[maxDepth]);
		triangle.fillPolygon(p);

		if (maxDepth > 0) {
			drawRecursively(triangle, maxDepth - 1, a, ab, ca);
			drawRecursively(triangle, maxDepth - 1, ab, b, bc);
			drawRecursively(triangle, maxDepth - 1, c, ca, bc);
		}
	}
	
	/*
	 * Calculates midpoints of Point a and Point b
	 */

	public Point midpoint(Point a, Point b) {
		return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
	}

}
