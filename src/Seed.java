import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Mancala game piece.
 * 
 * @author Andy, Jaylan, Matt
 *
 */
public class Seed {
	
	Ellipse2D.Double ellipse;
	
	public Seed(int x, int y, int w, int h) {
		ellipse = new Ellipse2D.Double(x, y, w, h);
	}
	
	public void draw(Graphics2D g2) {
		g2.draw(ellipse);
	}
}
