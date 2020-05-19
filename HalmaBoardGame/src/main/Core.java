package main;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

public class Core extends JPanel {
	
	private Square[][] board_squares = new Square[8][8];
	public Core() {
		
		initBoard();
	}
	
	public void initBoard() {
		setLayout(new GridLayout(0,8));
		for (int i = 0; i < board_squares.length; i++) {
            for (int j = 0; j < board_squares[i].length; j++) {
                Square b = new Square();
                if ((i+j)%2==0){
                	b.setBackground(new java.awt.Color(232,235,239));
                }
                else {
                	b.setBackground(new java.awt.Color(125,135,150));
                }
                add(b);
                board_squares[i][j]=b;
            }
        }
		
      
	}
	

}
