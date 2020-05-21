package main;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {
	private ImageIcon icon;
	private ImageIcon icon_chosen;
	private int number;
	public enum player_type{AI,PL}
	public player_type type;
	private ArrayList<Square>taws ;
	public Player(int number,PlayersIcon picons,int icon_num,player_type type) {
		this.type=type;
		this.number=number;
		icon=picons.icons[icon_num-1];
		icon_chosen=picons.icons_c[icon_num-1];
		taws=new ArrayList<Square>();
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
	
}
