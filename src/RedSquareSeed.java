import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;


public class RedSquareSeed implements Icon {

	/**
	 * amount = number of seeds
	 */
	private int amount;

	/**
	 * Constructor
	 * @param amount = number of seeds
	 */
	public RedSquareSeed(int amount) {
		this.amount = amount;
	}

	/**
	 * Get Icon (seed) Height
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
	 * Paint Red Square Seeds
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0, j = 0, k = 0, row = 0; i < amount; i++, j++, k++) {
			if (i != 0 && i % 5 == 0) {
				row += 16;
				j = 0;
				k = 0;
			}
			g2.setColor(Color.decode("0x9B111E"));
			Rectangle2D.Double ellipse = new Rectangle2D.Double(15 * j + k, row, 15, 15);
			g2.fill(ellipse);
		}
	}

}