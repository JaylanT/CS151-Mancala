import javax.swing.JButton;


public interface BoardStyle {
	
	/**
	 * Constructs the game board.
	 * @param houses the array of JButtons representing houses of mancala board
	 * @param undo the undo button
	 * @param gameSize the number of starting game pieces
	 */
	public void makeBoard(JButton[] houses, JButton undo, int gameSize);
	
	/**
	 * Sets the disabled house background color.
	 * @param house the house to set color
	 */
	public void setBackgroundDark(JButton house);
	
	/**
	 * Sets the enabled house background color.
	 * @param house the house to set color
	 */
	public void setBackgroundLight(JButton house);
	
	/**
	 * Sets the icons on the house
	 * @param i = position
	 * @param value = number of icons
	 */
	public void setIcons(int i, int value);
	
	/**
	 * Packs the frame.
	 */
	public void pack();
	
	/**
	 * Repaints the frame.
	 */
	public void repaint();
	
}