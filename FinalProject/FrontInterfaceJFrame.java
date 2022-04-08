//Provide front interface enabling selection between the above features or an option to exit
//the programme, and enabling required user input. It does not matter if this is command-line
//or graphical UI, as long as functionality/error checking is provided.
//You are required to provide error checking and show appropriate messages in the case of erroneous
//inputs – eg bus stop doesn’t exist, wrong format for time for bus stop (eg letters instead of
//numbers), no route possible etc.

//need to choose between command line or graphical UI.
//GUIs offer better multitasking and control, more user friendly. JFrame is best option through windowbuilder.

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FrontInterfaceJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontInterfaceJFrame frame = new FrontInterfaceJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrontInterfaceJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		//now add buttons
	}

}
