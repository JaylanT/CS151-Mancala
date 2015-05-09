import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Model for mancala board data.
 * @author Jaylan Tse
 *
 */
public class MancalaModel {

	/**
	 * board = Present Macala Board
	 * prevBoard = Macala Board before a certain move
	 * turn = player chance to make a move
	 * prevTurn = previous turn
	 * undoCounter = count number of undos made
	 * prevPlayerToUndo = determine which player used an undo
	 * listeners = list of listeners used
	 * KALAH_1 = Board number for player 1 Kalah
	 * KALAH_2 = Board number for player 2 Kalah
	 */
	private int[] board;
	private int[] prevBoard;
	private int turn;
	private int prevTurn;
	private int undoCounter;
	private int prevPlayerToUndo;
	private ArrayList<ChangeListener> listeners;

	public static final int KALAH_A = 6;
	public static final int KALAH_B = 13;

	/**
	 * Macala Model Constructor
	 */
	public MancalaModel() {
		board = new int[14];
		turn = 0;
		undoCounter = 0;
		prevPlayerToUndo = 0;
		listeners = new ArrayList<ChangeListener>();
	}

	/**
	 * Adds ChangeListener to listeners array.
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
	 * @param size the amount of starting pieces in each house
	 */
	public void setGameSize(int size) {
		for (int i = 0; i < 14; i++) {
			if (i != KALAH_A && i != KALAH_B) {
				board[i] = size;
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
		if (getTurn() != prevPlayerToUndo) {
			undoCounter = 0;
		}
		int pieces = board[position];
		board[position] = 0;
		for (int i = position + 1; pieces > 0; i++, pieces--) {
			// player 1's turn
			if (getTurn() == 0) {
				if (i == KALAH_B) {
					i = 0;
				}
				if (pieces == 1) {
					if (i >= 0 && i < KALAH_A) {
						// last piece landed on empty house of player 1's board
						if (board[i] == 0 && board[12 - i] != 0) {
							board[KALAH_A]++;
							board[i] = -1;
							board[KALAH_A] += board[12 - i];
							board[12 - i] = 0;
						}
					}
					// last piece landed on player 1's Kalah
					else if (i == KALAH_A) {
						turn++;
						undoCounter = 0;
					}
				}
			}
			// player 2's turn
			else {
				if (i == KALAH_A) {
					i++;
				} else if (i > KALAH_B) {
					i = 0;
				}
				if (pieces == 1) {
					if (i > KALAH_A && i < KALAH_B) {
						// last piece landed on empty house of player 2's board
						if (board[i] == 0 && board[12 - i] != 0) {
							board[KALAH_B]++;
							board[i] = -1;
							board[KALAH_B] += board[12 - i];
							board[12 - i] = 0;
						}
					}
					// last piece landed on player 2's Kalah
					else if (i == KALAH_B) {
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
		prevPlayerToUndo = getTurn();
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
		for (int i = 0; i < KALAH_A; i++) {
			total1 += board[i];
		}
		// check player 2's side
		int total2 = 0;
		for (int i = 7; i < KALAH_B; i++) {
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
		for (int i = 0; i < KALAH_A; i++) {
			board[KALAH_A] += board[i];
			board[i] = 0;
		}
		// sum up all seeds on player 2's side
		for (int i = 7; i < KALAH_B; i++) {
			board[KALAH_B] += board[i];
			board[i] = 0;
		}
		// find winner or else tie
		String winner = "";
		if (board[KALAH_A] > board[KALAH_B]) {
			winner = "Player A Wins!\n";
		} else if (board[KALAH_B] > board[KALAH_A]) {
			winner = "Player B Wins!\n";
		} else {
			winner = "Tie\n";
		}
		return winner + "\nResults\n" + "Player A: " + board[KALAH_A] + "\nPlayer B: " + board[KALAH_B];
	}
}
