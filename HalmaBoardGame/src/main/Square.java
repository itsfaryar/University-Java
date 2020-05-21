package main;

import java.awt.Insets;

import javax.swing.JButton;

public class Square extends JButton{
	public enum player_access{NONE,DIRECT,JUMP,PL,NOT_AVAILBLE}
	private PlayersIcon picons;
	private Player player;
	private Position p;
	private player_access access;
	public Square(Position p,PlayersIcon picons) {
		this.picons=picons;
		this.setMargin(new Insets(0, 0, 0, 0));
        this.setIcon(picons.pl_none);
        this.p=p;
        this.access=player_access.NONE;
        
	}
	public void setAccess(player_access inp) {
		this.access=inp;
		if(access==player_access.DIRECT || access==player_access.JUMP) {
			if(player==null)this.setIcon(picons.pl_none_c);
		}
		else {
			if(player==null)this.setIcon(picons.pl_none);
		}
		this.setText(access+"");
	}
	public Position getPosition() {
		return p;
	}
	public player_access getAccessType() {
		return this.access;
	}
	public void setPlayer(Player pl) {
		this.player=pl;
		this.player.addTaw(this);
		this.setIcon(pl.getIcon());
		this.access=player_access.PL;
	
	}
	public void unSetPlayer() {
		this.player.removeTaw(this);
		this.player=null;
		this.setIcon(picons.pl_none);
		this.access=player_access.NONE;
	
	}
	public Player getPlayer() {
		return player;
	}
	public void clear() {
		this.setIcon(picons.pl_none);
		this.player=null;
	}
	public void choose() {
		this.setIcon(player.getIconChosen());
	}
	public void unChoose() {
		this.setIcon(player.getIcon());
	}

}
