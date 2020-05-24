package main;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {
	private ImageIcon icon;
	private ImageIcon icon_chosen;
	private int number;
	public enum player_type{AI,PL}
	public player_type type;
	private ArrayList<Square>taws=null ;
	private String name;
	private int icon_num;
	private int move_counter;
	private int w_of_board;
	private int count_of_taws;
	public Player(String name,int number,PlayersIcon picons,int icon_num,player_type type) {
		this.name=name;
		this.type=type;
		this.number=number;
		this.icon_num=icon_num;
		move_counter=0;
		if(picons!=null) {
			icon=picons.icons[icon_num];
			icon_chosen=picons.icons_c[icon_num];
		}
		taws=new ArrayList<Square>();
	}
	public int getW_of_board() {
		return w_of_board;
	}
	public void setW_of_board(int w_of_board) {
		this.w_of_board = w_of_board;
	}
	public void setCount_of_taws(int c) {
		this.count_of_taws=c;
	}
	public int getCountOfTaws() {
		if(!(count_of_taws>0))count_of_taws=taws.size();
		return count_of_taws;
	}
	public String getName() {
		return name;
	}
	public ImageIcon getIcon() {
		return this.icon;
	}
	public ImageIcon getIconChosen() {
		return this.icon_chosen;
	}
	public int getNumber() {
		return this.number;
	}
	public void removeTaw(Square taw) {
		taws.remove(taw);
	}
	public void addTaw(Square taw) {
		taws.add(taw);
	}
	public void setAccessToAllTawsSquers(Square.player_access acs) {
		for(int i=0;i<taws.size();i++) {
			taws.get(i).setAccess(acs);
		}
	}
	public void setStarterTaws(Square[][]board_squares , BoardAreas ba) {
		ArrayList<Position>p=ba.getStarterPoints(this);
		if(p!=null) {
			for(int i=0;i<p.size();i++) {
				board_squares[p.get(i).i][p.get(i).j].setPlayer(this);
			}
		}
	}
	public boolean checkWinState(Square[][]board_squares ,BoardAreas ba) {
		boolean out=true;
		ArrayList<Position>p=ba.getWinnerArea(this);
		for(int i=0;i<p.size();i++){
			if(board_squares[p.get(i).i][p.get(i).j].getPlayer()!=this) {
				out=false;
				break;
			}
		}
		return out;
	}
	public void set_moved() {
		move_counter++;
	}
	public void setMoveCounter(int moves) {
		move_counter=moves;
	}
	public int getMoveCounter() {
		return move_counter;
	}
	public void refreshIcon(PlayersIcon picons) {
		icon=picons.icons[icon_num];
		icon_chosen=picons.icons_c[icon_num];
		
	}
	public ArrayList<Square>getTaws(){
		return taws;
	}
	public String toString() {
		return this.getName()+" "+this.getMoveCounter()+" "+this.getW_of_board()+" "+this.getCountOfTaws()+" "+this.type;
	}
}
