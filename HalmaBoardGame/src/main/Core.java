package main;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Core extends JPanel implements ActionListener{
	
	private Square[][] board_squares = new Square[8][8];
	private PlayersIcon picons;
	private BoardAreas ba;
	private Square chosen_squere=null;
	private enum direction{R,L,U,D};
	public Core(BoardAreas ba,PlayersIcon picons) {
		this.picons=picons;
		this.ba=ba;
		initBoard();
	}
	
	public void initBoard() {
		setLayout(new GridLayout(0,8));
		int c=1;
		for (int i = 0; i < board_squares.length; i++) {
            for (int j = 0; j < board_squares[i].length; j++) {
                Square b = new Square(new Position(i,j),picons);
                b.addActionListener(this);
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
		setSquersUnclickeble();
		
	}
	public void clearAllSquers() {
		
		for (int i = 0; i < board_squares.length; i++) {
			for (int j = 0; j < board_squares[i].length; j++) {
            	board_squares[i][j].clear();
            	
            }
		}
	}
	public void setStarterTaws(Player pl) {
		Position[]p=ba.getStarterPoints_one(pl);
		if(p!=null) {
			for(int i=0;i<p.length;i++) {
				board_squares[p[i].i][p[i].j].setPlayer(pl);
			}
		}
	}
	public void setPlayers(Player pl_1,Player pl_2,Player pl_3,Player pl_4) {
		if(pl_1!=null)setStarterTaws(pl_1);
		if(pl_2!=null)setStarterTaws(pl_2);
		if(pl_3!=null)setStarterTaws(pl_3);
		if(pl_4!=null)setStarterTaws(pl_4);
		
	}
	public void setSquersUnclickeble() {
		for (int i = 0; i < board_squares.length; i++) {
			for (int j = 0; j < board_squares[i].length; j++) {
            	board_squares[i][j].setClickble(false);
            	
            }
		}
	}
	public void checkChosenPos(Position past_pos,int i,int j,boolean is_jumped,direction dir) {
		if(!is_jumped) {
			if(board_squares[i][j].getPlayer()==null) {
				board_squares[i][j].setClickble(true);
				chooseSquere(new Position(i, j),past_pos,true);
			}
			else {
				if(dir==direction.U)i--;
				else if(dir==direction.D)i++;
				else if(dir==direction.R)j++;
				else if(dir==direction.L)j--;
				if((i>=0&&i<=7)&&(j>=0&&j<=7)) {
					if(board_squares[i][j].getPlayer()==null) {
						board_squares[i][j].setClickble(true);
						chooseSquere(new Position(i, j),past_pos,true);
					}
				}
			}
		}
		else {
			if(board_squares[i][j].getPlayer()!=null) {
				if(dir==direction.U)i--;
				else if(dir==direction.D)i++;
				else if(dir==direction.R)j++;
				else if(dir==direction.L)j--;
				if((i>=0&&i<=7)&&(j>=0&&j<=7)) {
					if(board_squares[i][j].getPlayer()==null) {
					board_squares[i][j].setClickble(true);
						chooseSquere(new Position(i, j),past_pos,true);
					}
				}
			}
		}
	}
	public void chooseSquere(Position p,Position past_p,boolean is_jumped) {
		int i,j;
		i=p.i-1;j=p.j;
		if(i>=0) {
			checkChosenPos(p, i, j, is_jumped,direction.U);
		}
		i=p.i+1;j=p.j;
		if(i<=7) {
			checkChosenPos(p, i, j, is_jumped,direction.D);
		}
		i=p.i-1;j=p.j;
		if(i>=0) {
			checkChosenPos(p, i, j, is_jumped,direction.U);
		}
		i=p.i-1;j=p.j;
		if(i>=0) {
			checkChosenPos(p, i, j, is_jumped,direction.U);
		}
	}
	public void moveTawTo(Square dist) {
		Player pl=chosen_squere.getPlayer();
		chosen_squere.unSetPlayer();
		dist.setPlayer(pl);
	}
	public void actionPerformed(ActionEvent e) { 
		Square sqr=(Square) e.getSource();
		if(chosen_squere==null ) {
			chosen_squere=sqr;
			if(!chosen_squere.is_clickble())chosen_squere=null;
			else{
				setSquersUnclickeble();
				chosen_squere.setClickble(true);
				chosen_squere.choose();
				chooseSquere(chosen_squere.getPosition(), new Position(-1,-1),false);
				System.out.println(chosen_squere.getPosition().i+","+chosen_squere.getPosition().j);
			}
		}
		else if(chosen_squere==sqr) {
			setSquersUnclickeble();
			chosen_squere.getPlayer().enableTawsSquers();
			chosen_squere.unChoose();
			chosen_squere=null;
		}
		else {
			if(sqr.is_clickble()) {
				moveTawTo(sqr);
				setSquersUnclickeble();
				//chosen_squere.getPlayer().enableTawsSquers();
				chosen_squere.unChoose();
				chosen_squere=null;
			}
		}
	}
}
