package VGCustomClasses;
import java.io.*; 
import java.net.*;

import VGFrames.VGGameFrame;

import java.io.*; import java.net.*; 
class VGServerUDP {   
public static void main(String args[]) throws Exception{          
	 DatagramSocket serverSocket = new DatagramSocket(9876);            
	 byte[] receiveData = new byte[1024];             
	 byte[] sendData = new byte[1024];     
	 String currentPlayer;
	 String monsterS;
	 int[] monsterPerCount = new int[8];
	 int playerCoins;
	 int playerLevel;
	 
	 while(true){             
		
		 String goldS;
		 String level;
		 DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);                   
		 serverSocket.receive(receivePacket);                   
		 String sentence = new String( receivePacket.getData());    
		 System.out.println("RECEIVED: " + sentence);
		 String delims = "[_]";
		 String[] tokens = sentence.split(delims);
		 
		 currentPlayer = tokens[0];
		 level = tokens[1];
		 monsterS = tokens[2];
		 goldS = tokens[3];
		
		 System.out.println("RECEIVED: " + sentence);                   
		 InetAddress IPAddress = receivePacket.getAddress();                  
		 int port = receivePacket.getPort();                   
		 String capitalizedSentence = sentence.toUpperCase();                   
		 sendData = capitalizedSentence.getBytes();                   
		 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);                  
		 serverSocket.send(sendPacket); 
		// playerCoins = Integer.parseInt(goldS);
		// playerLevel = Integer.parseInt(level);
		 
		 openNewFrame(currentPlayer, level, monsterPerCount,goldS);
			} 
		} 
   

     public static void openNewFrame(String name, String level, int[] troops, String money)
     {
    	// VGGameStates temp = new VGGameStates(name,level, troops, money);
    	 VGGameFrame vgObject = new VGGameFrame(name, "port");
     }
	
  

} 