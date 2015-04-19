import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;

/**
 * Mancala game piece.
 * 
 * @author Andy, Jaylan, Matt
 *
 */
public class Seed implements Icon {
	
	private int amount;
	
	public Seed(int amount) {
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
		for(int i = 0, j = 0; i < amount; i++, j++) {
			if(i != 0 && i % 5 == 0) {
				row += 15;
				j = 0;
			}
			Ellipse2D.Double ellipse = new Ellipse2D.Double(15 * j, row, 15, 15);
			g2.fill(ellipse);
		}
	}
}
