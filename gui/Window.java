package passwordmanager.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

import passwordmanager.GetPass;
import passwordmanager.cryptography.Cryptography;
import passwordmanager.filecontroler.FileControl;
import passwordmanager.model.Consts;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Window {

	JFrame frmPasswordManager;
	JMenu edit,help;    
	JMenuItem key1,key2,path,about,help1;    
	private JProgressBar progressBar;
	JButton btnNewButton , btnNewButton_1;
	Thread t;
	/**
	 * Launch the application.
	 */
	
	//TODO save login , process bar to check if already login and hide buttons while checking , if not show button
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmPasswordManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String save = Consts.savedUser;
				if(save.equals("NULL") || save == null)
				{
					progressBar.setValue(100);
				}
				else
				{
					String path = Consts.path+save;
			        File file = new File(path+"\\"+save+".pm");
			        
			        GuiFunction.userProfileData = FileControl.read(file);
			        
			        GuiFunction.userProfileData.replace("LogName", Cryptography.decryption(GuiFunction.userProfileData.get("LogName"), Consts.k1, Consts.k2));
			        GuiFunction.userProfileData.replace("LogPass", Cryptography.decryption(GuiFunction.userProfileData.get("LogPass"), Consts.k1, Consts.k2));
			        
					Main m = new Main();
					m.frmDashboard.setVisible(true);
					frmPasswordManager.setVisible(false);
					
				}
			}
		});
		
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				t2.start();
				progressBar.setVisible(true);
				
				for (int i = 0; i <= 100; i++) {
					progressBar.setValue(i);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(progressBar.getValue()<100)
					{
						progressBar.setVisible(true);
						btnNewButton_1.setVisible(false);
						btnNewButton.setVisible(false);
					}
					else
					{
						btnNewButton_1.setVisible(true);
						btnNewButton.setVisible(true);
						progressBar.setVisible(false);
					}
				}
			}
		});
		t.start();
		
		
		
		
		// check save log
		// TODO save on initial info log name not in user profile
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPasswordManager = new JFrame();
		frmPasswordManager.setTitle("Password Manager");
		frmPasswordManager.setBounds(100, 100, 369, 176);
		frmPasswordManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPasswordManager.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.frmLogin.setVisible(true);
				frmPasswordManager.setVisible(false);
			}
		});
		btnNewButton.setBounds(67, 58, 89, 23);
		frmPasswordManager.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("CreateUser");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUser cu = new CreateUser();
				cu.frame.setVisible(true);
				frmPasswordManager.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(197, 58, 89, 23);
		frmPasswordManager.getContentPane().add(btnNewButton_1);
		
		// menu edit keys -> open dialog , modify path -> open window , help , about
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 353, 21);
		
		key1=new JMenuItem("Key1");    
		key2=new JMenuItem("Key2");    
		path=new JMenuItem("Path");    
		about=new JMenuItem("About");    
		help1=new JMenuItem("Help");    
		// key1.addActionListener(this);
		edit=new JMenu("Edit");    
		help=new JMenu("Help");
		edit.add(key1);
		edit.add(key2);
		edit.add(path);
		help.add(about); 
		help.add(help1);
		menuBar.add(edit);
		menuBar.add(help);    
		frmPasswordManager.getContentPane().add(menuBar);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(67, 33, 219, 14);
		frmPasswordManager.getContentPane().add(progressBar);

		
		
		key1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent actionEvent) {
		    	String sk1=JOptionPane.showInputDialog(frmPasswordManager,"Enter Key 1");
		    	String path = "\\bin\\";
				File info = new File(path + "inf.pm");
				String dpath  = Consts.path;
				int k1 = Consts.k1, k2 = Consts.k2;
				k1 = Integer.valueOf(sk1);
				String data = GetPass.data(dpath,k1,k2);
				FileControl.write(info, data);
		    }
		});
		key2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent actionEvent) {
		    	String sk2=JOptionPane.showInputDialog(frmPasswordManager,"Enter Key 2");
		    	String path = "\\bin\\";
				File info = new File(path + "inf.pm");
				String dpath  = Consts.path;
				int k1 = Consts.k1, k2 = Consts.k2;
				k2 = Integer.valueOf(sk2);
				String data = GetPass.data(dpath,k1,k2);
				FileControl.write(info, data);
		    }
		});
		path.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent actionEvent) {
				ModifyPath pf = new ModifyPath();
				pf.pathFrame.setVisible(true);
				frmPasswordManager.setVisible(false);
		    }
		});
		help1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent actionEvent) {
		    	Help help = new Help();
				help.hframe.setVisible(true);
				// frmPasswordManager.setVisible(false);
		    }
		});
		about.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent actionEvent) {
				About ab = new About();
				ab.about.setVisible(true);
				frmPasswordManager.setVisible(false);
		    }
		});
	}    
}
