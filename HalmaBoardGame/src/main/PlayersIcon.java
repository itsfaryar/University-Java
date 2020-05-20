package main;

import javax.swing.ImageIcon;

public class PlayersIcon {
	public ImageIcon[] icons=new ImageIcon[7];
	public ImageIcon[] icons_c=new ImageIcon[7];
	public ImageIcon pl_none;
	public ImageIcon pl_none_c;
	public PlayersIcon(){
		pl_none=new ImageIcon("Players/none.png");
		pl_none_c=new ImageIcon("Players/none_c.png");
		icons[0]=new ImageIcon("Players/1.png");
		icons_c[0]=new ImageIcon("Players/1_c.png");
		icons[1]=new ImageIcon("Players/2.png");
		icons_c[1]=new ImageIcon("Players/2_c.png");
		icons[2]=new ImageIcon("Players/3.png");
		icons_c[2]=new ImageIcon("Players/3_c.png");
		icons[3]=new ImageIcon("Players/4.png");
		icons_c[3]=new ImageIcon("Players/4_c.png");
		icons[4]=new ImageIcon("Players/5.png");
		icons_c[4]=new ImageIcon("Players/5_c.png");
		icons[5]=new ImageIcon("Players/6.png");
		icons_c[5]=new ImageIcon("Players/6_c.png");
		icons[6]=new ImageIcon("Players/7.png");
		icons_c[6]=new ImageIcon("Players/7_c.png");
	}
	
}
