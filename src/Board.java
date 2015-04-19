import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
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
		frame.setTitle("Mancala");
		
		makeInterface();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private void makeInterface() {
		for(int i = 0; i < 14; i++) {
			JButton b = new JButton(Integer.toString(values[i]));
			b.setIcon(new Seed(values[i]));
			b.setPreferredSize(new Dimension(75, 75));
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
		housePanel.setLayout(new GridLayout(4, 0));
		JTextField labelA = new JTextField("        A1\t   A2              A3              A4               A5               A6");
		labelA.setEditable(false);
		labelA.setFont(new Font("SansSerif", Font.BOLD, 14));
		labelA.setBorder(BorderFactory.createEmptyBorder());
		JTextField labelB = new JTextField("       B1\t  B2              B3              B4              B5               B6");
		labelB.setEditable(false);
		labelB.setFont(new Font("SansSerif", Font.BOLD, 14));
		labelB.setBorder(BorderFactory.createEmptyBorder());
		
		housePanel.add(labelB);
		housePanel.add(row1);
		housePanel.add(row2);
		housePanel.add(labelA);
		
		JPanel mancalaA = new JPanel();
		mancalaA.setLayout(new FlowLayout());
		JTextField a = new JTextField("  A  ");
		a.setFont(new Font("SansSerif", Font.BOLD, 20));
		a.setEditable(false);
		a.setBorder(BorderFactory.createEmptyBorder());
		mancalaA.add(houses[MancalaModel.KALAH_1]);
		mancalaA.add(a);
		
		JPanel mancalaB = new JPanel();
		mancalaA.setLayout(new FlowLayout());
		JTextField b = new JTextField("  B  ");
		b.setFont(new Font("SansSerif", Font.BOLD, 20));
		b.setEditable(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		mancalaB.add(b);
		mancalaB.add(houses[MancalaModel.KALAH_2]);
		
		frame.add(mancalaB);
		frame.add(housePanel);
		frame.add(mancalaA);
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
				houses[i].setDisabledIcon(new Seed(values[i]));
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
		if(model.isGameOver()) {
			JFrame resultsFrame = new JFrame();
			JTextPane results = new JTextPane();
			results.setText(model.findWinner());
			results.setEditable(false);
			resultsFrame.add(results);
			resultsFrame.pack();
			resultsFrame.setVisible(true);
		}
		values = model.getBoard();
		for(int i = 0; i < 14; i++) {
			houses[i].setText(Integer.toString(values[i]));
			houses[i].setIcon(new Seed(values[i]));
		}
		setTurn();
		frame.pack();
		frame.repaint();
	}
}
