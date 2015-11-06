package VGFrames;

import java.awt.*;
import javax.swing.*;

import VGPanels.VGChatPanel;
import VGPanels.VGFieldPanel;
import VGPanels.VGMonstersPanel;
import VGPanels.VGStatsPanel;
import VGSingletons.VGPlayerSingleton;
import VGSingletons.VGPropertiesSingleton;

import java.util.*;

public class VGGameFrame extends JFrame  {
	
	public VGGameFrame(String pName, String ipAddress) {
		
		VGPlayerSingleton.setClient(ipAddress,1500,pName,this);
		VGPropertiesSingleton.getInstance().initializePanels();
		
		add(VGPropertiesSingleton.getInstance().getStatsPanel(), BorderLayout.PAGE_START);
		add(VGPropertiesSingleton.getInstance().getChatPanel(), BorderLayout.LINE_START);
		add(VGPropertiesSingleton.getInstance().getFieldPanel(), BorderLayout.CENTER);
		add(VGPropertiesSingleton.getInstance().getMonstersPanel(), BorderLayout.PAGE_END);
		
	    setResizable(false);
    	pack();
	    setLayout(new BorderLayout());
	    setSize(VGPropertiesSingleton.getInstance().getFrameDimension().width,VGPropertiesSingleton.getInstance().getFrameDimension().height);
		setVisible(true);
		
	}
	
}
