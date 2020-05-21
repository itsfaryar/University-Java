package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

public class Gui implements ActionListener{

	private JFrame frame;
	private JMenuBar menu_bar;
	private JMenu menu_new;
	private JMenuItem new_two,new_one,new_four;
	private JButton start_new;
	private JButton change_move;
	private Player[]players;
	private Core core;
	private PlayersIcon picons;
	private BoardAreas ba;
	/**
	 * Launch the application.
	 */
	public void startCore(Player pls[],int players_count) {
		core.clearAllSquers();
		core.setPlayers(pls);
		core.startTurns(players_count);
	}
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		ba=new BoardAreas();
		picons=new PlayersIcon();
		core=new Core(ba,picons);
		players=new Player[4];
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 1000);
		frame.getContentPane().add(core);
		//contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        //board.add(tools, BorderLayout.PAGE_START);
        
        menu_bar=new JMenuBar();
        frame.getContentPane().add(menu_bar, BorderLayout.PAGE_START);
        
        start_new=new JButton("New Game");
        start_new.addActionListener(this);
        menu_bar.add(start_new);
        menu_bar.add(new JSeparator());
        menu_new=new JMenu("New Game");
        menu_bar.add(menu_new);
        new_two=new JMenuItem("Tow Players");
        new_two.addActionListener(this);
        new_four=new JMenuItem("Four Players");
        new_four.addActionListener(this);
        menu_new.add(new_two);
        menu_new.add(new_four);
        
        change_move=new JButton("End Move");
        change_move.addActionListener(this);
        menu_bar.add(change_move);
        
       
       
        //menu_new.add(new Actiona)
        //menu_bar.add(two_pl);
        
        //tools.add(menu_new); 
        tools.addSeparator();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.pack();
        frame.setMinimumSize(frame.getSize());
	}
	public void actionPerformed(ActionEvent e) {  
		if(e.getSource()==start_new) {
			NewGameFrame new_game=new NewGameFrame(this,picons);
			new_game.setVisible(true);
		}
		else if(e.getSource()==new_two) {
			core.clearAllSquers();
			players[0]=new Player(1,picons, 1,player_type.PL);
			players[1]=new Player(2,picons, 2,player_type.PL);
			players[2]=null;
			players[3]=null;
			core.setPlayers(players);
			core.startTurns(2);
		}    
		else if(e.getSource()==new_four) {
			core.clearAllSquers();
			players[0]=new Player(1,picons, 1,player_type.PL);
			players[2]=new Player(2,picons, 2,player_type.PL);
			players[3]=new Player(3,picons, 3,player_type.PL);
			players[1]=new Player(4,picons, 4,player_type.PL);
			core.setPlayers(players);
			core.startTurns(4);
		}
		else if(e.getSource()==change_move) {
			if(core.is_playing())core.changeTurn();
		}
		
	}
}
