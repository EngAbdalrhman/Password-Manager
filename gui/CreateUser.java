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

public class CreateUser {

	private JFrame frame;
	private JTextField textField;
	Image see = null;
	Image hide = null;
	ImageIcon seeIcon = null , hideIcon = null;
	boolean isHide = false;
	private JPasswordField passwordField;
	String txthide = "unhide";
	
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
		frame.setBounds(100, 100, 314, 252);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(147, 44, 86, 20);
		frame.getContentPane().add(textField);
		
		JLabel label = new JLabel("user");
		label.setBounds(47, 47, 46, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("password");
		label_1.setBounds(47, 78, 46, 14);
		frame.getContentPane().add(label_1);
		
		JButton btnCreate = new JButton("create");
		btnCreate.setBounds(101, 132, 89, 23);
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
			 image = GuiFunction.getScaledImage(image, 35, 35);
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
		passwordField.setBounds(147, 75, 86, 20);
		frame.getContentPane().add(passwordField);
	}
	
}
