package VGPanels;

import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;
import VGSingletons.VGPlayerSingleton;
import VGSingletons.VGPropertiesSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VGFieldPanel extends JPanel {
	
	private JPanel buttonsPanel;
	private JPanel topPanel;
	private JButton[][] buttons = new JButton[10][10];
	private int[][] buttonsValue = new int[10][10];
	private JLabel labelTimer = new JLabel("Timer: ");
	private JLabel labelHit = new JLabel("Hit Count: ");
	private JLabel labelHealth = new JLabel("Health Status: ");
	private JLabel labelOpponent = new JLabel("Opponent: ");
	private JTextArea timerTextArea = new JTextArea(1,10);
	private JTextArea hitTextArea = new JTextArea(1,10);
	private JTextArea healthTextArea = new JTextArea(1,10);
	int flag = 0;
	int randXT;
	int randYT;
	int timer;
	int seconds = 300;
	int minute;
	int second;


	public VGFieldPanel() {
		
		setLayout(new BorderLayout());
		
		topPanel = new JPanel();
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(10,10,1,1));
		topPanel.setBackground(Color.GRAY);
		buttonsPanel.setBackground(Color.GREEN);
		topPanel.setPreferredSize(new Dimension(970, 35));  //width, height 
		buttonsPanel.setPreferredSize(new Dimension(950, 480));
		addTimer();
		timerStart();
	    
		add(topPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.CENTER);
		
		setBackground(Color.GREEN);
		setButtons();
		
	}
	
	public void addTimer()
	{
		
		add(topPanel);
		topPanel.add(labelTimer);
		labelTimer.setText("Timer: 5:00");
		
	
		//panel1.add(timerTextArea);
		topPanel.add(labelHit);
		topPanel.add(hitTextArea);

	}
	
	public void timerStart(){
		Timer timer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				seconds--;
				minute = (int) (TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60));
				second = (int) (TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60));
		
				labelTimer.setText("Timer: " + minute + ":" + second);
				
			}
			
		}
		
		);
		
	
		
		timer.start();
	}
	
	private void setButtons() {
		
		for(int i = 0 ; i<10 ; i+=1)
		{
			
			for(int j = 0 ; j<10 ; j+=1)
			{
				
				buttons[i][j] = new JButton();
				buttons[i][j].setEnabled(false);
				buttonsValue[i][j] = 10;
				buttonsPanel.add(buttons[i][j]);
				
				
			}
			
		}
		
	}
	
   public void position(){
	  Random random = new Random();
	  int randXB,randYB;
	  int count[] = VGPlayerSingleton.getInstance().getPlayerTroops();
	  
	  for(int i = 0; i<8; i++)
	  {
		  System.out.print(count[i]+"_");
	  }
	  
	  if(flag==0){
			
			
		 	 randXT = random.nextInt(6) + 4;
			 randYT = random.nextInt(6) + 4;
			
		    ImageIcon tower = new ImageIcon(getClass().getResource("tower.png"));  //insert picture in a button
			buttons[randXT][randYT].setIcon(tower);
			buttons[randXT][randYT].setEnabled(true);
			buttonsValue[randXT][randYT] = 8;
			flag++;
		  }
		  
	  
	  System.out.println("");

	 
	  for(int m=0; m<8; m++)
	  {
		  for(int n=0; n< count[m]; n++)
		  {
			  randXB = random.nextInt(9) + 1;
			  randYB = random.nextInt(9) + 1;
			  
			 if(buttonsValue[randXB][randYB] == 10 ){
				  randXB = random.nextInt(9) + 1;
				  randYB = random.nextInt(9) + 1;
				 positionTroop(m, randXB, randYB); 
			 } 
			
		  }
		
	  }
	
   
	   
	  
	
	  
   }
	
	public void attack() {
		 int troop[] = VGPlayerSingleton.getInstance().getPlayerTroops();
		 Random random = new Random();
		 int randXA,randYA;
		int timer;
	
			 while(buttonsValue[0][0] != 0)
			 {
				  for(int m=0; m<8; m++)
				  {
					  for(int n=0; n< troop[m]; n++)
					  {
						  randXA = random.nextInt(9) + 1;
						  randYA = random.nextInt(9) + 1;
						  
						  attackTroop(m, randXA, randYA); 
						//  buttons[randXB][randYB].setBackground(new JButton().getBackground());
						 
						
					  }
					
				  }
			 }
		
	}
	
	public void reset()
	{
		for(int i = 0 ; i<10 ; i+=1)
		{
			for(int j = 0 ; j<10 ; j+=1)
			{
				
				 buttons[i][j].setIcon(null);
				 buttons[i][j].setEnabled(false);
			}
			
		} 
		
		buttons[randXT][randYT].setIcon(null);
		buttons[randXT][randYT].setEnabled(false);
	   flag = 0;
	   VGPlayerSingleton.getInstance().resetGoldCount();
	   VGPropertiesSingleton.getInstance().getStatsPanel().updateGoldCount();
	   
	}
	
	public void positionTroop(int monster, int x, int y)
	{
		
	    ImageIcon troop = new ImageIcon(getClass().getResource(monster+".png"));  //insert picture in a button
				buttons[x][y].setIcon(troop);
				buttons[x][y].setEnabled(true);
				buttonsValue[x][y] = monster;
		/* switch(monster){
		    case 0: buttons[x][y].setBackground(Color.YELLOW);
		  			break;
		    case 1: buttons[x][y].setBackground(Color.PINK);
		    		break;
		    case 2: buttons[x][y].setBackground(Color.ORANGE);
		    		break;
		    case 3: buttons[x][y].setBackground(Color.WHITE);
		       		break;
		    case 4: buttons[x][y].setBackground(Color.BLUE);
		    		break;
		    case 5: buttons[x][y].setBackground(Color.BLACK);
		    		break;
		    case 6: buttons[x][y].setBackground(Color.MAGENTA);
		    		break;
		    case 7: buttons[x][y].setBackground(Color.RED);
		    		break;
	    }*/
	}
	
	public void attackTroop(int troop, int x, int y)
	{
		switch(troop){
	    case 0: buttons[x][y].setBackground(Color.YELLOW);
	  			break;
	    case 1: buttons[x][y].setBackground(Color.PINK);
	    		break;
	    case 2: buttons[x][y].setBackground(Color.ORANGE);
	    		break;
	    case 3: buttons[x][y].setBackground(Color.WHITE);
	       		break;
	    case 4: buttons[x][y].setBackground(Color.BLUE);
	    		break;
	    case 5: buttons[x][y].setBackground(Color.BLACK);
	    		break;
	    case 6: buttons[x][y].setBackground(Color.MAGENTA);
	    		break;
	    case 7: buttons[x][y].setBackground(Color.RED);
	    		break;
        }

	}
	
	public Dimension getPreferredSize() {
		
		return new Dimension(VGPropertiesSingleton.getInstance().getFieldPanelDimension().width,VGPropertiesSingleton.getInstance().getFieldPanelDimension().height);
	}

}
