import java.awt.Color;
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


public class BlackAndWhiteBoard implements BoardStyle {

	private JButton[] houses;
	private int[] boardValues;
	private MancalaModel model;
	
	@Override
	public void makeBoard(JFrame frame, JButton[] houses, int[] boardValues, final JButton undo, MancalaModel m) {
		this.houses = houses;
		this.boardValues = boardValues;
		this.model = m;
		for (int i = 0; i < 14; i++) {
			JButton b = new JButton();
			b.setPreferredSize(new Dimension(75, 75));
			b.setToolTipText(Integer.toString(boardValues[i]));
			final int position = i;
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					model.move(position);
				}
			});
			houses[i] = b;
			setIcons(i);
		}

		JPanel row1 = new JPanel();
		row1.setLayout(new GridLayout(1, 6));
		JPanel row2 = new JPanel();
		row2.setLayout(new GridLayout(1, 6));
		for (int i = 12; i > 6; i--) {
			row1.add(houses[i]);
		}
		for (int i = 0; i < 6; i++) {
			row2.add(houses[i]);
		}
		houses[MancalaModel.KALAH_1].setEnabled(false);
		houses[MancalaModel.KALAH_2].setEnabled(false);

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

	@Override
	public void setBackgroundDark(JButton house) {
		house.setBackground(Color.black);
	}
	
	@Override
	public void setBackgroundLight(JButton house) {
		house.setBackground(Color.white);
	}

	@Override
	public void setIcons(int i) {
		boardValues = model.getBoard();
		houses[i].setIcon(new ExtravaganzaCircleSeed(boardValues[i]));
		houses[i].setDisabledIcon(new ExtravaganzaCircleSeed(boardValues[i]));
	}
	
}
