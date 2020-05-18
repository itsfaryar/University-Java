package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.*;
import java.awt.event.*;

public class Gui implements ActionListener{

	private JFrame frame;
	private JMenuBar menu_bar;
	private JMenu menu_new;
	private JMenuItem new_tow,new_one,new_four;

	/**
	 * Launch the application.
	 */
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		
		//contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        //board.add(tools, BorderLayout.PAGE_START);
        menu_bar=new JMenuBar();
        frame.add(menu_bar, BorderLayout.PAGE_START);
        menu_new=new JMenu("New");
        new_tow=new JMenuItem("Tow Players");
        new_tow.addActionListener(this);
        menu_bar.add(menu_new);
        menu_new.add(new_tow);
        //menu_new.add(new Actiona)
        //menu_bar.add(two_pl);
        
        //tools.add(menu_new); 
        tools.addSeparator();
		Board gb=new Board();
		frame.add(gb.getBoard());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
	}
	public void actionPerformed(ActionEvent e) {    
		if(e.getSource()==new_tow)  {
			
		}
		
		
		}     

}
