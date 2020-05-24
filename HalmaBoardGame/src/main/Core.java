package main;

import main.Player.player_type;
import main.Square.player_access;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Core extends JPanel implements ActionListener{
	
	private Square[][] board_squares;
	private PlayersIcon picons;
	private BoardAreas ba;
	private Square chosen_squere=null;
	private Player[]players=null;
	private int pl_turn_index=-1;
	private int players_count=-1;
	private JLabel pl_info;
	private enum direction{R,L,U,D,RU,LU,RD,LD};
	private boolean ended=false;
	boolean is_first_move=true;
	ArrayList<Square>options;
	int w,n;
	public Core(BoardAreas ba,PlayersIcon picons,int w,int n,JLabel pl_info) {
		this.w=w;
		this.n=n;
		options=new ArrayList<Square>();
		board_squares= new Square[w][w];
		setLayout(new GridLayout(0,w));
		this.picons=picons;
		this.ba=ba;
		this.pl_info=pl_info;
		initBoard();
	}
	public boolean is_playing() {
		if(players==null || chosen_squere==null || is_first_move || ended)return false;
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
	public ImageIcon resizeIcon(ImageIcon image ,int w) {
		return new ImageIcon(image.getImage().getScaledInstance(w,w, Image.SCALE_DEFAULT));
	}
	public void startTurns(int pl_count) {
		is_first_move=true;
		chosen_squere=null;
		players_count=pl_count;
		pl_turn_index=0;
		setSquersUnclickeble();
		setAllPlayerSquersAccesses();
		pl_info.setText("it's your turn");
		pl_info.setIcon(resizeIcon(this.getPlayerThisTurn().getIcon(),25));
		if(players[pl_turn_index].type==player_type.AI ) {
			autoMove(players[pl_turn_index]);
			changeTurn();
		}
	}
	public void changeTurn() {
		options=new ArrayList<Square>();
		if(!ended) {
			players[pl_turn_index].set_moved();
			pl_turn_index++;
			if(pl_turn_index>(players_count-1)) {
				pl_turn_index=0;
			}
			setSquersUnclickeble();
			setAllPlayerSquersAccesses();
			if(chosen_squere!=null) {
				chosen_squere.unChoose();
				chosen_squere=null;
			}
			is_first_move=true;
			pl_info.setText("it's your turn");
			pl_info.setIcon(resizeIcon(this.getPlayerThisTurn().getIcon(),25));
			if(players[pl_turn_index].type==player_type.AI ) {
				autoMove(players[pl_turn_index]);
				changeTurn();
			}
		}
	}
	public void clearAllSquers() {
		
		for (int i = 0; i < board_squares.length; i++) {
			for (int j = 0; j < board_squares[i].length; j++) {
            	board_squares[i][j].clear();
            	
            }
		}
	}
	
	public void setPlayers(Player pls[]) {
		this.players=pls;
		if(pls[0]!=null) {
			pls[0].setW_of_board(w);
			pls[0].refreshIcon(picons);
			pls[0].setStarterTaws(board_squares, ba);
		}
		if(pls[1]!=null) {
			pls[1].setW_of_board(w);
			pls[1].refreshIcon(picons);
			pls[1].setStarterTaws(board_squares, ba);
		}
		if(pls[2]!=null) {
			pls[2].setW_of_board(w);
			pls[2].refreshIcon(picons);
			pls[2].setStarterTaws(board_squares, ba);
		}
		if(pls[3]!=null) {
			pls[3].setW_of_board(w);
			pls[3].refreshIcon(picons);
			pls[3].setStarterTaws(board_squares, ba);
		}
		
	}
	public Player[] getPlayers() {
		return players;
	}
	public Player getPlayerThisTurn() {
		return players[pl_turn_index];
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
					options.add(board_squares[i][j]);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.D) {
			i++;
			if(i<w) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					options.add(board_squares[i][j]);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.R) {
			j++;
			if(j<w) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					options.add(board_squares[i][j]);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.L) {
			j--;
			if(j>=0) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					options.add(board_squares[i][j]);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.RU) {
			i--;j++;
			if(i>=0&&j<w) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					options.add(board_squares[i][j]);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.LU) {
			i--;j--;
			if(i>=0&&j>=0) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					options.add(board_squares[i][j]);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.LD) {
			i++;j--;
			if(i<w&&j>=0) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					options.add(board_squares[i][j]);
					availble_moves++;
				}
			}
		}
		else if(dir==direction.RD) {
			i++;j++;
			if(i<w&&j<w) {
				if(board_squares[i][j].getAccessType()==player_access.NONE) {
					board_squares[i][j].setAccess(player_access.JUMP);
					options.add(board_squares[i][j]);
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
				options.add(board_squares[i][j]);
			}
			else {
				checkPosToChainJump(i,j,direction.U);
			}
		}
		i=p.i+1;j=p.j;
		if(i<w) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
				options.add(board_squares[i][j]);
			}
			else {
				checkPosToChainJump(i,j,direction.D);
			}
		}
		i=p.i;j=p.j+1;
		if(j<w) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
				options.add(board_squares[i][j]);
			}
			else {
				checkPosToChainJump(i,j,direction.R);
			}
		}
		i=p.i;j=p.j-1;
		if(j>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
				options.add(board_squares[i][j]);
			}
			else {
				checkPosToChainJump(i,j,direction.L);
			}
		}
		
		i=p.i-1;j=p.j+1;
		if(i>=0 && j<w) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
				options.add(board_squares[i][j]);
			}
			else {
				checkPosToChainJump(i,j,direction.RU);
			}
		}
		i=p.i-1;j=p.j-1;
		if(i>=0 && j>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
				options.add(board_squares[i][j]);
			}
			else {
				checkPosToChainJump(i,j,direction.LU);
			}
		}
		i=p.i+1;j=p.j-1;
		if(i<w&&j>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
			}
			else {
				checkPosToChainJump(i,j,direction.LD);
			}
		}
		i=p.i+1;j=p.j+1;
		if(i<w&&j<w) {
			if(board_squares[i][j].getAccessType()==player_access.NONE) {
				board_squares[i][j].setAccess(player_access.DIRECT);
				options.add(board_squares[i][j]);
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
		if(i<w) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.D);
			}
			
		}
		i=p.i;j=p.j+1;
		if(j<w) {
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
		if(i>=0 && j<w) {
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
		if(i<w&&j>=0) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.LD);
			}
		}
		i=p.i+1;j=p.j+1;
		if(i<w&&j<w) {
			if(board_squares[i][j].getAccessType()==player_access.NOT_AVAILBLE) {
				availble_moves+=checkPosToChainJump(i,j,direction.RD);
			}
		}
		return availble_moves;
	}
	public void autoMove(Player pl) {
		Random rd=new Random();
		ArrayList<Square>taws=pl.getTaws();
		chosen_squere=taws.get(rd.nextInt(taws.size()));
		if(chosen_squere.getAccessType()==player_access.PL) {
			setSquersUnclickeble();
			setAllPlayerSquersAccesses(player_access.NOT_AVAILBLE);
			chosen_squere.setAccess(player_access.PL);
			chosen_squere.choose();
			if(!ended) {
				firstMove(chosen_squere.getPosition());
			}
			moveTawTo(options.get(rd.nextInt(options.size())));
		}
		
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
		if(pl.checkWinState(board_squares, ba)) {
			changeTurn();
			WinnerPage winner=new WinnerPage(pl);
			winner.setVisible(true);
			ended=true;
		}
		
		
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
					if(!ended) {
						firstMove(chosen_squere.getPosition());
					}
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
					if(!ended) {
						changeTurn();
					}
				}
				else if(sqr.getAccessType()==player_access.JUMP){
					moveTawTo(sqr);
					if(!ended) {
						if(chainMove(chosen_squere.getPosition())==0) {
							changeTurn();
						}
					}
				}
			}
		}
		else {
			if(sqr.getAccessType()==player_access.DIRECT) {
				moveTawTo(sqr);
				if(!ended) {
					changeTurn();
				}
			}
			else if(sqr.getAccessType()==player_access.JUMP){
				moveTawTo(sqr);
				if(!ended) {
					if(chainMove(chosen_squere.getPosition())==0) {
						changeTurn();
					}
				}
			}
		}
	}
}
