import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Model for mancala board data.
 * @author Jaylan Tse
 *
 */
public class MancalaModel {

	private int[] board;
	private int[] prevBoard;
	private int turn;
	private int prevTurn;
	private int undoCounter;
	private int prevPlayerUndo;
	private ArrayList<ChangeListener> listeners;

	public static final int KALAH_1 = 6;
	public static final int KALAH_2 = 13;

	/**
	 * Constructor
	 */
	public MancalaModel() {
		board = new int[14];
		turn = 0;
		undoCounter = 0;
		prevPlayerUndo = 0;
		listeners = new ArrayList<ChangeListener>();
	}

	/**
	 * @param l the ChangeListener
	 */
	public void attach(ChangeListener l) {
		listeners.add(l);
	}

	/**
	 * Updates all views.
	 */
	public void update() {
		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}

	/**
	 * Sets the starting game size.
	 * @param pieces the amount of starting pieces in each house
	 */
	public void setGamePieces(int pieces) {
		for (int i = 0; i < 14; i++) {
			if (i != KALAH_1 && i != KALAH_2) {
				board[i] = pieces;
			}
		}
	}

	/**
	 * Moves the seeds in the selected house.
	 * @param position the index of the house
	 */
	public void move(int position) {
		prevBoard = board.clone();
		prevTurn = turn;
		if (getTurn() != prevPlayerUndo) {
			undoCounter = 0;
		}
		int pieces = board[position];
		board[position] = 0;
		for (int i = position + 1; pieces > 0; i++, pieces--) {
			// player 1's turn
			if (getTurn() == 0) {
				if (i == KALAH_2) {
					i = 0;
				}
				if (pieces == 1) {
					if (i >= 0 && i < KALAH_1) {
						// last piece landed on empty house of player 1's board
						if (board[i] == 0 && board[12 - i] != 0) {
							board[KALAH_1]++;
							board[i] = -1;
							board[KALAH_1] += board[12 - i];
							board[12 - i] = 0;
						}
					}
					// last piece landed on player 1's Kalah
					else if (i == KALAH_1) {
						turn++;
						undoCounter = 0;
					}
				}
			}
			// player 2's turn
			else {
				if (i == KALAH_1) {
					i++;
				} else if (i > KALAH_2) {
					i = 0;
				}
				if (pieces == 1) {
					if (i > KALAH_1 && i < KALAH_2) {
						// last piece landed on empty house of player 2's board
						if (board[i] == 0 && board[12 - i] != 0) {
							board[KALAH_2]++;
							board[i] = -1;
							board[KALAH_2] += board[12 - i];
							board[12 - i] = 0;
						}
					}
					// last piece landed on player 2's Kalah
					else if (i == KALAH_2) {
						turn++;
						undoCounter = 0;
					}
				}
			}
			board[i]++;
		}
		turn++;
		update();
	}

	/**
	 * Undoes previous move.
	 */
	public void undo() {
		board = prevBoard.clone();
		turn = prevTurn;
		prevPlayerUndo = getTurn();
		undoCounter++;
		update();
	}

	/**
	 * Checks if player can still undo. Max of 3 undos per turn.
	 * @return if player can undo
	 */
	public boolean isUndoable() {
		return undoCounter < 3;
	}

	/**
	 * Gets a copy of the board's values.
	 * @return a copy of the board's values
	 */
	public int[] getBoard() {
		return board.clone();
	}

	/**
	 * Gets the current player turn.
	 * @return the current turn (player A if 0, player B if 1)
	 */
	public int getTurn() {
		return turn % 2;
	}

	/**
	 * Checks if the game is over.
	 * @return if the game is over
	 */
	public boolean isGameOver() {
		boolean gameOver = false;
		// check player 1's side
		int total1 = 0;
		for (int i = 0; i < KALAH_1; i++) {
			total1 += board[i];
		}
		// check player 2's side
		int total2 = 0;
		for (int i = 7; i < KALAH_2; i++) {
			total2 += board[i];
		}
		// check if either side is empty
		if (total1 == 0 || total2 == 0) {
			gameOver = true;
		}
		return gameOver;
	}

	/**
	 * Gets the results of the game after it ends.
	 * @return the results of the game
	 */
	public String getResults() {
		// sum up all seeds on player 1's side
		for (int i = 0; i < KALAH_1; i++) {
			board[KALAH_1] += board[i];
			board[i] = 0;
		}
		// sum up all seeds on player 2's side
		for (int i = 7; i < KALAH_2; i++) {
			board[KALAH_2] += board[i];
			board[i] = 0;
		}
		// find winner or else tie
		String winner = "";
		if (board[KALAH_1] > board[KALAH_2]) {
			winner = "Player A Wins!\n";
		} else if (board[KALAH_2] > board[KALAH_1]) {
			winner = "Player B Wins!\n";
		} else {
			winner = "Tie\n";
		}
		return winner + "\nResults\n" + "Player A: " + board[KALAH_1] + "\nPlayer B: " + board[KALAH_2];
	}
}
