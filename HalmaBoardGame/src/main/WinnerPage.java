package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinnerPage extends JFrame {

	private JPanel contentPane;
	private WinnerDataBase db_winners;
	public WinnerPage(Player pl) {
		db_winners=new WinnerDataBase();
		db_winners.addNewWinner(pl);
		setTitle("Winner Page");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(12, 0, 416, 210);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel(resizeIcon(new ImageIcon("Data/Images/winner.png"),0.7));
		panel.add(label, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(12, 220, 406, 48);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblFaryar = new JLabel("congratulations "+pl.getName()+" ...");
		lblFaryar.setForeground(Color.LIGHT_GRAY);
		lblFaryar.setFont(new Font("Ubuntu", Font.BOLD, 25));
		lblFaryar.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblFaryar, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(12, 273, 208, 38);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("You won in "+pl.getMoveCounter()+" moves");
		lblNewLabel.setFont(new Font("Ubuntu", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(232, 273, 186, 38);
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JButton btnSeeAllWinners = new JButton("See All Winners");
		btnSeeAllWinners.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				db_winners=new WinnerDataBase();
				db_winners.display();
			}
		});
		btnSeeAllWinners.setFont(new Font("Ubuntu", Font.BOLD, 20));
		btnSeeAllWinners.setBackground(Color.LIGHT_GRAY);
		panel_3.add(btnSeeAllWinners, BorderLayout.CENTER);
		
		
	}
	public ImageIcon resizeIcon(ImageIcon image ,double per) {
		return new ImageIcon(image.getImage().getScaledInstance((int)(image.getIconWidth()*per),(int)(image.getIconHeight()*per), Image.SCALE_DEFAULT));
	}
}
