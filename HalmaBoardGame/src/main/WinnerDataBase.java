package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import main.Player.player_type;
public class WinnerDataBase  {

	
	private static final String path="Data/db_Winners/data.SVD";
	private ArrayList<Player>winners=new ArrayList<Player>();
    JTable j; 
	/**
	 * Create the frame.
	 */
	public WinnerDataBase() {
		readAllWinners();
	        // Data to be displayed in the JTable 
		  String[][] data=new String[winners.size()][5];
		for(int i=0;i<winners.size();i++) {
			Player pl=winners.get(i);
			data [i][0]=pl.getName();
			data [i][1]=""+pl.getMoveCounter();
			data [i][2]=""+pl.getW_of_board();
			data [i][3]=""+pl.getCountOfTaws();
			if(pl.type==player_type.PL) {
				data [i][4]="Human";
			}
			else {
				data [i][4]="Computer";
			}
			
		}
	       
	        // Column Names 
	        String[] columnNames = { "Name", "Moves", "Board Width","Count Of Taws" ,"Type"}; 
	  
	        // Initializing the JTable 
	        j = new JTable(data, columnNames); 
	        j.setBounds(30, 40, 200, 300); 

	} 
	public void display() {
		 JFrame f = new JFrame("ScrollGrid");
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        f.add(new JScrollPane(j));
	        f.pack();
	        f.setSize(512, 256);
	        f.setMinimumSize(f.getSize());
	        f.setLocationRelativeTo(null);
	        f.setVisible(true);
	}
	public void addNewWinner(Player pl) {
		winners.add(0,pl);
		saveAllWinners();
	}
	public void readAllWinners() {
		File info=new File(path);
		try {
			if(info.exists()) {
				Scanner data=new Scanner(info);
				winners= new ArrayList<Player>();
				while(true) {
					String name=data.next();
					
					if(name.charAt(0)=='#') break;
					int moves=data.nextInt();
					int w=data.nextInt();
					int n=data.nextInt();
					
					String str_type=data.next();
					
					Player.player_type type;
					if(str_type.equals("PL"))type=Player.player_type.PL;
					else type=Player.player_type.AI;
					Player pl=new Player(name, 0, null, 0, type);
					pl.setCount_of_taws(n);;
					pl.setW_of_board(w);
					pl.setMoveCounter(moves);
					winners.add(pl);
				}
			}
			else {
				info.getParentFile().mkdirs();
				saveAllWinners();
			}
		} catch (Exception e) {
		}
	}
	public void saveAllWinners() {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(path);
			fileWriter.write(codeData());
		    fileWriter.close();
		    
		} catch (IOException e) {}
	}
	public String codeData() {
		String out=new String();
		for(int i=0;i<winners.size();i++) {
			out+=winners.get(i).toString()+"\n";
		}
		out+="#";
		return out;
	}
	public ArrayList<Player>getWinners(){
		return winners;
	}

}
