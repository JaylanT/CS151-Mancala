import java.awt.BorderLayout;
import java.awt.Dimension;
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
	private int[] boardValues;
	private BoardStyle style;

	private JButton[] houses = new JButton[14];
	private JButton undo = new JButton("Undo");
	private JFrame frame = new JFrame("Mancala");

	/**
	 * Constructor
	 * @param m the model containing game data
	 */
	public Board(MancalaModel m) {
		model = m;
	}

	/**
	 * Starts the game by allowing user to select game size.
	 */
	public void startGame() {
		final JFrame f = new JFrame("Game Size");
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
	
	/**
	 * Makes the game board using selected style.
	 */
	private void makeGameBoard() {
		boardValues = model.getBoard();
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
		}
		
		JFrame styleFrame = new JFrame();
		styleFrame.setLayout(new BorderLayout());
		
		JButton blackWhite = new JButton("Black and White");
		blackWhite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				style = new BlackAndWhiteBoard();
				style.makeBoard(frame, houses, boardValues, undo, model);
				setTurn();
			}
		});
		JButton brown = new JButton("Brown");
		brown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				style = new BrownBoard();
				style.makeBoard(frame, houses, boardValues, undo, model);
				setTurn();
			}
		});
		styleFrame.add(blackWhite, BorderLayout.NORTH);
		styleFrame.add(brown, BorderLayout.CENTER);
		styleFrame.pack();
		styleFrame.setVisible(true);
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
			style.setIcons(i);
		}
		if (model.isUndoable()) {
			undo.setEnabled(true);
		}
		setTurn();
		frame.pack();
		frame.repaint();
	}
	
}
