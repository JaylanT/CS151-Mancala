import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;

/**
	 * Mancala game piece.
	 * @author Jaylan, Andy, Matt
	 *
	 */
	public class RedCircleSeed implements Icon {

		/**
		 * amount = number of seeds
		 */
		private int amount;

		/**
		 * Constructor
		 * @param amount = number of seeds
		 */
		public RedCircleSeed(int amount) {
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
		 * Paint Red Circle Seeds
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
				g2.setColor(Color.decode("0x9B111E"));
				Ellipse2D.Double ellipse = new Ellipse2D.Double(15 * j, row, 15, 15);
				g2.fill(ellipse);
			}
		}
	}