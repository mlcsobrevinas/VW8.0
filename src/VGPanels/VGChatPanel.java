package VGPanels;

import VGTCPChatSystem.ChatMessage;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

import VGSingletons.VGPlayerSingleton;
import VGSingletons.VGPropertiesSingleton;

public class VGChatPanel extends JPanel {

	private JTextArea chatLog;
	private JTextField chatEntry;
	
	public VGChatPanel() {
		
		setLayout(new BorderLayout());
		setBackground(Color.RED);
		setProperties();
		
		chatLog = new JTextArea("Welcome to the Chat Room.\n",80,80);
		JPanel logPanel = new JPanel(new GridLayout(1,1));
		logPanel.add(new JScrollPane(chatLog));
		chatLog.setEditable(false);
		add(logPanel,BorderLayout.CENTER);
		
		chatEntry = new JTextField("Enter message here.");
		chatEntry.setBackground(Color.WHITE);
		JPanel entry = new JPanel(new GridLayout(1,1));
		entry.add(chatEntry);
		add(entry,BorderLayout.PAGE_START);
		
		chatEntry.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e){
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					
					if(VGPlayerSingleton.getClient().sendMessage(new ChatMessage(ChatMessage.MESSAGE, chatEntry.getText())));
					{
						
						System.out.println("SUCCESS!");
						chatEntry.setText("");
						
					}
				}
				
			}
			
		});
		
		chatEntry.addFocusListener(new FocusListener(){
			
			public void focusGained(FocusEvent e) {

				if (chatEntry.getText().equals("Enter message here.")) {
					chatEntry.selectAll();
				}
			        
			}
			  
			public void focusLost(FocusEvent e) {
			
				if(chatEntry.getText().trim().length() == 0)
				{
					chatEntry.setText("Enter message here.");
				}
				
			}
			  
		});
		
		
		if(!VGPlayerSingleton.getClient().start())
			return;
		
		updateEntry("Client has started.");
			
	}
	
	private void setProperties() {
		
		
		
	}
	
	public Dimension getPreferredSize() {
		
		return new Dimension(VGPropertiesSingleton.getInstance().getChatPanelDimension().width,VGPropertiesSingleton.getInstance().getChatPanelDimension().height);
		
	}
	
	public void setActionListerToTextField() {
		
		//chatEntry.addActi
		
	}
	
	public void updateEntry(String text) {
		
		String str = text;
		chatLog.append(str);
		
	}

}
