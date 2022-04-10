import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;

public class invalidTimesClass {
	public static void main(String[]args) throws ParseException, IOException{
		remove();
	}
	
	public static void remove() {
		String tempStopsFile = "tempStopsFile.txt";
		//max time allowed is 23:59:59
		String timeInvalid = "24:00:00";
		String validTime = "10:00:00";
		String stopsFile = "stop_times.txt"; ///changing
		File originalFile = new File(stopsFile); //changed from stopsFile
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
			Scanner scanner = new Scanner(new File("stop_times.txt"));
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
				if(arrivalTime.compareTo(timeInvalid) < 0) {
					if(arrivalTime.compareTo(validTime)<0) {
					c.println(tripID + "" + arrivalTime + "" + departureTime + "" + stopID + "" + stopSequence + "" +
				            stopHeadSign + "" + pickupType + "" + dropoffType + "" + shapeDistTravelled);
				}
				else {
					c.println(tripID + "" + arrivalTime + "" + departureTime + "" + stopID + "" + stopSequence + "" +
				            stopHeadSign + "" + pickupType + "" + dropoffType + "" + shapeDistTravelled);
				}
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

