package VGServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VGServerGUI extends JFrame implements ActionListener {

	private JTextArea status;
	private VGServerClass server;
	private JButton startStop;
	
	VGServerGUI() {
		
		setLayout(new BorderLayout());
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2,1));
		center.setBackground(Color.YELLOW);
		add(center);
		
		status = new JTextArea(100,100);
		status.setEditable(false);
		appendEvent("Server online.\n");
		center.add(status);
		
		JPanel buttons = new JPanel();
		buttons.setBackground(Color.BLUE);
		buttons.setLayout(new GridLayout(1,2));
		center.add(buttons);
		
		/*
		JButton start = new JButton("Start");
		start.addActionListener(this);
		buttons.add(start);
		*/
		
		startStop = new JButton("Start");
		startStop.addActionListener(this);
		buttons.add(startStop);
		
		pack();
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void appendEvent(String str) {
		
		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		//String event = sdf.format(new Date()) + ": " + str;
		status.append(str);
		status.setCaretPosition(status.getText().length() - 1);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		
		if(server != null)
		{
			server.stop();
			appendEvent("\nServer stopped.");
			server = null;
			startStop.setText("Start");
			return;
		}
		
		startStop.setText("Stop");
		
		server = new VGServerClass(1500, this);
		new ServerRunning().start();
		
	}
	
	public static void main(String[] args) {

		new VGServerGUI();

	}

	class ServerRunning extends Thread {
		
		public void run() {
			server.start();
			
			System.out.println("Server Crashed");
			//appendEvent("Server Crashed");
			server = null;
		}
		
	}
	
}
