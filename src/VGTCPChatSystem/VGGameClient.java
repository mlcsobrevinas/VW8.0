package VGTCPChatSystem;

import java.net.*;
import java.io.*;
import java.util.*;

import VGFrames.VGGameFrame;
import VGFrames.VGMainFrame;
import VGPanels.VGChatPanel;

public class VGGameClient {
	
	private ObjectInputStream sInput;
	private ObjectOutputStream sOutput;
	private Socket socket;
	
	private VGGameFrame gui;
	
	private String server, username;
	private int port;

	public VGGameClient(String server, int port, String username, VGGameFrame gui) {
		
		this.server = server;
		this.port = port;
		this.username = username;
		this.gui = gui;
	
	}
	
	public boolean start() {
		
		try {
			
			socket = new Socket(server, port);
			System.out.println("created a socket");
			
		} catch(Exception e) {
			System.out.println("Error connection to server.");
			return false;
		}
		
		System.out.println("Connection accepted");
		
		try {
			
			sInput = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
			
		} catch(IOException e) {
			System.out.println("Error in client.java");
			return false;
		}
		
		new ListenFromServer().start();
		
		try {
			sOutput.writeObject(username);
		} catch (IOException e) {
			disconnect();
			return false;
		}
	
		return true;
	}
	
	public void display(String msg) {
		
		System.out.println("I should append this at the GUI.");
		
	}
	
	public boolean sendMessage(ChatMessage msg) {
		
		try {
			sOutput.writeObject(msg);
			
			return true;
		} catch(IOException e) {
			//exception
			
			return false;
		}
		
	}
	
	public void disconnect() {
		
		try {
			if(sInput != null) sInput.close();
		} catch (Exception e) {}
		try {
			if(sOutput != null) sOutput.close();
		} catch (Exception e) {}
		try {
			if(socket != null) socket.close();
		} catch (Exception e) {}
		
	}
	
	class ListenFromServer extends Thread {
		
		public void run() {
			
			System.out.println("Trying to listen to the server...");
			
			while(true) {
				try {
					
					String msg = (String) sInput.readObject();
					//.updateEntry(msg);
					
				} catch (IOException e) {
					break;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					break;
				}
			}
		}
		
	}
}
