package passwordmanager.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import passwordmanager.GetPass;
import passwordmanager.filecontroler.FileControl;
import passwordmanager.model.Consts;

import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Color;

public class Main {

	JFrame frmDashboard;
	JMenu edit,help;    
	JMenuItem key1,key2,path,about,help1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmDashboard.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDashboard = new JFrame();
		frmDashboard.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/passwordmanager/gui/images/lock.ico")));
		frmDashboard.setTitle("DashBoard");
		frmDashboard.setBounds(100, 100, 292, 300);
		frmDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDashboard.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setBounds(29, 26, 61, 14);
		frmDashboard.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("add Site");
		btnNewButton.setBounds(29, 72, 89, 23);
		frmDashboard.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modify Site");
		btnNewButton_1.setBounds(29, 120, 89, 23);
		frmDashboard.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("MY Sites");
		btnNewButton_2.setBounds(147, 72, 89, 23);
		frmDashboard.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Remove Site");
		btnNewButton_3.setBounds(147, 120, 89, 23);
		frmDashboard.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("View Profile");
		btnNewButton_4.setBounds(29, 172, 89, 23);
		frmDashboard.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Modify Profile");
		btnNewButton_5.setBounds(147, 172, 89, 23);
		frmDashboard.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Delete Profile");
		btnNewButton_6.setBounds(29, 216, 89, 23);
		frmDashboard.getContentPane().add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Logout");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// remove save login
				Window w = new Window();
				w.frmPasswordManager.setVisible(true);
				frmDashboard.setVisible(false);
			}
		});
		btnNewButton_7.setBounds(147, 216, 89, 23);
		frmDashboard.getContentPane().add(btnNewButton_7);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(100, 26, 136, 14);
		frmDashboard.getContentPane().add(lblNewLabel_1);
		
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
		frmDashboard.getContentPane().add(menuBar);
		
		key1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent actionEvent) {
		    	String sk1=JOptionPane.showInputDialog(frmDashboard,"Enter Key 1");
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
		    	String sk2=JOptionPane.showInputDialog(frmDashboard,"Enter Key 2");
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
				frmDashboard.setVisible(false);
		    }
		});
		help1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent actionEvent) {
		    	Help help = new Help();
				help.hframe.setVisible(true);
		    }
		});
		about.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent actionEvent) {
				About ab = new About();
				ab.about.setVisible(true);
				frmDashboard.setVisible(false);
		    }
		});
	
		
	}

}
