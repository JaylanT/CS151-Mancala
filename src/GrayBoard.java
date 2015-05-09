import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GrayBoard implements BoardStyle {

	/**
	 * houses = clickable areas on macala board
	 * frame = board framework
	 */
	private JButton[] houses;
	private JFrame frame;
	
	/**
	 * Create a Macala Board
	 */
	@Override
	public void makeBoard(JButton[] houses, final JButton undo, int gameSize) {
		this.houses = houses;
		setBackgroundLight(undo);
		
		/**
		 * Set House Size and Icons
		 */
		for (int i = 0; i < 14; i++) {
			houses[i].setPreferredSize(new Dimension(80, 80));
			if (i != MancalaModel.KALAH_A && i != MancalaModel.KALAH_B) {
				setIcons(i, gameSize);
			} else {
				setBackgroundLight(houses[i]);
			}
		}

		/**
		 * Set up the Houses of the Board
		 */
		JPanel row1 = new JPanel();
		row1.setLayout(new GridLayout(1, 6));
		JPanel row2 = new JPanel();
		row2.setLayout(new GridLayout(1, 6));
		for (int i = 12; i > MancalaModel.KALAH_A; i--) {
			row1.add(houses[i]);
		}
		for (int i = 0; i < MancalaModel.KALAH_A; i++) {
			row2.add(houses[i]);
		}

		/**
		 * Set up Panel for the Board
		 */
		JPanel housePanel = new JPanel();
		housePanel.setLayout(new GridLayout(4, 0));
		housePanel.setBackground(Color.gray);

		/**
		 * Label Board Parts (where Houses will be)
		 */
		JLabel labelA = new JLabel("        A1                A2                A3                A4                A5               A6");
		labelA.setFont(new Font("SansSerif", Font.BOLD, 14));
		labelA.setBorder(BorderFactory.createEmptyBorder());
		JLabel labelB = new JLabel("       B1                B2                B3               B4                B5               B6");
		labelB.setFont(new Font("SansSerif", Font.BOLD, 14));
		labelB.setBorder(BorderFactory.createEmptyBorder());

		/**
		 * Add Row of Houses and Labels to Board
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
		mancalaPanelA.add(undo);

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
		 * Add panels to Board Frame
		 */
		frame = new JFrame("Mancala");
		frame.getContentPane().setBackground(Color.gray);
		frame.add(mancalaPanelB);
		frame.add(housePanel);
		frame.add(mancalaPanelA);
		frame.setLayout(new FlowLayout());
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
	 * set which Icon to use on Houses
	 */
	@Override
	public void setIcons(int i, int value) {
		houses[i].setIcon(new RedSquareSeed(value));
		houses[i].setDisabledIcon(new RedSquareSeed(value));
	}

	/**
	 * Pack Frame
	 */
	@Override
	public void pack() {
		frame.pack();
	}

	/**
	 * Repaint Frame
	 */
	@Override
	public void repaint() {
		frame.repaint();
	}
	
}
