//Provide front interface enabling selection between the above features or an option to exit
//the programme, and enabling required user input. It does not matter if this is command-line
//or graphical UI, as long as functionality/error checking is provided.
//You are required to provide error checking and show appropriate messages in the case of erroneous
//inputs – eg bus stop doesn’t exist, wrong format for time for bus stop (eg letters instead of
//numbers), no route possible etc.

//need to choose between command line or graphical UI.
//GUIs offer better multitasking and control, more user friendly.
import java.awt.EventQueue;

import javax.swing.JFrame;

public class FrontInterfaceFinalProject {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontInterfaceFinalProject window = new FrontInterfaceFinalProject();
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
	public FrontInterfaceFinalProject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
