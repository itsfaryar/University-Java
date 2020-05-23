package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

import main.Player.player_type;

public class GameBoard  extends JFrame implements ActionListener{

	private JFrame frame;
	private JMenuBar menu_bar;
	private JMenu menu_new;
	private JMenuItem new_two,new_one,new_four;
	private JButton change_sett;
	private JButton change_move;
	private Player[]players;
	private Core core;
	private PlayersIcon picons;
	private BoardAreas ba;
	NewGameFrame gsetting;

	public void startCore(Player pls[],int players_count) {
		core.clearAllSquers();
		core.setPlayers(pls);
		core.startTurns(players_count);
	}
	

	/**
	 * Create the application.
	 */
	
	public GameBoard(PlayersIcon picons) {
		ba=new BoardAreas();
		this.picons=picons;
		core=new Core(ba,picons,10,3);
		players=new Player[4];
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		
		//this = new Jthis();
		this.setBounds(100, 100, 640, 640);
		this.getContentPane().add(core);
		//contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        //board.add(tools, BorderLayout.PAGE_START);
        
        menu_bar=new JMenuBar();
        this.getContentPane().add(menu_bar, BorderLayout.PAGE_START);
        change_sett=new JButton("Restart This Game");
        change_sett.addActionListener(this);
        menu_bar.add(change_sett);
        menu_bar.add(new JSeparator());
        change_move=new JButton("End Move");
        change_move.addActionListener(this);
        menu_bar.add(change_move);
        
       
       
        //menu_new.add(new Actiona)
        //menu_bar.add(two_pl);
        
        //tools.add(menu_new); 
        tools.addSeparator();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationByPlatform(true);
		//this.pack();
       // this.setMinimumSize(this.getSize());
		//this.setMaximizedBounds(new Rectangle(640, 640));
	}
	public void actionPerformed(ActionEvent e) {  
		if(e.getSource()==change_sett) {
			if(gsetting==null)gsetting=new NewGameFrame(this,picons);
			gsetting.setVisible(true);
		}
		else if(e.getSource()==change_move) {
			if(core.is_playing())core.changeTurn();
		}
		
	}
}
