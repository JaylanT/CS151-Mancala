
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Menu extends JPanel {

	/**
	 * img = Menu Background
	 * gameName = Title of Game Label
	 */
	private Image img;
	private JLabel gameName;

	/**
	 * Menu Specifics
	 */
	public Menu() {
		img = new ImageIcon("MenuBackground.jpg").getImage();
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
		gameName = new JLabel("Manacala", JLabel.LEFT);//The title
	    gameName.setFont(new Font("Serif",Font.BOLD,120));
	    gameName.setBounds(140, 10, 600, 300);
	    gameName.setForeground(Color.darkGray);
		this.add(gameName);
	}

	/**
	 * Paint Background
	 */
	public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0,800,600, null);
	}
	
}
