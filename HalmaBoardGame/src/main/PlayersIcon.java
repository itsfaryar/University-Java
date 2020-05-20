package main;


import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class PlayersIcon {
	public ImageIcon[] icons=new ImageIcon[7];
	public ImageIcon pl_none;
	public PlayersIcon(){
		pl_none=new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		icons[0]=new ImageIcon("Players/1.png");
		icons[1]=new ImageIcon("Players/2.png");
		icons[2]=new ImageIcon("Players/3.png");
		icons[3]=new ImageIcon("Players/4.png");
		icons[4]=new ImageIcon("Players/5.png");
		icons[5]=new ImageIcon("Players/6.png");
		icons[6]=new ImageIcon("Players/7.png");
	}
	
}
