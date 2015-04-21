import javax.swing.JButton;
import javax.swing.JFrame;


public interface BoardStyle {
	
	public void makeBoard(JFrame frame, JButton[] houses, int[] boardValues, JButton undo, MancalaModel model);
	public void setBackgroundDark(JButton house);
	public void setBackgroundLight(JButton house);
	public void setIcons(int i);
}
