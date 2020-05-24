package main;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PlayersIcon {
	public ImageIcon[] icons=new ImageIcon[7];
	public ImageIcon[] icons_c=new ImageIcon[7];
	public ImageIcon pl_none;
	public ImageIcon pl_none_c;
	public PlayersIcon(int w){
		if(w>8) {
			w=(640/w);
		}
		else if(w==-1) {
			w=64;
		}
		pl_none=resizeIcon(new ImageIcon("Players/none.png"),w);
		pl_none_c=resizeIcon(new ImageIcon("Data/Players/none_c.png"),w);
		icons[0]=resizeIcon(new ImageIcon("Data/Players/1.png"),w);
		icons_c[0]=resizeIcon(new ImageIcon("Data/Players/1_c.png"),w);
		icons[1]=resizeIcon(new ImageIcon("Data/Players/2.png"),w);
		icons_c[1]=resizeIcon(new ImageIcon("Data/Players/2_c.png"),w);
		icons[2]=resizeIcon(new ImageIcon("Data/Players/3.png"),w);
		icons_c[2]=resizeIcon(new ImageIcon("Data/Players/3_c.png"),w);
		icons[3]=resizeIcon(new ImageIcon("Data/Players/4.png"),w);
		icons_c[3]=resizeIcon(new ImageIcon("Data/Players/4_c.png"),w);
		icons[4]=resizeIcon(new ImageIcon("Data/Players/5.png"),w);
		icons_c[4]=resizeIcon(new ImageIcon("Data/Players/5_c.png"),w);
		icons[5]=resizeIcon(new ImageIcon("Data/Players/6.png"),w);
		icons_c[5]=resizeIcon(new ImageIcon("Data/Players/6_c.png"),w);
		icons[6]=resizeIcon(new ImageIcon("Data/Players/7.png"),w);
		icons_c[6]=resizeIcon(new ImageIcon("Data/Players/7_c.png"),w);
	}
	public ImageIcon resizeIcon(ImageIcon image ,int w) {
		return new ImageIcon(image.getImage().getScaledInstance(w,w, Image.SCALE_DEFAULT));
	}
	
}
