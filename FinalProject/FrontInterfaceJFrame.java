//Provide front interface enabling selection between the above features or an option to exit
//the programme, and enabling required user input. It does not matter if this is command-line
//or graphical UI, as long as functionality/error checking is provided.
//You are required to provide error checking and show appropriate messages in the case of erroneous
//inputs – eg bus stop doesn’t exist, wrong format for time for bus stop (eg letters instead of
//numbers), no route possible etc.

//need to choose between command line or graphical UI.
//GUIs offer better multitasking and control, more user friendly. JFrame is best option through windowbuilder.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
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
		setBounds(50, 100, 950, 700);//100, 100, 450, 300
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
        contentPane.setBackground(Color.YELLOW);
		
		//(A,B,C,D) A:HOW FAR LEFT/RIGHT, B:UP/DOWN, C:LENGTH OF TEXTBOX, D: Width of textbox
		//JLabel used to display a short string 
		JLabel messageToUser = new JLabel("Please choose your desired option:");
		messageToUser .setBounds(580, 100, 450, 200); //top centre
		contentPane.add(messageToUser );
		
		JLabel messageToUser2 = new JLabel("Please set this to fullscreen :)");
		messageToUser2 .setBounds(50, 200, 450, 200); //top centre
		contentPane.add(messageToUser2 );
		
		//now add buttons
		JButton shortPathButton = new JButton("Button for shortest path between two bus stops, this may take upto a minute.");//change name later
		shortPathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //takes so long to get to console
					
				try {
					ShortestPath.showShortestPath();
				} 
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
					
					
			}

		});
		//positioning explained:
		//(A,B,C,D) A:HOW FAR LEFT/RIGHT, B:UP/DOWN, C:LENGTH OF TEXTBOX, D: Width of textbox
		shortPathButton.setBounds(390, 250, 600, 80); //need to figure out positioning
		contentPane.add(shortPathButton);
		
		//create exit button
		
		JButton exitButton = new JButton("Click to Exit");
		exitButton.setForeground(new Color(200, 0, 0));
		exitButton.setFont(new Font("Red Exit", Font.PLAIN, 10));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		exitButton.setBounds(645, 560, 100, 40);//need to figure out positioning
		contentPane.add(exitButton);
		
		//onto trips w arrival time 
		JButton TSTButton = new JButton("Search for Bus Stop by full name or by the first few characters in the name");
		TSTButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = Integer.parseInt(JOptionPane.showInputDialog(null, "Do you want to search by first few characters of the stop name or full stop name? \n Enter 0 for first few characters "
						+ "or 1 for full stop name"));

				if(choice == 0)
				{
					TST.characterSearch();
				}
				else if (choice == 1)
					TST.tstStops();

				else {
					JOptionPane.showMessageDialog(null, "Not a valid option");

				}
			}
		});
		TSTButton.setBounds(393, 365, 600, 50); //positioned
		contentPane.add(TSTButton);
		
		
		
		JButton arrivalTimesButton = new JButton("Searching for all trips with a given arrival time");
		arrivalTimesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SortingArrivalTimes.arrivalTimesSorted(); //SortingArrivalTimes.arrivalTimesSorted();
			}
		});
		//(A,B,C,D) A:HOW FAR LEFT/RIGHT, B:UP/DOWN, C:LENGTH OF TEXTBOX, D: Width of textbox
		arrivalTimesButton.setBounds(393, 465, 600, 50);
		contentPane.add(arrivalTimesButton);
		
		//that should be all necessary buttons, next need to put them in correct positions.
		
		//adding another button that does nothing as arrivalTimesButton is taking up the whole jframe
		JButton arrivalTimesButton2 = new JButton("");
		arrivalTimesButton2.setBounds(10, 10, 10, 10);
		contentPane.add(arrivalTimesButton2);
	}
	

}
