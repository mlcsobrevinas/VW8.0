package VGCustomClasses;

import java.io.*; 
import java.net.*;
import java.util.Arrays; 
public class VGClientUDP {   
	String sentence;
	String monsterCount;
	int[] monsterPerCountReceived = new int[8];
	String playerLevel;
	String playerCoin;
	
	public void cRun(String pName, String ipAddress,int levelNo, int[] monsterCountReceived, int goldCount) throws IOException{
		int y;
		String countPerTroop;
		 BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));       
		 DatagramSocket clientSocket = new DatagramSocket();   
		 InetAddress IPAddress = InetAddress.getByName(ipAddress);  
		 byte[] sendData = new byte[1024];       
		 byte[] receiveData = new byte[1024];   
		 playerCoin = Integer.toString(goldCount);
		 playerLevel = Integer.toString(levelNo);
		 monsterCount = Arrays.toString(monsterCountReceived);
		 sentence =  pName+"_"+playerLevel+"_"+monsterCount+"_"+playerCoin;
		 //sentence = inFromUser.readLine();       
		 sendData = sentence.getBytes();       
		 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);       
		 clientSocket.send(sendPacket);       
		 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);     
		 clientSocket.receive(receivePacket);       
	     String modifiedSentence = new String(receivePacket.getData());      
	     System.out.println("FROM SERVER:" + modifiedSentence);              
	     clientSocket.close();    
	    
	}
	
	public void print( int[] monsterCountReceived)
	{
		System.out.println("Count Per troop" +Arrays.toString(monsterCountReceived));
	}
	
/*	public void insertPlayerData()
	{
		
		 int x;
		  for(x = 0; x<8; x++)
		  {
			  monsterPerCountReceived[x] = monsterCount[x];
		  }
		  
		  this.playerCoin = goldCount;
		  this.playerLevel = levelNo;
		  
		
	} */
	
	public void sendPositions()
	{
		
	}
	
	
	public void sendLevelNo()
	{
		
	}
	
   
	
 } 