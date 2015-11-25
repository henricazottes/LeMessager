package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import models.User;
import system.ChatController;

public class ChatGUI extends JFrame implements ActionListener, WindowListener, FocusListener{
	
	private ChatController cc;
	
	public ChatGUI(ChatController cc){
		super(); 
		this.cc = cc;
    	setBounds(100,100,800,600);   
    	this.setLocationRelativeTo(null);
    	//String[] argsthis.cc = cc;
    	init();
	}
	
	void init(){
		
		
		this.setResizable(true);
		
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container con = this.getContentPane(); // inherit main frame
        con.add(panel); // add the panel to frame
		
		/* ============
         *     Menu 
         * ============ */
        
        this.setIconImage(new ImageIcon("img/title_img.png").getImage());
      //Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        //a group of JMenuItems                
        
        menuItem = new JMenuItem("Disconnect",new ImageIcon("img/logout.png"));
        menuItem.addActionListener(this);
		menu.add(menuItem);		
		menu.addSeparator();
		
		menuItem = new JMenuItem("About",new ImageIcon("img/about.png"));
		menu.add(menuItem);
		
		//Build second menu in the menu bar.
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_N);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Nickname",new ImageIcon("img/edit.png"));
		menu.add(menuItem);
        
        this.setJMenuBar(menuBar);
        
        // List part
        JPanel listPanel = new JPanel(new BorderLayout());
        //String[] data = {"Broadcast","Henri", "Alfred", "Helene", "Pierre", "Tartanpion", "Arthur", "Titicaca"};
        JList myList = new JList(cc.getUserList().getUserList().toArray());
        JScrollPane listScroller = new JScrollPane(myList);
        listScroller.setPreferredSize(new Dimension(220, listScroller.getHeight()));
        listPanel.add(listScroller, "West");
        
        EmptyBorder margin = new EmptyBorder(10,5,5,5);
        EmptyBorder marginBottom = new EmptyBorder(0,0,10,0);
        EmptyBorder padding = new EmptyBorder(10,5,10,5);
        Border people = new TitledBorder("People");
        Border depth = BorderFactory.createLoweredBevelBorder();       
        
        CompoundBorder peopleBorder = new CompoundBorder(margin, people);
        peopleBorder = new CompoundBorder(peopleBorder, padding);
        peopleBorder = new CompoundBorder(peopleBorder, depth);
        listScroller.setBorder(peopleBorder);
        
        // TextArea       
        JPanel convPanel = new JPanel();
        convPanel.setLayout(new BoxLayout(convPanel, BoxLayout.PAGE_AXIS));
        JTextArea recvMessage = new JTextArea();
        recvMessage.setEditable(false);
        
        convPanel.add(recvMessage);
        convPanel.setBorder(new CompoundBorder(marginBottom, depth));
        
        
        
        // Textfield + Button
        JTextField sendMessage = new JTextField();
        JButton send = new JButton("Send");
        //send.addActionListener(this);
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.LINE_AXIS));
        messagePanel.add(sendMessage);
        messagePanel.add(send);
        
        
        // Combined recv and send parts
        JPanel recvsendPanel = new JPanel();
        recvsendPanel.setLayout(new BorderLayout()); 
        
        recvsendPanel.add(convPanel, "Center");
        recvsendPanel.add(messagePanel, "South"); 
        
        Border conv = new TitledBorder("Conversation");
        CompoundBorder recvsendBorder = new CompoundBorder(margin, conv);
        recvsendBorder = new CompoundBorder(recvsendBorder, padding);
        recvsendPanel.setBorder(recvsendBorder);
        
        
        listPanel.add(recvsendPanel, "Center");
        
        
        
        
        panel.add(listPanel);
        
        
        
        //setVisible(true); // display this frame // now this method is called in LoginGUI to switch from Login to Chat
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.cc.processGoodbye();
		System.exit(0);
	}

	public void updateList(String userListText) {
		// TODO Auto-generated method stub
		System.out.println("Updated");
	}

}
