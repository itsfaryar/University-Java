package main;

import main.Square.player_access;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Core extends JPanel implements ActionListener{
	
	private Square[][] board_squares;
	private PlayersIcon picons;
	private BoardAreas ba;
	private Square chosen_squere=null;
	private Player[]players=null;
	private int pl_turn_index=-1;
	private int players_count=-1;
	private enum direction{R,L,U,D,RU,LU,RD,LD};
	boolean is_first_move=true;
	public Core(BoardAreas ba,PlayersIcon picons,int w,int n) {
		board_squares= new Square[w][w];
		setLayout(new GridLayout(0,w));
		this.picons=picons;
		this.ba=ba;
		initBoard();
	}
	public boolean is_playing() {
		if(players==null)return false;
		else return true;
	}
	public void initBoard() {
		
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
		is_first_move=true;
		chosen_squere=null;
		players_count=pl_count;
		pl_turn_index=0;
		setSquersUnclickeble();
		setAllPlayerSquersAccesses();
	}
	public void changeTurn() {
		pl_turn_index++;
		if(pl_turn_index>(players_count-1)) {
			pl_turn_index=0;
		}
		setSquersUnclickeble();
		setAllPlayerSquersAccesses();
		chosen_squere.unChoose();
		chosen_squere=null;
		is_first_move=true;
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
            	board_squares[i][j].setAccess(player_access.NONE);
            	
            }
		}
	}
	public void setAllPlayerSquersAccesses() {
		for(int i=0;i<players_count;i++) {
			if(pl_turn_index==i)  players[i].setAccessToAllTawsSquers(player_access.PL);
			else   players[i].setAccessToAllTawsSquers(player_access.NOT_AVAILBLE);
            	
		}
	}
	public void setAllPlayerSquersAccesses(player_access asc) {
		for(int i=0;i<players_count;i++) {
			players[i].setAccessToAllTawsSquers(asc); 	
		}
	}
	public int checkPosToChainJump(int i,int j,direction dir) {
		int availble_moves=0;
		if(dir==direction.U) {
			i--;
			if(i>=0) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.D) {
			i++;
			if(i<=7) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.R) {
			j++;
			if(j<=7) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.L) {
			j--;
			if(j>=0) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.RU) {
			i--;j++;
			if(i>=0&&j<=7) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.LU) {
			i--;j--;
			if(i>=0&&j>=0) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.LD) {
			i++;j--;
			if(i<=7&&j>=0) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.RD) {
			i++;j++;
			if(i<=7&&j<=7) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					availble_moves++;
				}
			}
		}
		return  availble_moves;
	}
	public void firstMove(Position p) {
		int i,j;
		i=p.i-1;j=p.j;
		if(i>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
			}
			else {
				checkPosToChainJump(i,j,direction.U);
			}
		}
		i=p.i+1;j=p.j;
		if(i<=7) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
			}
			else {
				checkPosToChainJump(i,j,direction.D);
			}
		}
		i=p.i;j=p.j+1;
		if(j<=7) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
			}
			else {
				checkPosToChainJump(i,j,direction.R);
			}
		}
		i=p.i;j=p.j-1;
		if(j>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
			}
			else {
				checkPosToChainJump(i,j,direction.L);
			}
		}
		
		i=p.i-1;j=p.j+1;
		if(i>=0 && j<=7) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
			}
			else {
				checkPosToChainJump(i,j,direction.RU);
			}
		}
		i=p.i-1;j=p.j-1;
		if(i>=0 && j>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
			}
			else {
				checkPosToChainJump(i,j,direction.LU);
			}
		}
		i=p.i+1;j=p.j-1;
		if(i<=7&&j>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
			}
			else {
				checkPosToChainJump(i,j,direction.LD);
			}
		}
		i=p.i+1;j=p.j+1;
		if(i<=7&&j<=7) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
			}
			else {
				checkPosToChainJump(i,j,direction.RD);
			}
		}
	}
	public int chainMove(Position p) {
		int  availble_moves=0;
		int i,j;
		i=p.i-1;j=p.j;
		if(i>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.U);
			}
		}
		i=p.i+1;j=p.j;
		if(i<=7) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.D);
			}
			
		}
		i=p.i;j=p.j+1;
		if(j<=7) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.R);
			}
		}
		i=p.i;j=p.j-1;
		if(j>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.L);
			}
			
		}
		
		i=p.i-1;j=p.j+1;
		if(i>=0 && j<=7) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.RU);
			}
		}
		i=p.i-1;j=p.j-1;
		if(i>=0 && j>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.LU);
			}
		}
		i=p.i+1;j=p.j-1;
		if(i<=7&&j>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.LD);
			}
		}
		i=p.i+1;j=p.j+1;
		if(i<=7&&j<=7) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.RD);
			}
		}
		return availble_moves;
	}
	public void moveTawTo(Square dist) {
		setSquersUnclickeble();
		Player pl=chosen_squere.getPlayer();
		chosen_squere.unSetPlayer();
		chosen_squere.setAccess(player_access.NOT_AVAILBLE);
		dist.setPlayer(pl);
		dist.choose();
		setAllPlayerSquersAccesses(player_access.NOT_AVAILBLE);
		dist.setAccess(player_access.PL);
		chosen_squere=dist;
		is_first_move=false;
		
	}
	public void actionPerformed(ActionEvent e) { 
		Square sqr=(Square) e.getSource();
		if(is_first_move) {
			if(chosen_squere==null ) {
				chosen_squere=sqr;
				if(chosen_squere.getAccessType()==player_access.PL) {
					setSquersUnclickeble();
					setAllPlayerSquersAccesses(player_access.NOT_AVAILBLE);
					chosen_squere.setAccess(player_access.PL);
					chosen_squere.choose();
					firstMove(chosen_squere.getPosition());
					
				}
				else {
					chosen_squere=null;
				}
			}
			else if(chosen_squere==sqr) {
				setSquersUnclickeble();
				setAllPlayerSquersAccesses();
				chosen_squere.unChoose();
				chosen_squere=null;
			}
			else {
				if(sqr.getAccessType()==player_access.DIRECT) {
					moveTawTo(sqr);
					changeTurn();
				}
				else if(sqr.getAccessType()==player_access.JUMP){
					moveTawTo(sqr);
					if(chainMove(chosen_squere.getPosition())==0) {
						changeTurn();
					}
				}
			}
		}
		else {
			if(sqr.getAccessType()==player_access.DIRECT) {
				moveTawTo(sqr);
				changeTurn();
			}
			else if(sqr.getAccessType()==player_access.JUMP){
				moveTawTo(sqr);
				if(chainMove(chosen_squere.getPosition())==0) {
					changeTurn();
				}
			}
		}
	}
}
