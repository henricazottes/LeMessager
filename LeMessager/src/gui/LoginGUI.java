    package gui;
     
    import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
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
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
     
import system.ChatController;
     
    public class LoginGUI extends JFrame implements ActionListener, WindowListener, FocusListener{
    	
    	       
        private ChatController cc;  
       
        public LoginGUI(ChatController cc){
        	super("LeMessager - Connection"); 
        	setBounds(100,100,300,400);   
        	this.setLocationRelativeTo(null);
        	this.cc = cc;
        	init();
        }
       
        void init(){      	
            
            this.setResizable(false);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        	
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Container con = this.getContentPane(); // inherit main frame
            con.add(panel); // add the panel to frame
            // customize panel here
            // pane.add(someWidget);
            
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
            
            /* ============
             *    Content
             * ============ */
                           
            JLabel logo = new JLabel(new ImageIcon("img/title_img.png"));
            logo.setAlignmentX(CENTER_ALIGNMENT);
            JTextField nickname = new JTextField();
            
            
            nickname.setText("Enter a nickname");
            nickname.setPreferredSize(new Dimension(250,30));
            nickname.setMaximumSize(nickname.getPreferredSize());
            nickname.setAlignmentX(CENTER_ALIGNMENT);
            //nickname.addFocusListener(this);
            
            JButton connect = new JButton("Connect");
            connect.setPreferredSize(new Dimension(250,30));
            connect.setMaximumSize(connect.getPreferredSize());
            connect.setAlignmentX(CENTER_ALIGNMENT);
            
            panel.add(Box.createRigidArea(new Dimension(0,15)));
            panel.add(logo);   
            panel.add(Box.createRigidArea(new Dimension(0,30)));
            panel.add(nickname);
            panel.add(Box.createRigidArea(new Dimension(0,10)));
            panel.add(connect);
            
            /*
            
             lnickname = new JLabel("Nickname: ");
             // lnickname.set
             tfnickname = new JTextField(10);
             bconnect = new JButton("Connect");
             bconnect.addActionListener(this);
             bdisconnect = new JButton("Disconnect");
             
             // Chat window
             //textPanel = new JPanel();
             taConv = new JTextArea(20,45);
             taConv.setEditable(false);
             taMsg = new JTextArea(2, 45);
             taMsg.setEditable(true);
             taUserlist = new JTextArea(2, 45);
             taUserlist.setEditable(false);
             
             // TEST
             taUserlist.setText("Henri\n Zakaria");
             // A VOIR // taUserList.setText(UserList.ToString());
             
             lAlfred = new JLabel("RocketTeamChatSystem");
             //textPanel.add("North", taConv);
             //textPanel.add("South", taMsg);
             
             this.setTitle("ChatSystem");
             this.setSize(300, 300);
            // configures the JFrame layout using a Grid layout
             this.setLayout(new GridLayout(3,2));
             // places the components in the layout for Connection
             this.displayConnect();
             this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit program when Windows closes
             // packs the fenetre: size is calculated
             // regarding the added components
             this.pack();
             // the JFrame is visible now
             this.setVisible(true);*/
            
            setVisible(true); // display this frame
        }
       
        void displayConnect(){
                this.add(tfnickname);
                this.add(bconnect);
                this.add(lAlfred);
        }
       
        void displayChat(){
                //this.remove(lnickname);
                this.remove(tfnickname);
                bconnect.removeActionListener(this);
                this.remove(bconnect);
                this.remove(lAlfred);
                this.setSize(600, 500);
                this.add(lnickname);
                this.add(taConv);
                bdisconnect.addActionListener(this);
                this.add(taUserlist);
                this.add(lAlfred);
                this.add(bdisconnect);
                this.add(taMsg);
               
        }
       
 
        @Override
        public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub
               
        }
 
        @Override
        public void windowClosed(WindowEvent e) {
               
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
                        this.lnickname.setText(tfnickname.getText());
                        this.cc.setMyName(tfnickname.getText());
                        this.displayChat(); // places the components in the layout for Chat
                        this.cc.processHello();
                }
                else if (e.getSource() == bdisconnect){
                        this.cc.processGoodbye();
                        try {
                                Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                        }
                        System.exit(0);
                }
        }
 
        public void updateList(String list) {
                this.taUserlist.setText(list);
               
        }

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == nickname){
				nickname.setText("");
				
			}
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    }

