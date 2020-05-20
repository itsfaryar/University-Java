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
import java.util.Scanner;

public class Core extends JPanel implements ActionListener{
	
	private Square[][] board_squares = new Square[8][8];
	private PlayersIcon picons;
	private BoardAreas ba;
	private Square chosen_squere=null;
	private Player[]players;
	private int pl_turn_index=-1;
	private int players_count=-1;
	private enum direction{R,L,U,D,RU,LU,RD,LD};
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
	public void startTurns(int pl_count) {
		chosen_squere=null;
		players_count=pl_count;
		pl_turn_index=0;
		setSquersUnclickeble();
		players[pl_turn_index].enableTawsSquers();
	}
	public void changeTurn() {
		pl_turn_index++;
		if(pl_turn_index>(players_count-1)) {
			pl_turn_index=0;
		}
		setSquersUnclickeble();
		players[pl_turn_index].enableTawsSquers();
		
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
	public void setPlayers(Player pls[]) {
		this.players=pls;
		if(pls[0]!=null)setStarterTaws(pls[0]);
		if(pls[1]!=null)setStarterTaws(pls[1]);
		if(pls[2]!=null)setStarterTaws(pls[2]);
		if(pls[3]!=null)setStarterTaws(pls[3]);
		
	}
	public void setSquersUnclickeble() {
		for (int i = 0; i < board_squares.length; i++) {
			for (int j = 0; j < board_squares[i].length; j++) {
            	board_squares[i][j].setClickble(false);
            	
            }
		}
	}
	public void checkPosToChainJump(int i,int j,direction dir) {
		if(dir==direction.U) {
			i--;
			if(i>=0) {
				if(board_squares[i][j].getPlayer()==null) {
					board_squares[i][j].setClickble(true);
					chooseSquere(new Position(i, j), true);
					
				}
			}
		}
		else if(dir==direction.D) {
			i++;
			if(i<=7) {
				if(board_squares[i][j].getPlayer()==null) {
					board_squares[i][j].setClickble(true);
					chooseSquere(new Position(i, j), true);
				
				}
			}
		}
		else if(dir==direction.R) {
			j++;
			if(j<=7) {
				if(board_squares[i][j].getPlayer()==null) {
					board_squares[i][j].setClickble(true);
					chooseSquere(new Position(i, j), true);
					
				}
			}
		}
		else if(dir==direction.L) {
			j--;
			if(j>=0) {
				if(board_squares[i][j].getPlayer()==null) {
					board_squares[i][j].setClickble(true);
					chooseSquere(new Position(i, j), true);
					
				}
			}
		}
		else if(dir==direction.RU) {
			i--;j++;
			if(i>=0&&j<=7) {
				if(board_squares[i][j].getPlayer()==null) {
					board_squares[i][j].setClickble(true);
					chooseSquere(new Position(i, j), true);
					
				}
			}
		}
		else if(dir==direction.LU) {
			i--;j--;
			if(i>=0&&j>=0) {
				if(board_squares[i][j].getPlayer()==null) {
					board_squares[i][j].setClickble(true);
					chooseSquere(new Position(i, j), true);
				}
			}
		}
		else if(dir==direction.LD) {
			i++;j--;
			if(i<=7&&j>=0) {
				if(board_squares[i][j].getPlayer()==null) {
					board_squares[i][j].setClickble(true);
					chooseSquere(new Position(i, j), true);
					
				}
			}
		}
		else if(dir==direction.RD) {
			i++;j++;
			if(i<=7&&j<=7) {
				if(board_squares[i][j].getPlayer()==null) {
					board_squares[i][j].setClickble(true);
					chooseSquere(new Position(i, j), true);
					
				}
			}
		}
	}
	public void checkChosenPos(Position past_pos,int i,int j,direction dir,boolean chain_jump) {
		if(chain_jump) {
			if(board_squares[i][j].getPlayer()!=null) {
				
				//checkPosToChainJump(i,j,dir);
				
			}
		
		}
		else {
			if(board_squares[i][j].getPlayer()==null) {
				board_squares[i][j].setClickble(true);
			}
			else {
				checkPosToChainJump(i,j,dir);
			}
		}
	}
	public void chooseSquere(Position p,boolean chain_jump) {
		int i,j;
		i=p.i-1;j=p.j;
		if(i>=0) {
			checkChosenPos(p, i, j,direction.U, chain_jump);
		}
		i=p.i+1;j=p.j;
		if(i<=7) {
			checkChosenPos(p, i, j,direction.D, chain_jump);
		}
		i=p.i;j=p.j+1;
		if(j<=7) {
			checkChosenPos(p, i, j,direction.R, chain_jump);
		}
		i=p.i;j=p.j-1;
		if(j>=0) {
			checkChosenPos(p, i, j,direction.L, chain_jump);
		}
		
		i=p.i-1;j=p.j+1;
		if(i>=0 && j<=7) {
			checkChosenPos(p, i, j,direction.RU, chain_jump);
		}
		i=p.i-1;j=p.j-1;
		if(i>=0 && j>=0) {
			checkChosenPos(p, i, j,direction.LU, chain_jump);
		}
		i=p.i+1;j=p.j-1;
		if(i<=7&&j>=0) {
			checkChosenPos(p, i, j,direction.LD, chain_jump);
		}
		i=p.i+1;j=p.j+1;
		if(i<=7&&j<=7) {
			checkChosenPos(p, i, j,direction.RD, chain_jump);
		}
	}
	public void moveTawTo(Square dist) {
		Player pl=chosen_squere.getPlayer();
		chosen_squere.unSetPlayer();
		dist.setPlayer(pl);
		changeTurn();
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
				chooseSquere(chosen_squere.getPosition(),false);
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
				Player pl=chosen_squere.getPlayer();
				chosen_squere.unChoose();
				moveTawTo(sqr);
				chosen_squere=null;
			}
		}
	}
}
