import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;

import javax.swing.JOptionPane;

//Searching for a bus stop by full name or by the first few characters in the name, using a
//ternary search tree (TST), returning the full stop information for each stop matching the
//search criteria (which can be zero, one or more stops)
//In order for this to provide meaningful search functionality please move keywords flagstop, wb, nb,
//sb, eb from start of the names to the end of the names of the stops when reading the file into a TST
//(eg “WB HASTINGS ST FS HOLDOM AVE” becomes “HASTINGS ST FS HOLDOM AVE WB”)

public class SearchForBusStopTST {
	//need to provide meaningful search functionality, therefore delete the keywords, there are 5.
	//will need temporaryFile with keywords deleted.
	//then tst search. See example above how names of stops change.
	
	//the keywords are FLAGSTOP, WB, NB, SB AND EB.
	static final String removed1 = "FLAGSTOP";
	static final String removed2 = "WB";
	static final String removed3 = "NB";
	static final String removed4 = "SB";
	static final String removed5 = "EB";
	
	public static void main(String[]args) throws IOException, ParseException{
		remove(); //define below
	}
	
	//use stops.txt file
	//stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station (from file)
	public static void remove() {
		String tempStopsFile = "tempStopsFile.Txt";
		String stopsFile = "stops.txt";
		File originalFile = new File(stopsFile);
		File removedFile = new File(tempStopsFile);
		//stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station 
		//store to create new sentence without any keywords.
		String stopID = "";
		String stopCode = "";
		String stopName = "";
		String stopDesc = "";
		String stopLat = "";
		String stopLon = "";
		String zoneID = "";
		String stopUrl = "";
		String locationType = "";
		String parentStation = "";
		
		//import FileWriter, BufferedWriter, PrintWriter = a,b,c AND useDelimiter("[,\n]") new line and commas
		try {
			FileWriter a = new FileWriter(tempStopsFile, true);
			BufferedWriter b = new BufferedWriter(a);
			PrintWriter c = new PrintWriter(b);
			Scanner scanner = new Scanner(new File(stopsFile));
			scanner.useDelimiter("[,\n]");
			scanner.nextLine();
			
			
			while(scanner.hasNext()) {
				stopID = scanner.next();
				stopCode = scanner.next();
				stopName = scanner.next();
				stopDesc = scanner.next();
				stopLat = scanner.next();
				stopLon = scanner.next();
				zoneID = scanner.next();
				stopUrl = scanner.next();
				locationType = scanner.next();
				parentStation = scanner.next();
				
				String removedWords[] = stopName.split(" ");
				String allOtherWords = stopName.substring(stopName.indexOf(" "), stopName.length()).trim();
				String allOtherWords2 = stopName.substring(11, stopName.length()).trim();
				//need stopID and stopCode out(first and second words), then keyword is in stopName (third word).
				String ignoreStopID =removedWords[0];
				String ignoreStopCode = removedWords[1];
				
				if((stopName.contains(removed2) || stopName.contains(removed3) || stopName.contains(removed4) ||
						stopName.contains(removed5)) && !stopName.contains(removed1)) {
					stopName = (allOtherWords + " " + ignoreStopID);
					
					//PRINTWRITER = c
					c.print(stopID + "," + stopCode + "," + stopName + "," + stopDesc + "," + stopLat + "," + 
					           stopLon + "," + zoneID + "," + stopUrl + "," + locationType + "," + parentStation );
				}
				else if(stopName.contains(removed1)&& !stopName.contains(removed2) && !stopName.contains(removed3) &&
						!stopName.contains(removed4) && !stopName.contains(removed5)) {
					stopName = (allOtherWords + " " + ignoreStopID);
					
					c.print(stopID + "," + stopCode + "," + stopName + "," + stopDesc + "," + stopLat + "," + 
					           stopLon + "," + zoneID + "," + stopUrl + "," + locationType + "," + parentStation );
				}
				else if(stopName.contains(removed1) && !stopName.contains(removed2) && !stopName.contains(removed3) &&
						!stopName.contains(removed4) && !stopName.contains(removed5)) {
					stopName = (allOtherWords + " " + ignoreStopID);

					c.print(stopID + "," + stopCode + "," + stopName + "," + stopDesc + "," + stopLat + "," + 
					           stopLon + "," + zoneID + "," + stopUrl + "," + locationType + "," + parentStation );
				}
				else if(stopName.contains(removed1) && stopName.contains(removed2)) {
					stopName = (allOtherWords2 + " " + ignoreStopID + " " + ignoreStopCode);
					
					c.print(stopID + "," + stopCode + "," + stopName + "," + stopDesc + "," + stopLat + "," + 
					           stopLon + "," + zoneID + "," + stopUrl + "," + locationType + "," + parentStation );
					
				}
				else if(stopName.contains(removed1) && stopName.contains(removed3)) {
					stopName = (allOtherWords2 + " " + ignoreStopID + " " + ignoreStopCode);
					
					c.print(stopID + "," + stopCode + "," + stopName + "," + stopDesc + "," + stopLat + "," + 
					           stopLon + "," + zoneID + "," + stopUrl + "," + locationType + "," + parentStation );
				}
				else if(stopName.contains(removed1) && stopName.contains(removed4)) {
					stopName = (allOtherWords2 + " " + ignoreStopID + " " + ignoreStopCode);
					
					c.print(stopID + "," + stopCode + "," + stopName + "," + stopDesc + "," + stopLat + "," + 
					           stopLon + "," + zoneID + "," + stopUrl + "," + locationType + "," + parentStation );
				}
				else if(stopName.contains(removed1) && stopName.contains(removed5)) {
					stopName = (allOtherWords2 + " " + ignoreStopID + " " + ignoreStopCode);
					
					c.print(stopID + "," + stopCode + "," + stopName + "," + stopDesc + "," + stopLat + "," + 
					           stopLon + "," + zoneID + "," + stopUrl + "," + locationType + "," + parentStation );
				}
				
				else {
					c.print(stopID + "," + stopCode + "," + stopName + "," + stopDesc + "," + stopLat + "," + 
					           stopLon + "," + zoneID + "," + stopUrl + "," + locationType + "," + parentStation );
				}
			}
			scanner.close();
			c.flush();
			c.close();
			originalFile.delete();
			//need snapshot of file
			File snapDump = new File(stopsFile);
			removedFile.renameTo(snapDump);
				
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
//now onto TST - Searching for a bus stop by full name or by the first few characters in the name

public class TST<Value>{ //mmust be defined
	private tstNode<Value> root;
	private int N;
	
	public static void tstStops() {
		TST<String> search = new TST<String>();
		String line = "";
		File stopsFile = new File("stops.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(stopsFile));
			while((line = br.readLine()) != null) {
				String[] stops = line.split(",");
				String stopName = stops[2];
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
	
}
