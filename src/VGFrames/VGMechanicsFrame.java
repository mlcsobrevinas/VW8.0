package VGFrames;

import java.awt.*;
import javax.swing.*;
import java.io.*;

import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VGMechanicsFrame extends JFrame {
	
	CardLayout cl;//card layout for 2 jpanels
	JButton left;
	JButton right;
	//JButton mechanicsButton;
	JPanel mechanicsContainer;
	JPanel mechanicsPanel1;
	JPanel mechanicsPanel2;
	ImageIcon mechanics1;
	ImageIcon mechanics2;
	JButton home;
	
	public VGMechanicsFrame(){
		
		 setDefaultLookAndFeelDecorated(true);
		 getContentPane().setLayout(new BorderLayout());
		 cl = new CardLayout();
		 mechanicsContainer = new JPanel();
		 mechanicsPanel1 = new JPanel();
		 mechanicsPanel2 = new JPanel();
		 home = new JButton("Home");
		 	home.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		dispose(); //di gumagana sa conditions.java kaya dito nalang muna
		 	}
		 });
		 home.setBounds(438, 11, 95, 50);
		 left = new JButton("prev");
		 left.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
			 		cl.show(mechanicsContainer, "1");
			 	}
			 });
		 left.setBounds(899, 312, 95, 50);
		 right = new JButton("next");
		 right.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
			 		cl.show(mechanicsContainer, "2");
			 	}
			 });
		 right.setBounds(899, 312, 95, 50);
		 mechanics1 = new ImageIcon(getClass().getResource("mechanics1.png"));
		 mechanics2 = new ImageIcon(getClass().getResource("2mechanics.png"));

		 mechanicsPanel1 = new JPanel(){ //Add image 
			  public void paintComponent(Graphics g)
			  {
				  Dimension d = getSize();
				  g.drawImage(mechanics1.getImage(), 0, 0, d.width, d.height, null);
			  }
		      }; 
		      
		      mechanicsPanel2 = new JPanel(){ //Add image 
				  public void paintComponent(Graphics g)
				  {
					  Dimension d = getSize();
					  g.drawImage(mechanics2.getImage(), 0, 0, d.width, d.height, null);
				  }
			      }; 
		
	
			      
		 mechanicsContainer.setLayout(cl);
		 mechanicsContainer.add(mechanicsPanel1, "1");
		 mechanicsContainer.add(mechanicsPanel2, "2");
		 cl.show(mechanicsContainer, "1");
		 mechanicsPanel1.setLayout(null);
		 mechanicsPanel1.add(right);
		 mechanicsPanel2.setLayout(null);
		 mechanicsPanel2.add(left);
		 mechanicsPanel2.add(home);
		 getContentPane().add(mechanicsContainer, BorderLayout.CENTER);
		 
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setSize(1000, 700);
	     setLocationRelativeTo(null);
	     setVisible(true);
		
		
		 
	}
}
