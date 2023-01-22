package passwordmanager.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	Image see = null;
	Image hide = null;
	ImageIcon seeIcon = null , hideIcon = null;
	boolean isHide = false;
	String txthide = "unhide";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 323, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(122, 67, 86, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("user");
		lblNewLabel.setBounds(22, 70, 46, 14);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(22, 101, 46, 14);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Log");
		btnNewButton.setBounds(76, 155, 89, 23);
		frmLogin.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(122, 101, 86, 20);
		frmLogin.getContentPane().add(passwordField);
		
		Image image = null;
		try {
			 see = ImageIO.read(getClass().getResource("images\\see.png"));
			 seeIcon = new ImageIcon(see); // load the image to a imageIcon
			 image = seeIcon.getImage(); // transform it 
			 image = GuiFunction.getScaledImage(image, 35, 35);
			 seeIcon = new ImageIcon(image);
			 hide = ImageIO.read(getClass().getResource("images\\hide.png"));
			 hideIcon = new ImageIcon(hide); // load the image to a imageIcon
			 image = hideIcon.getImage(); // transform it 
			 image = GuiFunction.getScaledImage(image, 35, 35);
			 hideIcon = new ImageIcon(image);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton button = new JButton();
		button.setToolTipText(txthide);
		button.setIcon(seeIcon);// next state
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isHide) 
				{
					//TODO show password
					button.setIcon(hideIcon);
					isHide = true;
					//passwordField.setText(); 
					//System.out.println(passwordField.getPassword());
					passwordField.setEchoChar('\u0000'); // set to zero to disable
					txthide = "hide";
					button.setToolTipText(txthide);
				}
				else 
				{
					//TODO hide password
					button.setIcon(seeIcon);
					isHide = false;
					passwordField.setEchoChar('•');
					txthide = "unhide";
					button.setToolTipText(txthide);
				}
			}
		});
		button.setBounds(233, 97, 38, 23);
		frmLogin.getContentPane().add(button);
	}
}
