package passwordmanager.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;

import passwordmanager.model.ModelBuilder;

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
	private JTextField tFUser;
	Image see = null;
	Image hide = null;
	ImageIcon seeIcon = null , hideIcon = null;
	boolean isHide = false;
	private JPasswordField passwordField;
	String txthide = "unhide";
	private JTextField tfName;
	private JTextField tFMail;
	private JTextField tFDate;
	
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
		
		tFUser = new JTextField();
		tFUser.setColumns(10);
		tFUser.setBounds(93, 44, 140, 20);
		frame.getContentPane().add(tFUser);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(10, 47, 73, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel label_1 = new JLabel("password");
		label_1.setBounds(10, 72, 66, 14);
		frame.getContentPane().add(label_1);
		
		
		

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
		
		tfName = new JTextField();
		tfName.setBounds(93, 113, 140, 20);
		frame.getContentPane().add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(10, 141, 66, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		tFMail = new JTextField();
		tFMail.setBounds(93, 138, 140, 20);
		frame.getContentPane().add(tFMail);
		tFMail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Birth DATE");
		lblNewLabel_3.setBounds(10, 166, 66, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		tFDate = new JTextField();
		tFDate.setBounds(93, 163, 139, 20);
		frame.getContentPane().add(tFDate);
		tFDate.setColumns(10);
		
		JButton btnCreate = new JButton("create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO fast fail and check validation
				String log = tFUser.getText();
				String name = tfName.getText();
				int date =Integer.valueOf(tFDate.getText());
				String email = tFMail.getText();
				String password ="";
				char[] cp = passwordField.getPassword();
				for (int i = 0; i < cp.length; i++) {
					password+= cp[i];
				}
				
				GuiFunction.dataModel = new ModelBuilder().name(name)
						.logname(log)
						.logpass(password)
						.email(email)
						.yearOfBirth(String.valueOf(date)).build();
				// TODO If not exist return and error.
				passwordmanager.model.CreateUser.writeOperation(GuiFunction.dataModel);
			}
		});
		btnCreate.setBounds(51, 191, 89, 23);
		frame.getContentPane().add(btnCreate);
	}
	
}
