import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Mancala board.
 * @author Andy, Jaylan, Matt
 *
 */
public class Board {
	
	private int[] board;
	private int turn;
	private ArrayList<ChangeListener> listeners;
	
	private static final int KALAH_1 = 6;
	private static final int KALAH_2 = 13;
	
	public Board() {
		board = new int[14];
		turn = 0;
		listeners = new ArrayList<ChangeListener>();
	}

	public void attach(ChangeListener l) {
		listeners.add(l);
	}
	
	public void update(int position) {
		move(position);
		for(ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}
	
	public void setGamePieces(int pieces) {
		for(int i = 0; i < 14; i++) {
			if(i != KALAH_1 && i != KALAH_2) {
				board[i] = pieces;
			}
		}
	}
	
	public int getTurn() {
		return turn % 2;
	}
	
	public void move(int position) {
		int pieces = board[position];
		board[position] = 0;
		for(int i = position + 1; pieces > 0; i++, pieces--) {
			//player 1's turn
			if(turn % 2 == 0) {
				if(i == KALAH_2) {
					i = 0;
				}
				//last piece placed on player 1's Kalah
				else if(i == KALAH_1 && pieces == 1) {
					turn++;
				}
				board[i]++;
			}
			//player 2's turn
			else {
				if(i == KALAH_1) {
					i++;
				} else if(i > KALAH_2) {
					i = 0;
				}
				//last piece placed on player 2's Kalah
				else if(i == KALAH_2 && pieces == 1) {
					turn++;
				}
				board[i]++;
			}
		}
		turn++;
	}
	
	public void print() {
		String row1 = "";
		String row2 = "";
		for(int i = 0; i < 14; i++) {
			if(i >= 7) {
				row2 = board[i] + row2;
			} else {
				row1 = row1 + board[i];
			}
		}
		System.out.println(row2);
		System.out.println(row1);
		System.out.println("\nPlayer " + (getTurn() + 1) + "'s turn.");
	}
}
