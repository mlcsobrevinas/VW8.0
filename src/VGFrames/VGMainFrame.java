package VGFrames;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.ImageObserver;

import VGSingletons.VGPlayerSingleton;
import VGSingletons.VGPropertiesSingleton;

public class VGMainFrame extends JFrame implements ActionListener{
	
	JButton playButton;
	JButton menuButton;
    JPanel panel1;
	ImageIcon image1;
	JLabel label1;
	JTextArea nameTextArea;
	JTextArea portTextArea;
	String name;
	String ipAddress;
	String initialNameText = "Enter your name here.";
	String initialIPText = "Enter the IP address of the server.";
	  
	public VGMainFrame()  //constructor
	{
		
		  super("Main Frame");
		  setDefaultLookAndFeelDecorated(true);
		  addImage();    
		  addPanel();
		  setResizable(false);
		  setDefaultLookAndFeelDecorated(true);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  setSize(VGPropertiesSingleton.getInstance().getFrameDimension().width, VGPropertiesSingleton.getInstance().getFrameDimension().height);
		  setLocationRelativeTo(null);
		  setVisible(true);
		  
	  }
	 
	 public void addImage() {
		 
		 image1 = new ImageIcon(getClass().getResource("startScreen.png"));
		 panel1 = new JPanel(){    //Village War screen
			 
			 public void paintComponent(Graphics g)
			 {
				 Dimension d = getSize();
				 g.drawImage(image1.getImage(), 0, 0, d.width, d.height, null);
			 }
		 	};
		 	
	 }
	 
	 public void addPanel(){
		  getContentPane().add(panel1);
		  panel1.setLayout(null);  
		  nameTextArea = new JTextArea(initialNameText);       // Name of the player must be inserted here
		  nameTextArea.setBounds(400, 400, 200, 25);
		  portTextArea = new JTextArea(initialIPText);
		  portTextArea.setBounds(400, 430, 200, 25);
		  
		  nameTextArea.addFocusListener(new FocusListener(){
		  public void focusGained(FocusEvent e) {
			        if (nameTextArea.getText().equals(initialNameText)) {
			            nameTextArea.selectAll();
			       }
			  }
					@Override
					public void focusLost(FocusEvent e) {
					}
			  
		  });

		  portTextArea.addFocusListener(new FocusListener(){
			  public void focusGained(FocusEvent e) {
			        if (portTextArea.getText().equals(initialIPText)) {
			            portTextArea.selectAll();
			        
			       }
			  }
					@Override
					public void focusLost(FocusEvent e) {
					}
			  
		  });
		  panel1.add(nameTextArea);
		  panel1.add(portTextArea);
		  playButton = new JButton("Play");
		  playButton.addActionListener(this);
		  playButton.setBounds(410, 460, 70, 23);
		  panel1.add(playButton);  //ADD TO THE PANEL
		  menuButton = new JButton("How to Play");
		  menuButton.addActionListener(this);
		  menuButton.setBounds(490, 460, 100, 23);
		  panel1.add(menuButton);
	 }
	
	  public JButton getButton() {
		    return playButton;
		}
	  
	  public JButton getButton2(){
		  return menuButton;
	  }
	  
	  public JPanel getPanel() {
		    return panel1;
		}
	  
	  
	  public String getPlayerName() {
		    return nameTextArea.getText();
		}
	  
	  public String getAddress() {
		    return portTextArea.getText();
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == playButton ){
			
			 name = getPlayerName();
			 ipAddress = getAddress();
			 
			 VGPlayerSingleton.getInstance().setStats(name, ipAddress);
			 
			 if((nameTextArea.getText().trim().length() == 0 || portTextArea.getText().trim().length() == 0) || (nameTextArea.getText().equals(initialNameText) || portTextArea.getText().equals(initialIPText)))
			 {
				JOptionPane.showMessageDialog(this,  "Kindly fill out the empty boxes.", "Error", JOptionPane.WARNING_MESSAGE);	
			 }
			 else{
				 //If data are provided, start the game
				 
				 this.dispose();
				 new VGGameFrame(name, ipAddress);
			 }
		}
		
		if(e.getSource() == menuButton ){
		
			VGMechanicsFrame vgMechanicsFrame = new VGMechanicsFrame();
		
		}
		
		
	}
}

