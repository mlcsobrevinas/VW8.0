package VGCustomClasses;

import VGSingletons.VGPropertiesSingleton;

import java.awt.Color;
import javax.swing.JButton;

public class VGMonsterButton extends JButton {

	public int uniqueId;
	public String monsterName;
	
	public VGMonsterButton(int number, String monsterName) {
		
		this.monsterName = monsterName;
		uniqueId = number++;
		
		setText(monsterName);
		setSize(VGPropertiesSingleton.getInstance().getMonsterButtonSizes());
		
		setBackground(Color.RED);
		
	}
	
	
	
	
	
}
