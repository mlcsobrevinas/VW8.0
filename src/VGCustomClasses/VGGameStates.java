package VGCustomClasses;

public class VGGameStates{
	int[] countPerMonster = new int[8];
    int numberOfBricks;
    int level;
    int goldMoney;
    String name;
    int[][] wall;

	public VGGameStates(String pName,int levelNo, int[] monsterCount, int goldCount)
	{
		 this.level = levelNo;
		 this.goldMoney = goldCount;
		 this.name = pName;
		 System.out.println("Name from game states:" +name);
		 
		 int x;
		  for(x = 0; x<8; x++)
		  {
			  countPerMonster[x] = monsterCount[x];
		  }
		  
		 numberOfBricks = countPerMonster[7];
	}
   

	public void setLevel(int levelNo)
	{
		this.level = levelNo;
	}
	
	public void setGoldCount(int goldCount)
	{
		this.goldMoney = goldCount;
	}
	
	public void setName(String pName)
	{
		this.name = pName;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	
	public int getNoOfBricks()
	{
	   return numberOfBricks;
	}

	public int[] getCountPerMonster()
	{
		return countPerMonster;
	}
	
	public int[][] getPositionPerWall()
	{
		return wall;
	}
	
	
}
