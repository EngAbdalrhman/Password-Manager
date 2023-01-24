package passwordmanager.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;

import passwordmanager.GetPass;
import passwordmanager.filecontroler.FileControl;
import passwordmanager.model.Consts;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ModifyPath {
	// path to save user files
	JFrame pathFrame;
	private JTextField textField;
	JFileChooser chooser;
	String choosertitle;
	Window w = new Window();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyPath window = new ModifyPath();
					window.pathFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModifyPath() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		pathFrame = new JFrame();
		pathFrame.setBounds(100, 100, 450, 300);
		pathFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pathFrame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(93, 63, 284, 20);
		pathFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("Path");
		lblNewLabel.setBounds(22, 66, 46, 14);
		pathFrame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = "\\bin\\";
				File info = new File(path + "inf.pm");
				String dpath  = Consts.path;
				int k1 = Consts.k1, k2 = Consts.k2;
				if(choosertitle != null)
				dpath = choosertitle; // TODO Check if vaild
				String data = GetPass.data(dpath,k1,k2);
				FileControl.write(info, data);
				// TODO RET
				
				w.frmPasswordManager.setVisible(true);
				pathFrame.setVisible(false);
				
			}
		});
		btnNewButton.setBounds(110, 152, 89, 23);
		pathFrame.getContentPane().add(btnNewButton);
		
	      //Icon icon = new ImageIcon("images\\file.gif");
	      Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("images\\file.gif"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      
	      
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setIcon(new ImageIcon(img));
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				fileChooser(chooser);
				
		
			    chooser.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(387, 62, 37, 23);
		pathFrame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				w.frmPasswordManager.setVisible(true);
				pathFrame.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(247, 152, 89, 23);
		pathFrame.getContentPane().add(btnNewButton_2);
	}
	public JFileChooser fileChooser(JFileChooser chooser) {            
	    chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle(choosertitle);
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

	    // disable the "All files" option.
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(pathFrame) == JFileChooser.APPROVE_OPTION) { 
	      choosertitle = chooser.getSelectedFile().toString();
	      textField.setText(choosertitle);
	     /* System.out.println("getCurrentDirectory(): " 
	         +  chooser.getCurrentDirectory());
	      System.out.println("getSelectedFile() : " 
	         +  choosertitle);*/
	      }
	    else {
	      System.out.println("No Selection ");
	      }
		return chooser;
	     }
}
