package passwordmanager.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;

import passwordmanager.GetPass;
import passwordmanager.filecontroler.FileControl;
import passwordmanager.model.Consts;
import passwordmanager.model.LogIn;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Color;

public class Login {

	JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	Image see = null;
	Image hide = null;
	ImageIcon seeIcon = null , hideIcon = null;
	boolean isHide = false;
	String txthide = "unhide";
	boolean ckState = false;
	boolean authentication = false;
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
		textField.setBounds(95, 67, 113, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(22, 221, 275, 14);
		frmLogin.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("user");
		lblNewLabel.setBounds(22, 70, 46, 14);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(22, 101, 63, 14);
		frmLogin.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textField.getText();
				char[] cp = passwordField.getPassword();
				String pass = "";
				for (int i = 0; i < cp.length; i++) {
					pass+= cp[i];
				}
				authentication = GuiFunction.Verify(user,pass);
				if(!authentication)
				{
					lblNewLabel_2.setVisible(true);
					lblNewLabel_2.setText("Username or Password are incorrect!");
				}
				else
				{
					// check box save login
					String path = "\\bin\\";
					File info = new File(path + "inf.pm");
					String dpath  = Consts.path;
					int k1 = Consts.k1, k2 = Consts.k2;

					String data = GetPass.data(dpath,k1,k2);
					
//					if(ckState)
//					{
//						
//						data += "SaveLog:" +GuiFunction.userProfileData.get("LogName")+"\n";
//												
//						GuiFunction.userProfileData.put("SaveLog", "Y");
//						GuiFunction.writeProfile();
//					}
//					else
//					{
//						data += "SaveLog:" +"NULL"+"\n";
//						
//						GuiFunction.userProfileData.put("SaveLog", "N");
//						GuiFunction.writeProfile();
//					}
					
					FileControl.write(info, data);
					
					Main m = new Main();
					m.frmDashboard.setVisible(true);
					frmLogin.setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(42, 176, 89, 23);
		frmLogin.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(95, 101, 113, 20);
		frmLogin.getContentPane().add(passwordField);
		
		Image image = null;
		try {
			 see = ImageIO.read(getClass().getResource("images\\see.png"));
			 seeIcon = new ImageIcon(see); // load the image to a imageIcon
			 image = seeIcon.getImage(); // transform it 
			 image = GuiFunction.getScaledImage(image, 30, 30);
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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Save Login");
		chckbxNewCheckBox.setBounds(22, 140, 186, 23);
		frmLogin.getContentPane().add(chckbxNewCheckBox);
		
		chckbxNewCheckBox.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {                 
               
            	ckState = e.getStateChange() == 1 ?  true : false;    
            }    
         });    
		JButton btnNewButton_1 = new JButton("Sign up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUser cu = new CreateUser();
				cu.frame.setVisible(true);
				frmLogin.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(152, 176, 89, 23);
		frmLogin.getContentPane().add(btnNewButton_1);
		
		
		
	}
}
