import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private int[] values;
	
	private JButton[] houses = new JButton[14];
	private JButton undo = new JButton("Undo");
	private JFrame frame = new JFrame("Mancala");

	public Board(MancalaModel m) {
		model = m;

		JFrame f = new JFrame("Game Size");
		f.setLayout(new BorderLayout());
		
		JButton three = new JButton("3");
		three.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setGamePieces(3);
				f.dispose();
				makeGameBoard();
			}
		});
		JButton four = new JButton("4");
		four.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setGamePieces(4);
				f.dispose();
				makeGameBoard();
			}
		});
		f.add(new JLabel("Select game size."), BorderLayout.NORTH);
		f.add(three, BorderLayout.CENTER);
		f.add(four, BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
	private void makeGameBoard() {
		values = model.getBoard();
		for(int i = 0; i < 14; i++) {
			JButton b = new JButton(Integer.toString(values[i]), new Seed(values[i]));
			b.setPreferredSize(new Dimension(75, 75));
			final int position = i;
			b.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					model.move(position);
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
	
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.undo();
				undo.setEnabled(false);
			}
		});
		undo.setEnabled(false);
		
		JPanel mancalaPanelA = new JPanel();
		JTextField aText = new JTextField("  A  ");
		aText.setFont(new Font("SansSerif", Font.BOLD, 20));
		aText.setEditable(false);
		aText.setBorder(BorderFactory.createEmptyBorder());
		mancalaPanelA.add(houses[MancalaModel.KALAH_1]);
		mancalaPanelA.add(aText);
		mancalaPanelA.add(undo);
		
		JPanel mancalaPanelB = new JPanel();
		JTextField bText = new JTextField("  B  ");
		bText.setFont(new Font("SansSerif", Font.BOLD, 20));
		bText.setEditable(false);
		bText.setBorder(BorderFactory.createEmptyBorder());
		mancalaPanelB.add(bText);
		mancalaPanelB.add(houses[MancalaModel.KALAH_2]);
		
		frame.add(mancalaPanelB);
		frame.add(housePanel);
		frame.add(mancalaPanelA);
		frame.setLayout(new FlowLayout());
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
			results.setText(model.getResults());
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
		if(model.isUndoable()) {
			undo.setEnabled(true);
		}
		setTurn();
		frame.pack();
		frame.repaint();
	}
	
	/**
	 * Mancala game piece.
	 * 
	 * @author Andy, Jaylan, Matt
	 *
	 */
	private class Seed implements Icon {
		
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
}
