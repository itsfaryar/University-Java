package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

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
	private JLabel lblPlayer_1_1;
	private JPanel player1_options;
	private JTextField pl1_name;
	
	private JLabel lblPlayer_5;
	private JComboBox pl2_type_choose;
	private JLabel lblPlayer_2;
	private JPanel player2_options;
	private JTextField pl2_name;
	
	private JComboBox pl3_type_choose;
	private JLabel lblPlayer_3;
	private JLabel lblPlayer_5_1;
	private JPanel player3_options;
	private JTextField pl3_name;
	
	private JLabel lblPlayer_4;
	private JComboBox pl4_type_choose;
	private JPanel player4_options;
	private JTextField pl4_name;
	private GameBoard g;
	private JTextField wOfBoard;
	private JTextField cOfTaws;
	private JTextField textField;
	private JLabel lblW;
	private JLabel lblCountOfTaws;
	private PlayersIcon picons=new PlayersIcon(-1);
	private int w=0,n=0;
	private JLabel lblTawscounthint;private JLabel lblPlayer_5_2;
	/**
	 * Create the frame.
	private JTextField pl2_name;
	private JLabel lblPlayer_5;
	private JTextField pl3_name;
	private JTextField pl4_name;
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	
	public NewGameFrame() {
		setBackground(Color.DARK_GRAY);
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
		setBounds(100, 100, 864, 471);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
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
					set_genaralSett_Enable(true);
					lblTawscounthint.setText("number<W and number>0");
				}
				else if(o.equals("Four Player Mode")) {
					players_count=4;
					set_Pl1Section_Enable(true);
					set_Pl2Section_Enable(true);
					set_Pl3Section_Enable(true);
					set_Pl4Section_Enable(true);
					set_genaralSett_Enable(true);
					lblTawscounthint.setText("number<=W/2 and number>0");
				}
				else {
					set_Pl1Section_Enable(false);
					set_Pl2Section_Enable(false);
					set_Pl3Section_Enable(false);
					set_Pl4Section_Enable(false);
					set_genaralSett_Enable(false);
				
				}
				checkfields();
			}
		});
		contentPane.setLayout(null);
		comboBox.setBounds(15, 0, 162, 24);
		
		contentPane.add(comboBox);
		
		JLabel lblDimintionOfBoaed = new JLabel("W of board");
		lblDimintionOfBoaed.setForeground(Color.LIGHT_GRAY);
		lblDimintionOfBoaed.setBounds(250, -14, 143, 52);
		contentPane.add(lblDimintionOfBoaed);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(12, 36, 823, 329);
		contentPane.add(panel);
		panel.setLayout(null);
		//////////////////////////////////////
		
		lblPlayer_1 = new JLabel("name");
		lblPlayer_1.setForeground(Color.LIGHT_GRAY);
		lblPlayer_1.setBounds(10, 50, 70, 15);
		panel.add(lblPlayer_1);
		
		pl1_type_choose = new JComboBox();
		pl1_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Human", "Computer"}));
		pl1_type_choose.setBounds(70, 20, 100, 24);
		panel.add(pl1_type_choose);
		pl1_type_choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pl1_type_choose.getSelectedItem().toString()=="Computer") {
					pl1_type=player_type.AI;
				}
				else {
					pl1_type=player_type.PL;
				}
			}
		});
		
		player1_options = new JPanel();
		player1_options.setBackground(Color.DARK_GRAY);
		player1_options.setBounds(180, 10, 634, 52);
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
		lblPlayer_2.setForeground(Color.LIGHT_GRAY);
		lblPlayer_2.setBounds(5, 100, 70, 15);
		panel.add(lblPlayer_2);
		
		pl2_type_choose = new JComboBox();
		pl2_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Human", "Computer"}));
		pl2_type_choose.setBounds(70, 100, 100, 24);
		panel.add(pl2_type_choose);
		pl2_type_choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pl2_type_choose.getSelectedItem().toString()=="Computer") {
					pl2_type=player_type.AI;
				}
				else {
					pl2_type=player_type.PL;
				}
			}
		});
		
		player2_options = new JPanel();
		player2_options.setBackground(Color.DARK_GRAY);
		player2_options.setBounds(180, 88, 634, 52);
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
		lblPlayer_3.setForeground(Color.LIGHT_GRAY);
		lblPlayer_3.setBounds(5, 165, 70, 15);
		panel.add(lblPlayer_3);
		
		pl3_type_choose = new JComboBox();
		pl3_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Human", "Computer"}));
		pl3_type_choose.setBounds(70, 165, 100, 24);
		panel.add(pl3_type_choose);
		pl3_type_choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pl3_type_choose.getSelectedItem().toString()=="Computer") {
					pl3_type=player_type.AI;
				}
				else {
					pl3_type=player_type.PL;
				}
			}
		});
		
		player3_options = new JPanel();
		player3_options.setBackground(Color.DARK_GRAY);
		player3_options.setForeground(Color.DARK_GRAY);
		player3_options.setBounds(180, 152, 634, 52);
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
		lblPlayer_4.setForeground(Color.LIGHT_GRAY);
		lblPlayer_4.setBounds(5, 235, 70, 15);
		panel.add(lblPlayer_4);
		
		pl4_type_choose = new JComboBox();
		pl4_type_choose.setModel(new DefaultComboBoxModel(new String[] {"Human", "Computer"}));
		pl4_type_choose.setBounds(70, 235, 100, 24);
		panel.add(pl4_type_choose);
		pl4_type_choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pl4_type_choose.getSelectedItem().toString()=="Computer") {
					pl4_type=player_type.AI;
				}
				else {
					pl4_type=player_type.PL;
				}
			}
		});
		
		player4_options = new JPanel();
		player4_options.setBackground(Color.DARK_GRAY);
		player4_options.setForeground(Color.DARK_GRAY);
		player4_options.setBounds(180, 222, 634, 52);
		panel.add(player4_options);
		player4_options.setLayout(new GridLayout(0, 7));
		for(int i=0;i<pl4_icons.length;i++) {
			pl4_icons[i]= new JButton(picons.icons[i]);
			pl4_icons[i].addActionListener(this);
			pl4_icons[i].setBackground(Color.WHITE);
			player4_options.add(pl4_icons[i]);
		}
		
		pl1_name = new JTextField();
		pl1_name.setBounds(70, 50, 100, 19);
		Document doc = pl1_name.getDocument();
	    doc.addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent arg0) {
				checkfields();
			}
			public void insertUpdate(DocumentEvent arg0) {
				checkfields();
			}
			public void changedUpdate(DocumentEvent arg0) {
				checkfields();
			}
		});
		panel.add(pl1_name);
		pl1_name.setColumns(10);
		
		lblPlayer_1_1 = new JLabel("Player 1");
		lblPlayer_1_1.setForeground(Color.LIGHT_GRAY);
		lblPlayer_1_1.setEnabled(false);
		lblPlayer_1_1.setBounds(8, 25, 70, 15);
		panel.add(lblPlayer_1_1);
		
		pl2_name = new JTextField();
		pl2_name.setColumns(10);
		pl2_name.setBounds(75, 130, 100, 19);
		Document doc2 = pl2_name.getDocument();
	    doc2.addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent arg0) {
				checkfields();
			}
			public void insertUpdate(DocumentEvent arg0) {
				checkfields();
			}
			public void changedUpdate(DocumentEvent arg0) {
				checkfields();
			}
		});
		panel.add(pl2_name);
		
		lblPlayer_5 = new JLabel("name");
		lblPlayer_5.setForeground(Color.LIGHT_GRAY);
		lblPlayer_5.setEnabled(false);
		lblPlayer_5.setBounds(15, 130, 70, 15);
		panel.add(lblPlayer_5);
		
		pl3_name = new JTextField();
		pl3_name.setColumns(10);
		pl3_name.setBounds(75, 195, 100, 19);
		Document doc3 = pl3_name.getDocument();
	    doc3.addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent arg0) {
				checkfields();
			}
			public void insertUpdate(DocumentEvent arg0) {
				checkfields();
			}
			public void changedUpdate(DocumentEvent arg0) {
				checkfields();
			}
		});
		panel.add(pl3_name);
		
		lblPlayer_5_1 = new JLabel("name");
		lblPlayer_5_1.setForeground(Color.LIGHT_GRAY);
		lblPlayer_5_1.setEnabled(false);
		lblPlayer_5_1.setBounds(15, 195, 70, 15);
		panel.add(lblPlayer_5_1);
		
		pl4_name = new JTextField();
		pl4_name.setColumns(10);
		pl4_name.setBounds(75, 265, 100, 19);
		Document doc4 = pl4_name.getDocument();
	    doc4.addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent arg0) {
				checkfields();
			}
			public void insertUpdate(DocumentEvent arg0) {
				checkfields();
			}
			public void changedUpdate(DocumentEvent arg0) {
				checkfields();
			}
		});
		panel.add(pl4_name);
		
		
		
		lblPlayer_5_2 = new JLabel("name");
		lblPlayer_5_2.setForeground(Color.LIGHT_GRAY);
		lblPlayer_5_2.setEnabled(false);
		lblPlayer_5_2.setBounds(10, 268, 70, 15);
		panel.add(lblPlayer_5_2);
		
	
		
		btn_cancel = new JButton("cancel");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btn_cancel.setBounds(358, 389, 90, 33);
		contentPane.add(btn_cancel);
		
		btn_ok = new JButton("OK");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(players_count==2) {
					players[0]=new Player(pl1_name.getText(),1,picons, pl1_icon_index,pl1_type);
					players[1]=new Player(pl2_name.getText(),2,picons, pl2_icon_index,pl2_type);
					players[2]=null;
					players[3]=null;
					g=new GameBoard(w,n);
					picons=null;
					g.startCore(players,2);
					setVisible(false);
					
				}
				else if(players_count==4) {
					players[0]=new Player(pl1_name.getText(),1,picons, pl1_icon_index,pl1_type);
					players[2]=new Player(pl2_name.getText(),2,picons, pl2_icon_index,pl2_type);
					players[3]=new Player(pl3_name.getText(),3,picons, pl3_icon_index,pl3_type);
					players[1]=new Player(pl4_name.getText(),4,picons, pl4_icon_index,pl4_type);
					picons=null;
					g=new GameBoard(w,n);
					g.startCore(players,4);
					setVisible(false);
				}
			}
		});
		btn_ok.setEnabled(false);
		btn_ok.setBounds(256, 389, 90, 33);
		contentPane.add(btn_ok);
		
		wOfBoard = new JTextField();
		wOfBoard.setBounds(350, 0, 90, 19);
		contentPane.add(wOfBoard);
		wOfBoard.setColumns(10);
		wOfBoard.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent arg0) {
				w=getnumber(wOfBoard.getText());
				checkfields();
				
			}
			public void insertUpdate(DocumentEvent arg0) {
				w=getnumber(wOfBoard.getText());
				
				checkfields();
			}
			public void changedUpdate(DocumentEvent arg0) {
				w=getnumber(wOfBoard.getText());
				checkfields();
			}
		});
	
		lblCountOfTaws = new JLabel("count of taws in row");
		lblCountOfTaws.setForeground(Color.LIGHT_GRAY);
		lblCountOfTaws.setBounds(500, -14, 162, 52);
		contentPane.add(lblCountOfTaws);
		
		
		lblTawscounthint = new JLabel("");
		lblTawscounthint.setForeground(Color.LIGHT_GRAY);
		lblTawscounthint.setBounds(650, 20, 300, 15);
		contentPane.add(lblTawscounthint);
		
	
		lblW = new JLabel("W > 0 And even");
		lblW.setForeground(Color.LIGHT_GRAY);
		lblW.setBounds(350, 20, 120, 15);
		contentPane.add(lblW);

		
		cOfTaws = new JTextField();
		cOfTaws.setColumns(10);
		cOfTaws.setBounds(670, 3, 90, 19);
		cOfTaws.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent arg0) {
				n=getnumber(cOfTaws.getText());
				checkfields();
				
			}
			public void insertUpdate(DocumentEvent arg0) {
				n=getnumber(cOfTaws.getText());
				checkfields();
			}
			public void changedUpdate(DocumentEvent arg0) {
				n=getnumber(cOfTaws.getText());
				checkfields();
			}
		});
		contentPane.add(cOfTaws);
		
		/////////////////////////////////
		set_Pl1Section_Enable(false);
		set_Pl2Section_Enable(false);
		set_Pl3Section_Enable(false);
		set_Pl4Section_Enable(false);
		set_genaralSett_Enable(false);
		////////////////////////////////
		
	}
	
	public void set_Pl1Section_Enable(boolean bool) {
		lblPlayer_1_1.setEnabled(bool);
		lblPlayer_1.setEnabled(bool);
		pl1_name.setEnabled(bool);
		pl1_type_choose.setEnabled(bool);
		for(int i=0;i<pl1_icons.length;i++) {
			pl1_icons[i].setEnabled(bool);
		}
	}
	public void set_Pl2Section_Enable(boolean bool) {
		lblPlayer_2.setEnabled(bool);
		lblPlayer_5.setEnabled(bool);
		pl2_type_choose.setEnabled(bool);
		pl2_name.setEnabled(bool);
		for(int i=0;i<pl2_icons.length;i++) {
			pl2_icons[i].setEnabled(bool);
		}
	}
	public void set_Pl3Section_Enable(boolean bool) {
		lblPlayer_3.setEnabled(bool);
		pl3_type_choose.setEnabled(bool);
		pl3_name.setEnabled(bool);
		lblPlayer_5_1.setEnabled(bool);
		for(int i=0;i<pl3_icons.length;i++) {
			pl3_icons[i].setEnabled(bool);
		}
	}
	public void set_genaralSett_Enable(boolean bool) {
		cOfTaws.setEnabled(bool);
		wOfBoard.setEnabled(bool);
		lblTawscounthint.setText("");
	}
	public void set_Pl4Section_Enable(boolean bool) {
		lblPlayer_4.setEnabled(bool);
		pl4_type_choose.setEnabled(bool);
		pl4_name.setEnabled(bool);
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
		if(g!=null)g.disposeFrame();
	}
	public void setGameBoardVisible(boolean b) {
		if(g!=null)g.setFrameVisible(b);
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
	public int getnumber(String text) {
		int num=0;
		try {
			
			if(text.length()>0)num=Integer.parseInt(text);
		} catch (Exception e) {
			num=0;
		}
		return num;
		
	}
	public void checkfields() {
		boolean checked=false;
		if(players_count==2) {
			if(w>0 && w%2==0) {
				if(n>0 && n<w) {
					if(pl1_icon_index>=0 && pl2_icon_index>=0 && pl1_name.getText().length()>0 &&  pl2_name.getText().length()>0) {
						checked=true;
					}
				}
			}
		}
		else if(players_count==4) {
			if(w>0 && w%2==0) {
				if(n>0 && n<=w/2) {
					if(pl1_icon_index>=0 && pl2_icon_index>=0 &&pl3_icon_index>=0 && pl4_icon_index>=0 &&  pl1_name.getText().length()>0 && pl2_name.getText().length()>0 &&  pl3_name.getText().length()>0 && pl4_name.getText().length()>0) {
						checked=true;
					}
				}
			}
		}
	
		
		this.btn_ok.setEnabled(checked);
	}
	public void actionPerformed(ActionEvent e) { 
		
		findAndSetOption((JButton)e.getSource());
		checkfields();
	}
}
