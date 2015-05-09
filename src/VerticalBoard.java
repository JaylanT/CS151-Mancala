import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VerticalBoard  implements BoardStyle {

	/**
	 * houses = Clickable Areas on the Board
	 * frame = Board Framework
	 */
	private JButton[] houses;
	private JFrame frame;
	
	/**
	 * Create a Macala Board
	 */
	@Override
	public void makeBoard(JButton[] houses, final JButton undo, int gameSize) {
		this.houses = houses;
		undo.setBackground(Color.lightGray);
		
		/**
		 * Set House Sizes and Icons 
		 */
		for (int i = 0; i < 14; i++) {
			houses[i].setPreferredSize(new Dimension(80, 80));
			if (i == MancalaModel.KALAH_A || i == MancalaModel.KALAH_B) {
				setIcons(i, 0);
				houses[i].setBackground(Color.LIGHT_GRAY);
			} else {
				setIcons(i, gameSize);
			}
		}
		
		/**
		 * Set up Houses for the Board
		 */
		JPanel row1 = new JPanel();
		row1.setLayout(new GridLayout(6, 1));
		JPanel row2 = new JPanel();
		row2.setLayout(new GridLayout(6, 1));
		for (int i = 7; i < MancalaModel.KALAH_B; i++) {
			row1.add(houses[i]);
		}
		for (int i = 5; i >= 0; i--) {
			row2.add(houses[i]);
		}

		/** 
		 * Set up Panel for the Board
		 */
		JPanel housePanel = new JPanel();
		housePanel.setLayout(new GridLayout(1, 4));
		housePanel.setBackground(Color.gray);

		/**
		 * Label Board Parts (where Houses will be)
		 */
		JLabel labelA = new JLabel("A1");
		labelA.setFont(new Font("SansSerif", Font.BOLD, 14));
		labelA.setBorder(BorderFactory.createEmptyBorder());
		JLabel labelB = new JLabel("B1");
		labelB.setFont(new Font("SansSerif", Font.BOLD, 14));
		labelB.setBorder(BorderFactory.createEmptyBorder());

		/**
		 * Add Rows of Houses and Labels to Board
		 */
		housePanel.add(labelB);
		housePanel.add(row1);
		housePanel.add(row2);
		housePanel.add(labelA);

		/**
		 * Set up area for Player A
		 */
		JPanel mancalaPanelA = new JPanel();
		mancalaPanelA.setBackground(Color.gray);
		JLabel aText = new JLabel("  A  ");
		aText.setFont(new Font("SansSerif", Font.BOLD, 20));
		aText.setBorder(BorderFactory.createEmptyBorder());
		mancalaPanelA.add(houses[MancalaModel.KALAH_A]);
		mancalaPanelA.add(aText);
		
		/**
		 * Set up area for Player B
		 */
		JPanel mancalaPanelB = new JPanel();
		mancalaPanelB.setBackground(Color.gray);
		JLabel bText = new JLabel("  B  ");
		bText.setFont(new Font("SansSerif", Font.BOLD, 20));
		bText.setBorder(BorderFactory.createEmptyBorder());
		mancalaPanelB.add(bText);
		mancalaPanelB.add(houses[MancalaModel.KALAH_B]);
		
		/**
		 * Add Panels to Board Frame
		 */
		frame = new JFrame("Mancala");
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(Color.gray);
		frame.add(mancalaPanelA, BorderLayout.NORTH);
		frame.add(housePanel, BorderLayout.CENTER);
		frame.add(mancalaPanelB, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Set Disabled House Color
	 */
	@Override
	public void setBackgroundDark(JButton house) {
		house.setBackground(Color.gray);
	}
	
	/**
	 * Set Enabled House Color
	 */
	@Override
	public void setBackgroundLight(JButton house) {
		house.setBackground(Color.lightGray);
	}

	/**
	 * Set which Icon to use on House
	 */
	@Override
	public void setIcons(int i, int value) {
		houses[i].setIcon(new RedSquareSeed(value));
		houses[i].setDisabledIcon(new RedSquareSeed(value));
	}

	/**
	 * Packs the Frame
	 */
	@Override
	public void pack() {
		frame.pack();
	}

	/**
	 * Repaint Board
	 */
	@Override
	public void repaint() {
		frame.repaint();
	}
}
