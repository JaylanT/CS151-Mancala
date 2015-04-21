import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Mancala board.
 * @author Jaylan, Andy, Matt
 *
 */
public class Board implements ChangeListener {

	private MancalaModel model;
	private BoardStyle style;
	private int[] boardValues;

	private JButton[] houses;
	private JButton undo;

	/**
	 * Constructor
	 * @param m the model containing game data
	 */
	public Board(MancalaModel m) {
		model = m;
		houses = new JButton[14];
		undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.undo();
				undo.setEnabled(false);
			}
		});
		undo.setEnabled(false);
	}

	/**
	 * Starts the game by allowing user to select game size.
	 */
	public void startGame() {
		final JFrame sizeSelectFrame = new JFrame("Game Size");
		sizeSelectFrame.setLayout(new BorderLayout());
		JButton three = new JButton("3");
		three.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setGameSize(3);
				sizeSelectFrame.dispose();
				makeGameBoard();
			}
		});
		JButton four = new JButton("4");
		four.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setGameSize(4);
				sizeSelectFrame.dispose();
				makeGameBoard();
			}
		});
		sizeSelectFrame.add(new JLabel("Select game size."), BorderLayout.NORTH);
		sizeSelectFrame.add(three, BorderLayout.CENTER);
		sizeSelectFrame.add(four, BorderLayout.SOUTH);
		sizeSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sizeSelectFrame.pack();
		sizeSelectFrame.setVisible(true);
	}
	
	/**
	 * Makes the game board using selected style.
	 */
	private void makeGameBoard() {
		boardValues = model.getBoard();
		
		for (int i = 0; i < 14; i++) {
			JButton b = new JButton();
			b.setToolTipText(Integer.toString(boardValues[i]));
			final int position = i;
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					model.move(position);
				}
			});
			houses[i] = b;
		}
		houses[MancalaModel.KALAH_1].setEnabled(false);
		houses[MancalaModel.KALAH_2].setEnabled(false);
		
		final JFrame styleSelectFrame = new JFrame();
		styleSelectFrame.setLayout(new BorderLayout());
		
		JButton blackWhite = new JButton("Black and White");
		blackWhite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				styleSelectFrame.dispose();
				style = new BlackAndWhiteBoard();
				style.makeBoard(houses, undo, model.getGameSize());
				setTurn();
			}
		});
		JButton brown = new JButton("Brown");
		brown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				styleSelectFrame.dispose();
				style = new BrownBoard();
				style.makeBoard(houses, undo, model.getGameSize());
				setTurn();
			}
		});
		styleSelectFrame.add(blackWhite, BorderLayout.NORTH);
		styleSelectFrame.add(brown, BorderLayout.CENTER);
		styleSelectFrame.pack();
		styleSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		styleSelectFrame.setVisible(true);
	}

	/**
	 * Enables buttons for current player's turn and disables for the other.
	 */
	private void setTurn() {
		if (model.getTurn() == 0) {
			for (int i = 0; i < MancalaModel.KALAH_1; i++) {
				if (boardValues[i] == 0) {
					houses[i].setEnabled(false);
					style.setBackgroundDark(houses[i]);
				} else {
					houses[i].setEnabled(true);
					style.setBackgroundLight(houses[i]);
				}
			}
			for (int i = 7; i < MancalaModel.KALAH_2; i++) {
				houses[i].setEnabled(false);
				style.setBackgroundDark(houses[i]);
			}
		} else {
			for (int i = 7; i < MancalaModel.KALAH_2; i++) {
				if (boardValues[i] == 0) {
					houses[i].setEnabled(false);
					style.setBackgroundDark(houses[i]);
				} else {
					houses[i].setEnabled(true);
					style.setBackgroundLight(houses[i]);
				}
			}
			for (int i = 0; i < MancalaModel.KALAH_1; i++) {
				houses[i].setEnabled(false);
				style.setBackgroundDark(houses[i]);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (model.isGameOver()) {
			JFrame resultsFrame = new JFrame();
			JTextPane results = new JTextPane();
			results.setText(model.getResults());
			results.setEditable(false);
			resultsFrame.add(results);
			resultsFrame.pack();
			resultsFrame.setVisible(true);
		}
		boardValues = model.getBoard();
		for (int i = 0; i < 14; i++) {
			houses[i].setToolTipText(Integer.toString(boardValues[i]));
			style.setIcons(i, boardValues[i]);
		}
		if (model.isUndoable()) {
			undo.setEnabled(true);
		}
		setTurn();
		style.pack();
		style.repaint();
	}
	
}
