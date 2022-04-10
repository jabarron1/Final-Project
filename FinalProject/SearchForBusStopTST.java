import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.ParseException;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.Scanner;

import javax.swing.JOptionPane;

//Searching for a bus stop by full name or by the first few characters in the name, using a
//ternary search tree (TST), returning the full stop information for each stop matching the
//search criteria (which can be zero, one or more stops)
//In order for this to provide meaningful search functionality please move keywords flagstop, wb, nb,
//sb, eb from start of the names to the end of the names of the stops when reading the file into a TST
//(eg “WB HASTINGS ST FS HOLDOM AVE” becomes “HASTINGS ST FS HOLDOM AVE WB”)

//public class SearchForBusStopTST {
	//need to provide meaningful search functionality, therefore delete the keywords, there are 5.
	//will need temporaryFile with keywords deleted.
	//then tst search. See example above how names of stops change.
	
	//the keywords are FLAGSTOP, WB, NB, SB AND EB.
	
//now onto TST - Searching for a bus stop by full name or by the first few characters in the name

public class SearchForBusStopTST<Value>{ 
	private tstNode<Value> root;
	private int N;
	
	
	
	public static void tstStops() {
		SearchForBusStopTST<String> search = new SearchForBusStopTST<String>();
		String line = "";
		File stopsFile = new File("stops.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(stopsFile)); //STOPSFILE
			while((line = br.readLine()) != null) {
				String[] stops = line.split(","); //error around here
				String stopName = stops[2]; //get name
				//
				String stopInfo = "Stop id: " + stops[0] + "," + " Stop Code: " + stops[1] + "," 
				            + " Stop Desc: " + stops[3] + "," + " Stop Lat: " + stops[4] + "," + 
						    " Stop Lon: " + stops[5] + "," + " Zone ID " + stops[6];
				search.put(stopName, stopInfo); //need private below ************
			}
			br.close();
		} //catch below
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		//now need to allow user input for searching for bus stop tst. joption pane
		
		String userInput = JOptionPane.showInputDialog("Please enter the name of the bus stop that you are searching for");
		String input = userInput.toUpperCase();
		
		if(search.contains(input)) { //need to define
			JOptionPane.showMessageDialog(null, "This is the stop name information for your search: " + "\n" 
		                        + search.get(input)); //define 
		}
		else {
			JOptionPane.showMessageDialog(null, "The bus stop you are searching for does not exist, please enter a valid bus stop name.");
		}
	}
	
	//now need to do the search by first few characters only
	public static void characterSearch() {
		SearchForBusStopTST<String> search2 = new SearchForBusStopTST<String>(); //search2
		String line ="";
        File stopsFile = new File("stops.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(stopsFile));//stops file
			while((line = br.readLine()) != null) {
				String[] stops = line.split(",");
				String stopName = stops[2];//changed from 2 //error around here
				String[] name = stopName.split(" ");
				String stopName2 = name[0];
				
				String stopInfo = "Stop id: " + stops[0] + "," + " Stop Code: " + stops[1] + "," 
			            + " Stop Desc: " + stops[3] + "," + " Stop Lat: " + stops[4] + "," + 
					    " Stop Lon: " + stops[5] + "," + " Zone ID " + stops[6];
				search2.put(stopName2, stopInfo); //search2
				
			}
			br.close();
	} //catch
		
	catch(FileNotFoundException e) {
		e.printStackTrace();
	}
	catch(IOException e) {
		e.printStackTrace();
	}
		//now user input again
		String userInput2 = JOptionPane.showInputDialog("Enter the start of the bus stop you would like to find");
		String input = userInput2.toUpperCase();
		
		String notValid = "";
		for(String search3 : search2.keysWithPrefix(input)) { //********search2
			notValid += search2+search2.get(search3)+ "\n"; ///search2******
			if(notValid == "") {
				JOptionPane.showMessageDialog(null, "This is not a valid stop. Please enter a valid bus stop.");
			}
			else {
				JOptionPane.showMessageDialog(null, "Here is the information on the bus stop you have searched for: " + "\n" + notValid);
			}
		}
}
	
	//go through above, see what needs to be defined ect.
	
	private static class tstNode<Value>{
		private char character;
		private tstNode<Value> left;
		private tstNode<Value> middle;
		private tstNode<Value> right;
		private Value val;
	}
	
	public SearchForBusStopTST(){
		
	}
	
	public int size() {
		return N;
	}
	
	public boolean contains(String key) {
		if(key==null)
			return false;
		return get(key) != null; //get
	}
	
	public Value get(String key) {
		if(key == null) {
			return null;
		}
		if(key.length() == 0) {
			return null;
		}
		tstNode<Value> a = get(root, key, 0); //get
		if(a==null)
			return null;
		return a.val;
	}
	
	private tstNode<Value> get(tstNode<Value> b, String key, int d){
		if(b==null) {
			return null;
		}
		char e = key.charAt(d);
		if(e<b.character)
			return get(b.left, key, d);
		else if (e>b.character)
			return get(b.right, key, d);
		else if(d<key.length()-1)
			return get(b.middle, key, d+1);
		else
			return b;
	}
	//onto put
	private tstNode<Value> put(tstNode<Value> a, String key, Value val, int d){
		char e = key.charAt(d);
		if (a == null) 
		{
			a = new tstNode<Value>();
			a.character = e;
		}
		if (e < a.character)              
			a.left  = put(a.left,  key, val, d);
		else if (e > a.character)               
			a.right = put(a.right, key, val, d);
		else if (d < key.length() - 1)  
			a.middle   = put(a.middle,   key, val, d+1);
		else                            
			a.val   = val;

		return a;
	}
	public void put(String key, Value val) {
		try {
			if(!contains(key))
				N++;
			root = put(root, key, val, 0);
		}//catch
		catch(NullPointerException e) {
			System.out.println("This threw an exception.");
		}
	}
	
	//keys with prefix , keys with prefix check above
	public Iterable<String> keysWithPrefix(String prefix){
		if(prefix == null) {
			throw new IllegalArgumentException("Null argument keysWithPrefix.");
		}
		Queue<String> queue = new LinkedList<String>();
		tstNode<Value> a = get(root, prefix, 0);
		if(a==null)
			return queue;
		if(a.val != null)
			queue.add(prefix);
		collecting(a.middle, new StringBuilder(prefix), queue);//collecting
		return queue;
	}
	
	private void collecting(tstNode<Value> a, StringBuilder prefix, Queue<String> queue) {
		if(a==null)
			return;
		collecting(a.left, prefix, queue);
		if(a.val != null)
			queue.add(prefix.toString()+ a.character);
		collecting(a.middle, prefix.append(a.character), queue);
		prefix.deleteCharAt(prefix.length()-1);
		collecting(a.right, prefix, queue);
	}
	
}
