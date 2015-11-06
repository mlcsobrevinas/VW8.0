package VGCustomClasses;

public class VGMonster {

	private int numberOfBuys = 0; 
	
	private int monsterCost;
	private String monsterName;
	private int maxNumberOfBuys;
	
	public VGMonster(int monsterCost, String monsterName, int maxNumberOfBuys) {
		
		this.monsterCost = monsterCost;
		this.monsterName = monsterName;
		this.maxNumberOfBuys = maxNumberOfBuys;
		
	}
	
	public void buyMonster() {
		
		numberOfBuys++;
		
	}
	
	public void deleteMonster() {
		
		numberOfBuys--;
		
	}
	
	public String getMonsterName() {
		
		return monsterName;
		
	}
	
	public int getMonsterCost() {
		
		return monsterCost;
		
	}
	
	public int getMaxNumberOfBuys() {
		
		return maxNumberOfBuys;
		
	}
	
}
