import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.Icon;

public class RainbowCircleSeed implements Icon {

	/**
	 * amount = number of seeds
	 * colors = possible colors of seeds
	 */
	private int amount;
	private String[] colors = new String[] { "0xED1C24", "0xF26522", "0xF7941D", "0xFFF200", "0x8DC73F", "0x39B54A", "0x00A651", "0x00A99D", "0x00AEEF", "0x0072BC", "0x0054A6", "0x2E3192", "0x662D91", "0x92278F", "0xEC008C", "0xED145B" };

	/**
	 * Constructor
	 * @param amount = number of seeds
	 */
	public RainbowCircleSeed(int amount) {
		this.amount = amount;
	}

	/**
	 * Get Icon (seed) height
	 */
	@Override
	public int getIconHeight() {
		return 0;
	}

	/**
	 * Get Icon (seed) Width
	 */
	@Override
	public int getIconWidth() {
		return 0;
	}

	/**
	 * Paint the seeds (with random colors)
	 */
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
			String color = colors[randomGenerator.nextInt(colors.length)];
			g2.setColor(Color.decode(color));
			Ellipse2D.Double ellipse = new Ellipse2D.Double(15 * j, row, 15, 15);
			g2.fill(ellipse);
		}
	}
}