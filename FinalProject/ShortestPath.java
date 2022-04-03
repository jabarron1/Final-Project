import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Finding shortest paths between 2 bus stops (as input by the user), returning the list of stops
//en route as well as the associated “cost”.
//Stops are listed in stops.txt and connections (edges) between them come from stop_times.txt and
//transfers.txt files. All lines in transfers.txt are edges (directed), while in stop_times.txt an edge
//should be added only between 2 consecutive stops with the same trip_id.
//eg first 3 entries in stop_times.txt are
//9017927, 5:25:00, 5:25:00,646,1,,0,0,
//9017927, 5:25:50, 5:25:50,378,2,,0,0,0.3300
//9017927, 5:26:28, 5:26:28,379,3,,0,0,0.5780
//This should add a directed edge from 646 to 378, and a directed edge from 378 to 379 (as they’re on
//the same trip id 9017927).
//Cost associated with edges should be as follows: 1 if it comes from stop_times.txt, 2 if it comes from
//transfers.txt with transfer type 0 (which is immediate transfer possible), and for transfer type 2 the
//cost is the minimum transfer time divided by 100.

//adjacency matrix representation of a graph, undirected/directed. This will create a graph by 
//parsing through the data files and reading it into a graph.


public class ShortestPath {
	//input file names
	private String stop_timesTxt; 
	private String transfersTxt;
	//adjacency matrix for graph
	private double adjMatrix[][] = new double [10000][10000]; //dimension expressions needed, will start with 10,000
	
	//want to print the shortest path graph, need a public main, then publicly define the method , then private create graph
	//want the main to just return the shortest path
	public static void main(String[] args) throws FileNotFoundException{
		
		showShortestPath(); //define this method
	}
	
	public static void showShortestPath() throws FileNotFoundException{
		ShortestPath map = new ShortestPath("stop_timesTxt", "transfersTxt"); //define this
		
		try
		
		{
			map.createGraph(); //need to define this
		}
		//catch (FileNotFoundException e) 
		//{
			//e.printStackTrace();
		//}
		
		//take user input
		Scanner userInput = new Scanner (System.in);
		System.out.print("Please enter starting bus stop location.");
		int startPlace = userInput.nextInt();
		
		System.out.print("Please enter your final destinations bus stop.");
		int finishPlace = userInput.nextInt();
		
		//can then print shortest distance between the two user inputted locations, first need to create this.
		System.out.println("The shortest distance from " + startPlace + " to " + finishPlace + " is " + map.shortestPath(startPlace, finishPlace));
		
		userInput.close();
			
		
	}
	
	//private createGraph which is the matrix
	private void createGraph() throws FileNotFoundException{
		for(int i = 0; i < 10000; i++) {
			for(int j = 0; j < 10000; j++) {
				if(i != j) { //two different locations
					adjMatrix[i][j] = Math.pow(10000,10000); //need to recheck this
				}
				else { //0 as bus doesnt have to go anywhere
					adjMatrix[i][j] = 0;
				}
			}
		}
		
		//initialise a startDestination, finalDestination, lastTripID, currentTripID, shortestTime, cost, direct, line
		int startDestination = 0;
		int finalDestination = 0;
		int lastTripID = 0;
		int currentTripID = 0;
		double shortestTime;
		double cost = 1; //1 from stop times, 2 if from transfer
		int transferNeeded; //
		String currentLineStopTimes;
		
		//stop times file
		File stopTimes = new File(stop_timesTxt);
		Scanner stopTimesFile = new Scanner(stopTimes);
		Scanner lineStopTimes = null;
		stopTimesFile.nextLine();
		
		while(stopTimesFile.hasNextLine()) {
			currentLineStopTimes = stopTimesFile.nextLine();
			lineStopTimes = new Scanner(currentLineStopTimes);
			lineStopTimes.useDelimiter(",");
			
			lastTripID = currentTripID;
			for(int i = 1; i < 3; i++) {
				lineStopTimes.next();
			}
			startDestination = finalDestination;
			finalDestination = lineStopTimes.nextInt();
			
			
		}
		
		
	}
	
	//define ShortestPath
	public ShortestPath(String stop_timesTxt, String transfersTxt) throws FileNotFoundException{
		
	}
	
	//need to find the shortest path, shortestPath.
	public String shortestPath() {
		
	}
	
	

	
}
