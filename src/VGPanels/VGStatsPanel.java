package VGPanels;

import java.awt.*;
import javax.swing.*;

import VGCustomClasses.VGGameStates;
import VGFrames.*;
import VGSingletons.VGPlayerSingleton;
import VGSingletons.VGPropertiesSingleton;

import java.util.*;

public class VGStatsPanel extends JPanel {
 
	JTextField playerName;
	JTextField levelNumber;
	JTextField goldCount;
 
	public VGStatsPanel() {
		
		setLayout(new GridLayout(1,3));
		
		add(new JPanel());
		
		JPanel info = new JPanel();
		info.setBackground(Color.RED);
		info.setLayout(new GridLayout(1,3));
		add(info);
		
		playerName = new JTextField();
		playerName.setEditable(false);
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		levelNumber = new JTextField();
		levelNumber.setEditable(false);
		levelNumber.setHorizontalAlignment(SwingConstants.CENTER);
		goldCount = new JTextField();
		goldCount.setEditable(false);
		goldCount.setHorizontalAlignment(SwingConstants.CENTER);
		info.add(playerName);
		info.add(levelNumber);
		info.add(goldCount);
		
		add(new JPanel());
		
		updateName();
		updateLevelNumber();
		updateGoldCount();
		
	}
	
	public void updateName() {

		playerName.setText("Player Name: " +VGPlayerSingleton.getInstance().getPlayerName());
		
	}
	
	public void updateLevelNumber() {
		
		levelNumber.setText("Level Number: " +VGPlayerSingleton.getInstance().getLevel());
		
	}
	
	public void updateGoldCount() {
		
		goldCount.setText("Gold Count: " +VGPlayerSingleton.getInstance().getGoldCoins());
		
	}
	
	public void updateHitCount(){
		
	}
	
	public Dimension getPreferredSize() {
		
		return new Dimension(VGPropertiesSingleton.getInstance().getStatsPanelDimension().width,VGPropertiesSingleton.getInstance().getStatsPanelDimension().height);
		
	}
	
}
