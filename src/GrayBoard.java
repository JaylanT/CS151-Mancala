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

	private JButton[] houses;
	private JFrame frame;
	
	@Override
	public void makeBoard(JButton[] houses, final JButton undo, int gameSize) {
		this.houses = houses;
		setBackgroundLight(undo);
		
		// set houses size and icons
		for (int i = 0; i < 14; i++) {
			houses[i].setPreferredSize(new Dimension(80, 80));
			if (i != MancalaModel.KALAH_1 && i != MancalaModel.KALAH_2) {
				setIcons(i, gameSize);
			} else {
				setBackgroundLight(houses[i]);
			}
		}

		JPanel row1 = new JPanel();
		row1.setLayout(new GridLayout(1, 6));
		JPanel row2 = new JPanel();
		row2.setLayout(new GridLayout(1, 6));
		for (int i = 12; i > MancalaModel.KALAH_1; i--) {
			row1.add(houses[i]);
		}
		for (int i = 0; i < MancalaModel.KALAH_1; i++) {
			row2.add(houses[i]);
		}

		JPanel housePanel = new JPanel();
		housePanel.setLayout(new GridLayout(4, 0));
		housePanel.setBackground(Color.gray);

		JLabel labelA = new JLabel("        A1                A2                A3                A4                A5               A6");
		labelA.setFont(new Font("SansSerif", Font.BOLD, 14));
		labelA.setBorder(BorderFactory.createEmptyBorder());
		JLabel labelB = new JLabel("       B1                B2                B3               B4                B5               B6");
		labelB.setFont(new Font("SansSerif", Font.BOLD, 14));
		labelB.setBorder(BorderFactory.createEmptyBorder());

		housePanel.add(labelB);
		housePanel.add(row1);
		housePanel.add(row2);
		housePanel.add(labelA);

		JPanel mancalaPanelA = new JPanel();
		mancalaPanelA.setBackground(Color.gray);
		JLabel aText = new JLabel("  A  ");
		aText.setFont(new Font("SansSerif", Font.BOLD, 20));
		aText.setBorder(BorderFactory.createEmptyBorder());
		mancalaPanelA.add(houses[MancalaModel.KALAH_1]);
		mancalaPanelA.add(aText);
		mancalaPanelA.add(undo);

		JPanel mancalaPanelB = new JPanel();
		mancalaPanelB.setBackground(Color.gray);
		JLabel bText = new JLabel("  B  ");
		bText.setFont(new Font("SansSerif", Font.BOLD, 20));
		bText.setBorder(BorderFactory.createEmptyBorder());
		mancalaPanelB.add(bText);
		mancalaPanelB.add(houses[MancalaModel.KALAH_2]);
		
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

	@Override
	public void setBackgroundDark(JButton house) {
		house.setBackground(Color.gray);
	}
	
	@Override
	public void setBackgroundLight(JButton house) {
		house.setBackground(Color.lightGray);
	}

	@Override
	public void setIcons(int i, int value) {
		houses[i].setIcon(new RedSquareSeed(value));
		houses[i].setDisabledIcon(new RedSquareSeed(value));
	}

	@Override
	public void pack() {
		frame.pack();
	}

	@Override
	public void repaint() {
		frame.repaint();
	}
	
}
