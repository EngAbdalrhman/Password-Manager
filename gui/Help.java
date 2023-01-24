package passwordmanager.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Help {

	JFrame hframe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help window = new Help();
					window.hframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Help() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		hframe = new JFrame();
		hframe.setBounds(100, 100, 450, 300);
		hframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
