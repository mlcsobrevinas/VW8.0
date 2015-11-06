package VGServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import VGTCPChatSystem.ChatMessage;

public class VGServerClass {

	private static int uniqueID;
	private ArrayList<ClientThread> al;
	private VGServerGUI gui;
	
	private SimpleDateFormat sdf;
	private int port;
	private boolean keepgoing;
	
	public VGServerClass(int port, VGServerGUI gui) {
		
		this.port = port;
		this.gui = gui;
		sdf = new SimpleDateFormat("HH:mm:ss");
		al = new ArrayList<ClientThread>();
		
	}
	
	public void start() {
		keepgoing = true;
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			
			while(keepgoing) {
				display("Waiting for clients at port " + port +".");
			
				Socket socket = serverSocket.accept();
				
				if(!keepgoing)
					break;
				
				ClientThread t = new ClientThread(socket);
				al.add(t);
				t.start();
			}
			
			System.out.println("number of clients: "+al.size());
			
			try {
				serverSocket.close();
				
				for(int i = 0; i<al.size() ; i++) {
					ClientThread tc = al.get(i);
					
					try {
						tc.sInput.close();
						tc.sOutput.close();
						tc.socket.close();
					} catch(Exception e) {
						//okay
					}
				}
				
			} catch(IOException e){
				System.out.println("Error in closing!");
			}
			
		} catch(IOException e) {
			System.out.println("Error in opening!");
		}
	}
	
	private void display(String msg) {
		
		String time = sdf.format(new Date()) + " " + msg;
		gui.appendEvent(time);
		
	}
	
	public void stop() {
		keepgoing = false;
		
		try {
			new Socket("localhost", port);
		} catch (Exception e) {
			
		}
	}
	
	private synchronized void broadcast(String message) {
		String time = sdf.format(new Date());
		String officialMessage = time + "<" + message + "\n";
		
		display(officialMessage);
		
		for(int i = al.size(); --i >= 0;) {
			ClientThread ct = al.get(i);
			
			ct.writeMsg(officialMessage);
		}
	}
	
	synchronized void remove(int id) {
		
		for(int i = 0 ; i<al.size() ; i++) {
			
			ClientThread ct = al.get(i);
			
			if(ct.id == id) {
				al.remove(i);
				return;
			}
			
		}
		
	}

	class ClientThread extends Thread {
		Socket socket;
		ObjectInputStream sInput;
		ObjectOutputStream sOutput;
		
		int id;
		
		String username;
		ChatMessage cm;
		String date;
		
		ClientThread(Socket socket) {
			
			id = ++uniqueID;
			this.socket = socket;
			
			System.out.println("Creating client thread");
			
			try {
				sOutput = new ObjectOutputStream(socket.getOutputStream());
				sInput = new ObjectInputStream(socket.getInputStream());
				
				username = (String) sInput.readObject();
				
				System.out.println("Added "+username);
				
			} catch (IOException e) {
				System.out.println("Exception creating input or output");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			date = new Date().toString() + "\n";
		}
		
		public void run() {
			boolean keepgoing = true;
			
			while(keepgoing) {
				
				try {
					cm = (ChatMessage) sInput.readObject();
				} catch (IOException e) {
					break;
				} catch (ClassNotFoundException e) {
					break;
				}
				
				String message = cm.getMessage();
				
				switch(cm.getType()) {
				
					case ChatMessage.MESSAGE:
						broadcast(" " + username + ": " + message);
						break;
					case ChatMessage.LOGOUT:
						System.out.println(username + "");
						break;
					}
				
				
			}
			
			remove(id);
			close();
		}
		
		private void close() {
			
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
		
		private boolean writeMsg(String msg) {
			
			if(!socket.isConnected()) {
				close();
				return false;
			}
			
			try {
				sOutput.writeObject(msg);
			} catch (IOException e) {
				
			}
			
			return true;
		}
		
	}

}
