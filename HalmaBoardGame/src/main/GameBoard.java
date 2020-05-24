package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

public class GameBoard  implements ActionListener{

	private JFrame frame;
	private JMenuBar menu_bar;
	private JButton change_move;
	private int players_count;
	private Core core;
	private PlayersIcon picons;
	private BoardAreas ba;
	NewGameFrame gsetting;
	private JLabel pl_info;
	private int w,n;
	public void startCore(Player pls[],int players_count) {
		this.players_count=players_count;
		core.clearAllSquers();
		core.setPlayers(pls);
		core.startTurns(players_count);
		
	}
	

	/**
	 * Create the application.
	 */
	
	public GameBoard(int w,int n) {

		this.w=w;
		this.n=n;
		ba=new BoardAreas(w,n);
		this.picons=new PlayersIcon(w);
		pl_info=new JLabel();
		core=new Core(ba,picons,w,n,pl_info);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.getContentPane().add(core);
		//frame.getContentPane().setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        //board.add(tools, BorderLayout.PAGE_START);
        
        menu_bar=new JMenuBar();
        frame.getContentPane().add(menu_bar, BorderLayout.PAGE_START);
    
        menu_bar.add(pl_info);
        //menu_bar.add(pl_icon);
        menu_bar.add(new JSeparator());
        change_move=new JButton("End Move");
        change_move.addActionListener(this);
        menu_bar.add(change_move);
        
       
       
        //menu_new.add(new Actiona)
        //menu_bar.add(two_pl);
        //tools.add(menu_new); 
        tools.addSeparator();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationByPlatform(true);
		//frame.pack();
        frame.setMaximizedBounds(new Rectangle(700, 700));
        frame.pack();
		frame.setMinimumSize(new Dimension(200, 200));
	}
	
	public void setFrameVisible(boolean b ) {
		frame.setVisible(b);
	}
	public void disposeFrame() {
		frame.dispose();
	}
	public void actionPerformed(ActionEvent e) {  
		if(e.getSource()==change_move) {
			if(core.is_playing())core.changeTurn();
		}
		
	}
	
}
