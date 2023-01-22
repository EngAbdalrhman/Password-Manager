package passwordmanager.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window {

	private JFrame frmPasswordManager;

	/**
	 * Launch the application.
	 */
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPasswordManager = new JFrame();
		frmPasswordManager.setTitle("Password Manager");
		frmPasswordManager.setBounds(100, 100, 369, 275);
		frmPasswordManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPasswordManager.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(67, 58, 89, 23);
		frmPasswordManager.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CreateUser");
		btnNewButton_1.setBounds(197, 58, 89, 23);
		frmPasswordManager.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("About");
		btnNewButton_2.setBounds(143, 162, 69, 23);
		frmPasswordManager.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Help");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_3.setBounds(67, 106, 89, 23);
		frmPasswordManager.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Edit Path");
		btnNewButton_4.setBounds(197, 106, 89, 23);
		frmPasswordManager.getContentPane().add(btnNewButton_4);
	}
}
