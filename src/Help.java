
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Help extends JPanel implements ActionListener{

	/**
	 * img = Background image
	 * text = Help text
	 * str = Help Menu Title
	 * introText = Text area showing help text
	 * night = lighting of introText
	 */
	private Image img;
	private String text;
	private static JLabel str;
	private static JTextArea introText;
	private static JCheckBox night;

	/**
	 * Help Menu to describe How To Play Macala
	 */
	public Help(){
	    img = new ImageIcon("HelpBackground.jpg").getImage();
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	    text = "Instructions:\n"
				+ "-------------------------------------------------------------\n"
				+ " The object of this game is to collect as many gems in your \n"
				+ " mancala as possible. YOu and your opponent will take turns\n"
				+ " to move the gems according to the following rules.\n\n"
				+ "How to Play:\n"
				+ "-------------------------------------------------------------\n"
				+ " 1) A player can only move the gems on his/her side\n"
				+ " 2) Each time a player move, he/she pick up all the gems in a \n"
				+ " cup and distribute them in a counter clockwise direction to \n"
				+ " the next cup.\n"
				+ " 3) If the last gem of a move landed on your mancala, then you\n"
				+ " can move again.\n"
				+ " 4) If the last gem of a move landed on an empty cup on your side\n"
				+ " and there are some gems in the oppostite cup, then the gems in \n" 
				+ " the two cups will be capture in your Mancala.\n"
				+ "-------------------------------------------------------------\n";
	
	    this.setLayout(null);
		
		/**
		 * Label the Menu
		 */
		str = new JLabel("HOW TO PLAY THIS                   ???????", JLabel.LEFT);//The title
		str.setFont(new Font("Serif",Font.BOLD,30));
		str.setBounds(120, 20, 600, 50);
		str.setForeground(Color.black);
		this.add(str);
		
		/**
		 *  Textarea to show introduction, using Scroll 
		 */
		introText = new JTextArea();
		introText.setText(text);
		introText.setEditable(false);
		introText.setFont(new Font("Monospaced", Font.PLAIN, 20));
		introText.setBackground(Color.black);
		introText.setForeground(Color.white);
		JScrollPane scroller = new JScrollPane(introText);
		scroller.setBounds(50, 80, 700, 400);
		this.add(scroller);
		
		/**
		 * Checkbox to determine Dark or Light Lighting for text area	
		 */
		night = new JCheckBox("Night");
        night.setSelected(true);
        night.setBounds(700, 40, 70, 20);
        night.setBackground(Color.orange);
        night.addActionListener( this);
        this.add(night);

	}

	/**
	 * Paint Background
	 */
	public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0,800,600, null);
	}
	
	/**
	 * When night checkbox is checked, use black background with white text
	 * else use white background with black text
	 */
	public void actionPerformed(ActionEvent e){  
		Object source = e.getSource();
		if(source == night){
			boolean state = night.isSelected();
			if(state == true){
				introText.setBackground(Color.black);
				introText.setForeground(Color.white);
			}
			else{
				introText.setBackground(Color.white);
				introText.setForeground(Color.black);
			}
		}
	}
}
