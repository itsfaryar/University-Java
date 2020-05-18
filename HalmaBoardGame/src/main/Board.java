package main;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Board {
	private final JPanel board = new JPanel(new BorderLayout(3, 3));
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private JPanel chessBoard;
	
	/**
	 * Create the panel.
	 */
	public Board() {
		initBoard();
	}
	public void initBoard() {
		
      
	}
	public JPanel getBoard() {
		return board;
	}

}
