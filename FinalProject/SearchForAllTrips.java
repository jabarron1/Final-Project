import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;

//Searching for all trips with a given arrival time, returning full details of all trips matching the
//criteria (zero, one or more), sorted by trip id
//Arrival time should be provided by the user as hh:mm:ss. When reading in stop_times.txt file you
//will need to remove all invalid times, e.g., there are times in the file that start at 27/28 hours, so are
//clearly invalid. Maximum time allowed is 23:59:59. ***

public class SearchForAllTrips {
	public static void main(String[]args) throws ParseException, IOException{
		remove();
	}
	
	public static void remove() {
		String tempStopsFile = "tempStopsFile.txt";
		//max time allowed is 23:59:59
		String timeInvalid = "24:00:00";
		String validTime = "10:00:00";
		String stopsFile = "stops.txt";
		File originalFile = new File(stopsFile);
		File removedFile = new File(tempStopsFile);
		
		//trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_traveled
		//create strings for all
		String tripID = "";
		String arrivalTime = "";
		String departureTime = "";
		String stopID = "";
		String stopSequence = "";
		String stopHeadSign = "";
		String pickupType = "";
		String dropoffType = "";
		String shapeDistTravelled = "";
		
		try { //same as SearchForBusStopTST
			FileWriter a = new FileWriter(tempStopsFile, true);
			BufferedWriter b = new BufferedWriter(a);
			PrintWriter c = new PrintWriter(b);
			Scanner scanner = new Scanner(new File(stopsFile));
			scanner.useDelimiter("[,\n]");
			scanner.nextLine();
			
			
			while(scanner.hasNext()) {
				tripID = scanner.next();
				arrivalTime = scanner.next();
				departureTime = scanner.next();
				stopID = scanner.next();
				stopSequence = scanner.next();
				stopHeadSign = scanner.next();
				pickupType = scanner.next();
				dropoffType = scanner.next();
				shapeDistTravelled = scanner.next();
				
				//if statement
				//arrival time is less than timeInvalid
				if(arrivalTime.compareTo(validTime) < 0) {
					c.println(tripID + "" + arrivalTime + "" + departureTime + "" + stopID + "" + stopSequence + "" +
				            stopHeadSign + "" + pickupType + "" + dropoffType + "" + shapeDistTravelled);
				}
				else {
					c.println(tripID + "" + arrivalTime + "" + departureTime + "" + stopID + "" + stopSequence + "" +
				            stopHeadSign + "" + pickupType + "" + dropoffType + "" + shapeDistTravelled);
				}
			}
			scanner.close();
			c.flush();
			c.close();
			originalFile.delete();
			//need snapshot of file
			File snapDump = new File(stopsFile);
			removedFile.renameTo(snapDump);
			
		}//catch
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

//now need to be able to take user inputted arrival time sorted by trip id, removing invalid times.
