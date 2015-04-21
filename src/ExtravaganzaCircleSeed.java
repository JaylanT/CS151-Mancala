import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.Icon;


public class ExtravaganzaCircleSeed implements Icon {

	private int amount;
	private String[] colors = new String[] {"0x9B111E", "0x50C878", "0xffff00", "0x7a378b", "0xee7621", "0x0000ff", "0x20fa48", "0xee3a8c", "0xFF0000"};

	public ExtravaganzaCircleSeed(int amount) {
		this.amount = amount;
	}

	@Override
	public int getIconHeight() {
		return 0;
	}

	@Override
	public int getIconWidth() {
		return 0;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		int row = 0;
		for (int i = 0, j = 0; i < amount; i++, j++) {
			if (i != 0 && i % 5 == 0) {
				row += 15;
				j = 0;
			}
			Random randomGenerator = new Random();
			String color = colors[randomGenerator.nextInt(9)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double ellipse = new Ellipse2D.Double(15 * j, row, 15, 15);
			g2.fill(ellipse);
		}
	}
}
