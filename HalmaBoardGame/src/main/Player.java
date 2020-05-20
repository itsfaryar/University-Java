package main;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {
	private ImageIcon icon;
	private int number;
	public enum player_type{AI,PL}
	public player_type type;
	private ArrayList<Square>taws ;
	public Player(int number,PlayersIcon picons,int icon_num,player_type type) {
		this.type=type;
		this.number=number;
		if(icon_num>0&&icon_num<8)icon=picons.icons[icon_num-1];
		taws=new ArrayList<Square>();
	}
	public ImageIcon getIcon() {
		return this.icon;
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
	public void enableTawsSquers() {
		for(int i=0;i<taws.size();i++) {
			taws.get(i).setClickble(true);
		}
	}
	
}
