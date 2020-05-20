package main;

import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Square extends JButton{
	private PlayersIcon picons;
	private Player player;
	private Position p;
	private boolean is_clickble;
	
	public Square(Position p,PlayersIcon picons) {
		this.is_clickble=false;
		this.picons=picons;
		this.setMargin(new Insets(0, 0, 0, 0));
        this.setIcon(picons.pl_none);
        this.p=p;
        
	}
	public void setClickble(boolean inp) {
		this.is_clickble=inp;
		if(inp) {
			if(player==null)this.setIcon(picons.pl_none_c);
		}
		else {
			if(player==null)this.setIcon(picons.pl_none);
		}
	}
	public Position getPosition() {
		return p;
	}
	public boolean is_clickble() {
		return this.is_clickble;
	}
	public void setPlayer(Player pl) {
		this.player=pl;
		this.player.addTaw(this);
		this.setIcon(pl.getIcon());
		this.is_clickble=true;
	
	}
	public void unSetPlayer() {
		this.player.removeTaw(this);
		this.player=null;
		this.setIcon(picons.pl_none);
		this.is_clickble=true;
	
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
