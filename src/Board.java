import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Mancala board.
 * 
 * @author Andy, Jaylan, Matt
 *
 */
public class Board implements ChangeListener {

	private MancalaModel model;
	private JButton[] houses;
	private int[] values;
	private JFrame frame;

	public Board(MancalaModel m) {
		model = m;
		houses = new JButton[14];
		values = model.getBoard();
		
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		
		for(int i = 0; i < 14; i++) {
			JButton b = new JButton(Integer.toString(values[i]));
			final int position = i;
			b.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					model.update(position);
				}
			});
			houses[i] = b;
		}
		
		JPanel row1 = new JPanel();
		row1.setLayout(new GridLayout(1, 6));
		JPanel row2 = new JPanel();
		row2.setLayout(new GridLayout(1, 6));
		for(int i = 12; i > 6; i--) {
			row1.add(houses[i]);
		}
		for(int i = 0; i < 6; i++) {
			row2.add(houses[i]);
		}
		houses[MancalaModel.KALAH_1].setEnabled(false);
		houses[MancalaModel.KALAH_2].setEnabled(false);
		setTurn();
		
		JPanel housePanel = new JPanel();
		housePanel.setLayout(new GridLayout(2, 0));
		housePanel.add(row1);
		housePanel.add(row2);
		
		frame.add(houses[MancalaModel.KALAH_2]);
		frame.add(housePanel);
		frame.add(houses[MancalaModel.KALAH_1]);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private void setTurn() {
		if(model.getTurn() == 0) {
			for(int i = 0; i < MancalaModel.KALAH_1; i++) {
				if(values[i] == 0) {
					houses[i].setEnabled(false);
				} else {
					houses[i].setEnabled(true);
				}
			}
			for(int i = 7; i < MancalaModel.KALAH_2; i++) {
				houses[i].setEnabled(false);
			}
		} else {
			for(int i = 7; i < MancalaModel.KALAH_2; i++) {
				if(values[i] == 0) {
					houses[i].setEnabled(false);
				} else {
					houses[i].setEnabled(true);
				}
			}
			for(int i = 0; i < MancalaModel.KALAH_1; i++) {
				houses[i].setEnabled(false);
			}
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		values = model.getBoard();
		for(int i = 0; i < 14; i++) {
			houses[i].setText(Integer.toString(values[i]));
		}
		setTurn();
		frame.pack();
		frame.repaint();
	}
}
