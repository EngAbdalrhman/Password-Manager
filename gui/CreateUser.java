package passwordmanager.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class CreateUser {

	JFrame frame;
	private JTextField textField;
	Image see = null;
	Image hide = null;
	ImageIcon seeIcon = null , hideIcon = null;
	boolean isHide = false;
	private JPasswordField passwordField;
	String txthide = "unhide";
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser window = new CreateUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 314, 302);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(93, 44, 140, 20);
		frame.getContentPane().add(textField);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(10, 47, 73, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel label_1 = new JLabel("password");
		label_1.setBounds(10, 72, 66, 14);
		frame.getContentPane().add(label_1);
		
		JButton btnCreate = new JButton("create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreate.setBounds(51, 191, 89, 23);
		frame.getContentPane().add(btnCreate);
		

		//ImageIcon seeIcon = null , hideIcon = null; // load the image to a imageIcon
		Image image = null; //seeIcon.getImage(); // transform it 
		// Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		// imageIcon = new ImageIcon(newimg);  // transform it back

		
		try {
			 see = ImageIO.read(getClass().getResource("images\\see.png"));
			 seeIcon = new ImageIcon(see); // load the image to a imageIcon
			 image = seeIcon.getImage(); // transform it 
			 image = GuiFunction.getScaledImage(image, 35, 35);
			 seeIcon = new ImageIcon(image);
			 hide = ImageIO.read(getClass().getResource("images\\hide.png"));
			 hideIcon = new ImageIcon(hide); // load the image to a imageIcon
			 image = hideIcon.getImage(); // transform it 
			 image = GuiFunction.getScaledImage(image, 30, 30);
			 hideIcon = new ImageIcon(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnNewButton = new JButton();
		btnNewButton.setIcon(seeIcon);
		btnNewButton.setToolTipText(txthide);

		btnNewButton.addActionListener(e -> {
			if(!isHide) 
			{
				btnNewButton.setIcon(hideIcon);
				isHide = true;
				passwordField.setEchoChar('\u0000'); // set to zero to disable
				txthide = "hide";
				btnNewButton.setToolTipText(txthide);
			}
			else 
			{
				btnNewButton.setIcon(seeIcon);
				isHide = false;
				passwordField.setEchoChar('•');
				txthide = "unhide";
				btnNewButton.setToolTipText(txthide);
			}
		});
		
		/*btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setIcon(new ImageIcon());
			}
		});*/
		btnNewButton.setBounds(243, 74, 38, 23);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(93, 75, 140, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.frmLogin.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(143, 191, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("User Exist!");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(10, 225, 271, 14);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 116, 66, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(93, 113, 140, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(10, 141, 66, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(93, 144, 140, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Birth DATE");
		lblNewLabel_3.setBounds(10, 166, 66, 14);
		frame.getContentPane().add(lblNewLabel_3);
	}
	
}
