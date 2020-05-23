package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Player.player_type;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class NewGameFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JComboBox comboBox;
	private JPanel panel;
	private JButton btn_ok;
	private JButton btn_cancel;
	private Player[]players;
	private JButton[]pl1_icons;
	private JButton[]pl2_icons;
	private JButton[]pl3_icons;
	private JButton[]pl4_icons;
	
	private int players_count=0;
	
	private player_type pl1_type;
	private player_type pl2_type;
	private player_type pl3_type;
	private player_type pl4_type;
	
	private int pl1_icon_index;
	private int pl2_icon_index;
	private int pl3_icon_index;
	private int pl4_icon_index;
	
	private JComboBox pl1_type_choose;
	private JLabel lblPlayer_1;
	private JPanel player1_options;
	
	private JComboBox pl2_type_choose;
	private JLabel lblPlayer_2;
	private JPanel player2_options;
	
	private JComboBox pl3_type_choose;
	private JLabel lblPlayer_3;
	private JPanel player3_options;
	
	private JLabel lblPlayer_4;
	private JComboBox pl4_type_choose;
	private JPanel player4_options;
	private GameBoard g;
	private JTextField wOfBoard;
	private JTextField cOfTaws;
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	
	public NewGameFrame(PlayersIcon picons) {
		players=new Player[4];
		pl1_icons=new JButton[7];
		pl2_icons=new JButton[7];
		pl3_icons=new JButton[7];
		pl4_icons=new JButton[7];
		pl1_icon_index=-1;
		pl2_icon_index=-1;
		pl3_icon_index=-1;
		pl4_icon_index=-1;
		pl1_type=player_type.PL;
		pl2_type=player_type.PL;
		pl3_type=player_type.PL;
		pl4_type=player_type.PL;
		setTitle("Start New Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 864, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String []game_modes= {"Choose Game Mode","Two Player Mode","Four Player Mode"};
		comboBox = new JComboBox(game_modes);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String o=comboBox.getSelectedItem().toString();
				if(o.equals("Two Player Mode")) {
					players_count=2;
					set_Pl1Section_Enable(true);
					set_Pl2Section_Enable(true);
					set_Pl3Section_Enable(false);
					set_Pl4Section_Enable(false);
				}
				else if(o.equals("Four Player Mode")) {
					players_count=4;
					set_Pl1Section_Enable(true);
					set_Pl2Section_Enable(true);
					set_Pl3Section_Enable(true);
					set_Pl4Section_Enable(true);
				}
				else {
					set_Pl1Section_Enable(false);
					set_Pl2Section_Enable(false);
					set_Pl3Section_Enable(false);
					set_Pl4Section_Enable(false);
				}
			}
		});
		contentPane.setLayout(null);
		comboBox.setBounds(554, 0, 162, 24);
		
		contentPane.add(comboBox);
		
		JLabel lblDimintionOfBoaed = new JLabel("dimintion of board");
		lblDimintionOfBoaed.setBounds(12, -14, 143, 52);
		contentPane.add(lblDimintionOfBoaed);
		
		panel = new JPanel();
		panel.setBounds(12, 36, 823, 286);
		contentPane.add(panel);
		panel.setLayout(null);
		//////////////////////////////////////
		
		lblPlayer_1 = new JLabel("Player 1");
		lblPlayer_1.setBounds(26, 25, 70, 15);
		panel.add(lblPlayer_1);
		
		pl1_type_choose = new JComboBox();
		pl1_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Player", "Computer"}));
		pl1_type_choose.setBounds(12, 52, 76, 24);
		panel.add(pl1_type_choose);
		
		player1_options = new JPanel();
		player1_options.setBounds(100, 24, 711, 52);
		player1_options.setLayout(new GridLayout(0,7));
		for(int i=0;i<pl1_icons.length;i++) {
			pl1_icons[i]= new JButton(picons.icons[i]);
			pl1_icons[i].addActionListener(this);
			pl1_icons[i].setBackground(Color.WHITE);
			player1_options.add(pl1_icons[i]);
		}
		panel.add(player1_options);
		
		/////////////////////////////////////
		
		lblPlayer_2 = new JLabel("Player 2");
		lblPlayer_2.setBounds(26, 89, 70, 15);
		panel.add(lblPlayer_2);
		
		pl2_type_choose = new JComboBox();
		pl2_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Player", "Computer"}));
		pl2_type_choose.setBounds(12, 116, 76, 24);
		panel.add(pl2_type_choose);
		
		player2_options = new JPanel();
		player2_options.setBounds(100, 88, 711, 52);
		player2_options.setLayout(new GridLayout(0, 7));
		for(int i=0;i<pl2_icons.length;i++) {
			pl2_icons[i]= new JButton(picons.icons[i]);
			pl2_icons[i].addActionListener(this);
			pl2_icons[i].setBackground(Color.WHITE);
			player2_options.add(pl2_icons[i]);
		}
		panel.add(player2_options);
		
		//////////////////////////////////////////
		lblPlayer_3 = new JLabel("Player 3");
		lblPlayer_3.setBounds(26, 153, 70, 15);
		panel.add(lblPlayer_3);
		
		pl3_type_choose = new JComboBox();
		pl3_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Player", "Computer"}));
		pl3_type_choose.setBounds(12, 180, 76, 24);
		panel.add(pl3_type_choose);
		
		player3_options = new JPanel();
		player3_options.setBounds(100, 152, 711, 52);
		player3_options.setLayout(new GridLayout(0, 7));
		for(int i=0;i<pl3_icons.length;i++) {
			pl3_icons[i]= new JButton(picons.icons[i]);
			pl3_icons[i].addActionListener(this);
			pl3_icons[i].setBackground(Color.WHITE);
			player3_options.add(pl3_icons[i]);
		}
		panel.add(player3_options);
		////////////////////////////////////////////////
		lblPlayer_4 = new JLabel("Player 4");
		lblPlayer_4.setBounds(26, 223, 70, 15);
		panel.add(lblPlayer_4);
		
		pl4_type_choose = new JComboBox();
		pl4_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Player", "Computer"}));
		pl4_type_choose.setBounds(12, 250, 76, 24);
		panel.add(pl4_type_choose);
		
		player4_options = new JPanel();
		player4_options.setBounds(100, 222, 711, 52);
		panel.add(player4_options);
		player4_options.setLayout(new GridLayout(0, 7));
		for(int i=0;i<pl4_icons.length;i++) {
			pl4_icons[i]= new JButton(picons.icons[i]);
			pl4_icons[i].addActionListener(this);
			pl4_icons[i].setBackground(Color.WHITE);
			player4_options.add(pl4_icons[i]);
		}

		/////////////////////////////////
		set_Pl1Section_Enable(false);
		set_Pl2Section_Enable(false);
		set_Pl3Section_Enable(false);
		set_Pl4Section_Enable(false);
		////////////////////////////////
		
		btn_cancel = new JButton("cancel");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btn_cancel.setBounds(362, 355, 90, 33);
		contentPane.add(btn_cancel);
		
		btn_ok = new JButton("OK");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(players_count==2) {
					players[0]=new Player(1,picons, pl1_icon_index+1,pl1_type);
					players[1]=new Player(2,picons, pl2_icon_index+1,pl2_type);
					players[2]=null;
					players[3]=null;
					g=new GameBoard(picons);
					g.startCore(players,2);
					setVisible(false);
					
				}
				else if(players_count==4) {
					players[0]=new Player(1,picons, pl1_icon_index+1,pl1_type);
					players[2]=new Player(2,picons, pl2_icon_index+1,pl2_type);
					players[3]=new Player(3,picons, pl3_icon_index+1,pl3_type);
					players[1]=new Player(4,picons, pl4_icon_index+1,pl4_type);
					g=new GameBoard(picons);
					g.startCore(players,4);
					setVisible(false);
				}
			}
		});
		btn_ok.setEnabled(false);
		btn_ok.setBounds(260, 355, 90, 33);
		contentPane.add(btn_ok);
		
		wOfBoard = new JTextField();
		wOfBoard.setBounds(154, 3, 90, 19);
		contentPane.add(wOfBoard);
		wOfBoard.setColumns(10);
		
		JLabel lblCountOfTaws = new JLabel("count of taws in row");
		lblCountOfTaws.setBounds(269, -14, 162, 52);
		contentPane.add(lblCountOfTaws);
		
		cOfTaws = new JTextField();
		cOfTaws.setColumns(10);
		cOfTaws.setBounds(428, 3, 90, 19);
		contentPane.add(cOfTaws);
		
	}
	public NewGameFrame(GameBoard g,PlayersIcon picons) {
		this.g=g;
		players=new Player[4];
		pl1_icons=new JButton[7];
		pl2_icons=new JButton[7];
		pl3_icons=new JButton[7];
		pl4_icons=new JButton[7];
		pl1_icon_index=-1;
		pl2_icon_index=-1;
		pl3_icon_index=-1;
		pl4_icon_index=-1;
		pl1_type=player_type.PL;
		pl2_type=player_type.PL;
		pl3_type=player_type.PL;
		pl4_type=player_type.PL;
		setTitle("Start New Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 864, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String []game_modes= {"Choose Game Mode","Two Player Mode","Four Player Mode"};
		comboBox = new JComboBox(game_modes);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String o=comboBox.getSelectedItem().toString();
				if(o.equals("Two Player Mode")) {
					players_count=2;
					set_Pl1Section_Enable(true);
					set_Pl2Section_Enable(true);
					set_Pl3Section_Enable(false);
					set_Pl4Section_Enable(false);
				}
				else if(o.equals("Four Player Mode")) {
					players_count=4;
					set_Pl1Section_Enable(true);
					set_Pl2Section_Enable(true);
					set_Pl3Section_Enable(true);
					set_Pl4Section_Enable(true);
				}
				else {
					set_Pl1Section_Enable(false);
					set_Pl2Section_Enable(false);
					set_Pl3Section_Enable(false);
					set_Pl4Section_Enable(false);
				}
			}
		});
		contentPane.setLayout(null);
		comboBox.setBounds(208, 0, 196, 24);
		
		contentPane.add(comboBox);
		
		panel = new JPanel();
		panel.setBounds(12, 36, 823, 286);
		contentPane.add(panel);
		panel.setLayout(null);
		//////////////////////////////////////
		
		lblPlayer_1 = new JLabel("Player 1");
		lblPlayer_1.setBounds(26, 25, 70, 15);
		panel.add(lblPlayer_1);
		
		pl1_type_choose = new JComboBox();
		pl1_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Player", "Computer"}));
		pl1_type_choose.setBounds(12, 52, 76, 24);
		panel.add(pl1_type_choose);
		
		player1_options = new JPanel();
		player1_options.setBounds(100, 24, 711, 52);
		player1_options.setLayout(new GridLayout(0,7));
		for(int i=0;i<pl1_icons.length;i++) {
			pl1_icons[i]= new JButton(picons.icons[i]);
			pl1_icons[i].addActionListener(this);
			pl1_icons[i].setBackground(Color.WHITE);
			player1_options.add(pl1_icons[i]);
		}
		panel.add(player1_options);
		
		/////////////////////////////////////
		
		lblPlayer_2 = new JLabel("Player 2");
		lblPlayer_2.setBounds(26, 89, 70, 15);
		panel.add(lblPlayer_2);
		
		pl2_type_choose = new JComboBox();
		pl2_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Player", "Computer"}));
		pl2_type_choose.setBounds(12, 116, 76, 24);
		panel.add(pl2_type_choose);
		
		player2_options = new JPanel();
		player2_options.setBounds(100, 88, 711, 52);
		player2_options.setLayout(new GridLayout(0, 7));
		for(int i=0;i<pl2_icons.length;i++) {
			pl2_icons[i]= new JButton(picons.icons[i]);
			pl2_icons[i].addActionListener(this);
			pl2_icons[i].setBackground(Color.WHITE);
			player2_options.add(pl2_icons[i]);
		}
		panel.add(player2_options);
		
		//////////////////////////////////////////
		lblPlayer_3 = new JLabel("Player 3");
		lblPlayer_3.setBounds(26, 153, 70, 15);
		panel.add(lblPlayer_3);
		
		pl3_type_choose = new JComboBox();
		pl3_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Player", "Computer"}));
		pl3_type_choose.setBounds(12, 180, 76, 24);
		panel.add(pl3_type_choose);
		
		player3_options = new JPanel();
		player3_options.setBounds(100, 152, 711, 52);
		player3_options.setLayout(new GridLayout(0, 7));
		for(int i=0;i<pl3_icons.length;i++) {
			pl3_icons[i]= new JButton(picons.icons[i]);
			pl3_icons[i].addActionListener(this);
			pl3_icons[i].setBackground(Color.WHITE);
			player3_options.add(pl3_icons[i]);
		}
		panel.add(player3_options);
		////////////////////////////////////////////////
		lblPlayer_4 = new JLabel("Player 4");
		lblPlayer_4.setBounds(26, 223, 70, 15);
		panel.add(lblPlayer_4);
		
		pl4_type_choose = new JComboBox();
		pl4_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Player", "Computer"}));
		pl4_type_choose.setBounds(12, 250, 76, 24);
		panel.add(pl4_type_choose);
		
		player4_options = new JPanel();
		player4_options.setBounds(100, 222, 711, 52);
		panel.add(player4_options);
		player4_options.setLayout(new GridLayout(0, 7));
		for(int i=0;i<pl4_icons.length;i++) {
			pl4_icons[i]= new JButton(picons.icons[i]);
			pl4_icons[i].addActionListener(this);
			pl4_icons[i].setBackground(Color.WHITE);
			player4_options.add(pl4_icons[i]);
		}

		/////////////////////////////////
		set_Pl1Section_Enable(false);
		set_Pl2Section_Enable(false);
		set_Pl3Section_Enable(false);
		set_Pl4Section_Enable(false);
		////////////////////////////////
		
		btn_cancel = new JButton("cancel");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btn_cancel.setBounds(362, 355, 90, 33);
		contentPane.add(btn_cancel);
		
		btn_ok = new JButton("OK");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(players_count==2) {
					players[0]=new Player(1,picons, pl1_icon_index+1,pl1_type);
					players[1]=new Player(2,picons, pl2_icon_index+1,pl2_type);
					players[2]=null;
					players[3]=null;
					g.startCore(players,2);
					setVisible(false);
				}
				else if(players_count==4) {
					players[0]=new Player(1,picons, pl1_icon_index+1,pl1_type);
					players[2]=new Player(2,picons, pl2_icon_index+1,pl2_type);
					players[3]=new Player(3,picons, pl3_icon_index+1,pl3_type);
					players[1]=new Player(4,picons, pl4_icon_index+1,pl4_type);
					g.startCore(players,4);
					setVisible(false);
				}
			}
		});
		btn_ok.setEnabled(false);
		btn_ok.setBounds(260, 355, 90, 33);
		contentPane.add(btn_ok);
		
	}
	public void set_Pl1Section_Enable(boolean bool) {
		lblPlayer_1.setEnabled(bool);
		pl1_type_choose.setEnabled(bool);
		for(int i=0;i<pl1_icons.length;i++) {
			pl1_icons[i].setEnabled(bool);
		}
	}
	public void set_Pl2Section_Enable(boolean bool) {
		lblPlayer_2.setEnabled(bool);
		pl2_type_choose.setEnabled(bool);
		for(int i=0;i<pl2_icons.length;i++) {
			pl2_icons[i].setEnabled(bool);
		}
	}
	public void set_Pl3Section_Enable(boolean bool) {
		lblPlayer_3.setEnabled(bool);
		pl3_type_choose.setEnabled(bool);
		for(int i=0;i<pl3_icons.length;i++) {
			pl3_icons[i].setEnabled(bool);
		}
	}
	public void set_Pl4Section_Enable(boolean bool) {
		lblPlayer_4.setEnabled(bool);
		pl4_type_choose.setEnabled(bool);
		for(int i=0;i<pl4_icons.length;i++) {
			pl4_icons[i].setEnabled(bool);
		}
	}
	public void setAllBackgroundsWhite(JButton[] bs) {
		for(int i=0;i<bs.length;i++) {
			bs[i].setBackground(Color.WHITE);
		}
	}
	public GameBoard getGameBoard(){
		return g;
	}
	public void disposeGame() {
		if(g!=null)g.dispose();
	}
	public void setGameBoardVisible(boolean b) {
		if(g!=null)g.setVisible(b);
	}
	public void findAndSetOption(JButton b) {
		boolean found=false;
		if(!found) {
			for(int i=0;i<pl1_icons.length;i++) {
				if(b==pl1_icons[i]) {
					found=true;
					pl1_icon_index=i;
					setAllBackgroundsWhite(pl1_icons);
					b.setBackground(Color.YELLOW);
				}
			}
		}
		if(!found) {
			for(int i=0;i<pl2_icons.length;i++) {
				if(b==pl2_icons[i]) {
					found=true;
					pl2_icon_index=i;
					setAllBackgroundsWhite(pl2_icons);
					b.setBackground(Color.YELLOW);
				}
			}
		}
		if(!found) {
			for(int i=0;i<pl3_icons.length;i++) {
				if(b==pl3_icons[i]) {
					found=true;
					pl3_icon_index=i;
					setAllBackgroundsWhite(pl3_icons);
					b.setBackground(Color.YELLOW);
				}
			}
		}
		if(!found) {
			for(int i=0;i<pl4_icons.length;i++) {
				if(b==pl4_icons[i]) {
					found=true;
					pl4_icon_index=i;
					setAllBackgroundsWhite(pl4_icons);
					b.setBackground(Color.YELLOW);
				}
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent e) { 
		findAndSetOption((JButton)e.getSource());
		if(players_count==2) {
			if(pl1_icon_index>=0 && pl2_icon_index>=0) {
				this.btn_ok.setEnabled(true);
			}
		}
		else if(players_count==4) {
			if(pl1_icon_index>=0 && pl2_icon_index>=0 &&pl3_icon_index>=0 && pl4_icon_index>=0) {
				this.btn_ok.setEnabled(true);
			}
		}
		else {
			this.btn_ok.setEnabled(false);
		}
	}
}
