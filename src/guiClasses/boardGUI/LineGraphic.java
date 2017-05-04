package guiClasses.boardGUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JPanel;

public class LineGraphic extends JPanel {

	private int startY;
	private int startX;
	private int endY;
	private int endX;
	private Color lineColor;
	private int scale;
	private int buffer;

	public LineGraphic(Point startPoint, Point endPoint, Color lineColor, int scale, int buffer) {
		this.startY = (int) (startPoint.getY());
		this.startX = (int) (startPoint.getX());
		this.endY = (int) (endPoint.getY());
		this.endX = (int) (endPoint.getX());
		this.lineColor = lineColor;
		this.scale = scale;
		this.buffer = buffer;
	}// end constructor

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(lineColor);
		// creates a solid stroke with line width is 6, the ends go not go past
		// the bounding points, and lines will join smoothly
		Stroke stroke = new BasicStroke(6f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
		g2d.setStroke(stroke);

		// Adjusts the start and end y values to account for the uneven height
		// of the tiles
		int adjustHeightStart = scale / 4 * startY;
		if (startY % 2 == 1) {
			adjustHeightStart += scale / 4;
		}
		int adjustHeightEnd = scale / 4 * endY;
		if (endY % 2 == 1) {
			adjustHeightEnd += scale / 4;
		}
		// draws the line with the same adjustments needed on the CatanWindow so
		// it will layer properly
		g2d.drawLine(startX * scale + buffer, startY * scale + buffer - adjustHeightStart, endX * scale + buffer,
				endY * scale + buffer - adjustHeightEnd);

	}
}// end LineGraphic class
