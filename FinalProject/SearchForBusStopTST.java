import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;

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
			}
		}
		
		
	}
	

	
}
