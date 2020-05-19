package main;

import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Square extends JButton{
	public Square() {
		this.setMargin(new Insets(0, 0, 0, 0));
        this.setIcon(new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
	}
}
