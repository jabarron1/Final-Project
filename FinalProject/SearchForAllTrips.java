import java.io.File;
import java.io.IOException;
import java.text.ParseException;

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
		
		
	}
	
}
