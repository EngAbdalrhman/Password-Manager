package passwordmanager.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class About {

	JFrame about;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About window = new About();
					window.about.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public About() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		about = new JFrame();
		about.setBounds(100, 100, 450, 300);
		about.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
