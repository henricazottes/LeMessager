package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import system.ChatController;

public class ChatGui extends JFrame implements ActionListener, WindowListener{
	
	private ChatController cc;
	private JPanel textPanel;
	private JLabel lnickname;
	private JTextField tfnickname;
	private JButton bconnect;
	private JTextArea taConv;
	private JTextArea taMsg;
	void ChatGUI(){
		init();
	}
	
	void init(){
		
		 // Connect window
		 lnickname = new JLabel("Nickname: ");
		 tfnickname = new JTextField(10); 
		 bconnect = new JButton("Connect");
		 bconnect.addActionListener(this);
		 
		 
		 // Chat window
		 textPanel = new JPanel();
		 taConv = new JTextArea(20,45);
		 taMsg = new JTextArea(2, 45);
		 
		 
		 textPanel.add("North", taConv);
		 textPanel.add("South", taMsg);
		 
		 
		// configures the JFrame layout using a border layout
		 this.setLayout(new BorderLayout());
		 // places the components in the layout
		 this.displayChat();
		 
		 // packs the fenetre: size is calculated
		 // regarding the added components
		 this.pack();
		 // the JFrame is visible now
		 this.setVisible(true);
	}
	
	void displayConnect(){
		this.add("West", lnickname);
		this.add("Center", tfnickname);
		this.add("South", bconnect);
	}
	
	void displayChat(){
		
		
	}
	
	

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bconnect){
			this.cc.setMyName(tfnickname.getText());
			this.displayChat();
			this.cc.processHello();
		}
	}

}
