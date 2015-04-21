import javax.swing.JButton;


public interface BoardStyle {
	
	public void makeBoard(JButton[] houses, int[] boardValues, JButton undo, MancalaModel model);
	public void setBackgroundDark(JButton house);
	public void setBackgroundLight(JButton house);
	public void setIcons(int i);
	public void pack();
	public void repaint();
}
