package VGSingletons;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import VGCustomClasses.VGMonster;
import VGPanels.VGChatPanel;
import VGPanels.VGFieldPanel;
import VGPanels.VGMonstersPanel;
import VGPanels.VGStatsPanel;

public class VGPropertiesSingleton {

	private static VGPropertiesSingleton singleton = null;
	
	
	private VGStatsPanel stats;
	private VGChatPanel chat;
	private VGFieldPanel field;
	private VGMonstersPanel monsters;
	
	
	private String[] monsterNames = {"Barbarian","Archer","Giant","Wallbreaker","Wizard","Hog Rider","Dragon","Wall"};
	private int[] monsterValues = {1,5,10,10,10,20,30,1};
	private int[] monsterMaxBuys = {100,75,20,26,5,20,10,25};
	
	private ArrayList<VGMonster> monstersList;
	
	private Dimension screenSize;
	private int width;
	private int height;
	
	private VGPropertiesSingleton() {
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int)(screenSize.width*.95); 
		height = (int)(screenSize.height*.90);
		
		monstersList = new ArrayList<VGMonster>();
		
		initializeMonsters();
		
	}
	
	public static VGPropertiesSingleton getInstance() {
		
		if(singleton == null) {
			singleton = new VGPropertiesSingleton();
		}
		
		return singleton;
		
	}
	
	public void initializePanels() {
		
		stats = new VGStatsPanel();
		chat = new VGChatPanel();
		field = new VGFieldPanel();
		monsters = new VGMonstersPanel();
		
	}
	
	public boolean initializeMonsters() {
		
		for(int i = 0; i<8 ; i++)
		{
			VGMonster monster = new VGMonster(monsterValues[i],monsterNames[i],monsterMaxBuys[i]);
			monstersList.add(monster);
		}
		
		return true;
		
	}
	
	public int getMonsterValues(int troop)
	{
		return monsterValues[troop];
	}

	public Dimension getFrameDimension() {
		
		return new Dimension(width,height);
		
	}
	
	public Dimension getMonstersPanelDimension() {
		
		return new Dimension(width,(int)(height*.22));
		
	}
	
	public Dimension getChatPanelDimension() {
		
		return new Dimension((int)(width*.25),(int)(height*.70));
		
	}
	
	public Dimension getFieldPanelDimension() {
		
		return new Dimension((int)(width*.75),(int)(height*.70));
		
	}
	
	public Dimension getStatsPanelDimension() {
		
		return new Dimension(width,(int)(height*.05));
		
	}
	
	public Dimension getMonsterButtonSizes() {
		
		return new Dimension((int)((height*.25)*.50),(int)((height*.25)*.50));
		
	}
	
	public VGFieldPanel getFieldPanel() {
		
		return field;
		
	}
	
	public VGChatPanel getChatPanel() {
		
		return chat;
		
	}
	
	public VGStatsPanel getStatsPanel() {
		
		return stats;
		
	}
	
	public VGMonstersPanel getMonstersPanel() {
		
		return monsters;
		
	}
	
}
