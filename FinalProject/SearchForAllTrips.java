import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

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

public class SortingArrivalTimes{ //***
	//need to remove invalid times such as 24:00:00 and 00:00:00
	static String timeInvalid = "24:00:00";
	static String timeInvalid2 = "00:00:00";
	static boolean valid = false;
	
	public static void main(String[] args) throws ParseException{
		arrivalTimesSorted();
	}
	public static void arrivalTimesSorted() {
		String line = "";
		List<String> output = new ArrayList<String>();
		
		//user input
		String userInput =JOptionPane.showInputDialog("Please enter the time which you would like to arrive as 'HH:MM:SS'");
		
		try {
			//make sure time is valid, show message if not valid.
			BufferedReader br = new BufferedReader(new FileReader(stop_times.txt)); //read in file
			while((line = br.readLine()) != null) {
				String[] stop = line.split(" ");
				String arrivalTime = stop[1];
				if(userInput.compareTo(timeInvalid) < 0 && userInput.compareTo(timeInvalid2) > 0) {
					valid = true;
					
					if(userInput.equals(arrivalTime)) {
						output.add("Trip ID: " + stop[0] + " " + "Arrival Time: " + arrivalTime + " " +
								   "Departure Time: " + stop[2] + " " + "Stop ID: " + stop[3] + " " + 
								   "Stop Sequence" + stop[4] + " " + "Stop Headsign: " + stop[5] + " " + "Pickup Type: " + stop[6] +
								     " " + "Drop-off Type: " + stop[7] + " " + "Shape Distance Traveleved" + stop[8] + "\n");
					}
					else {
						valid = false;
						JOptionPane.showMessageDialog(null, "You entered an invalid time");
						break;
					}
				}
			}
			
			
		}//exception catch
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		//if statement for when valid = true.
		if(valid == true) {
			Collections.sort(output);
			//show message and replace [] and ,
			JOptionPane.showMessageDialog(null, "This is a list of all trips that arrive at the time you entered: "
					+ "\n" + Arrays.toString(output.toArray()).replace("[","").replace("]", "").replace(", ", ""));
		}
	}
}













