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
		double minTransferTime; //for transfer type 2
		double cost = 1; //1 from stop times, 2 if from transfer
		int transferNeeded; //
		String currentLineStopTimes;
		
		//stop times file
		File stopTimes = new File(stop_timesTxt); //1
		Scanner stopTimesAndTransferFile = new Scanner(stopTimes); //2
		Scanner lineStopTimes = null; //3
		stopTimesAndTransferFile.nextLine(); //2
		
		while(stopTimesAndTransferFile.hasNextLine()) { //2
			currentLineStopTimes = stopTimesAndTransferFile.nextLine(); //str
			lineStopTimes = new Scanner(currentLineStopTimes); //3
			lineStopTimes.useDelimiter(","); //3
			
			lastTripID = currentTripID;
			for(int i = 1; i < 3; i++) {
				lineStopTimes.next(); //3
			}
			startDestination = finalDestination;
			finalDestination = lineStopTimes.nextInt(); //3
			
			if(lastTripID == currentTripID) {
				adjMatrix[startDestination][finalDestination] = cost; //cost = 1 when stop times file
			}
			
			lineStopTimes.close(); //3
			
		}
		stopTimesAndTransferFile.close(); //2
		
		//now onto transfers file, therefore indirect service (ie transfer needed) with a cost of 2
		
		File transfers = new File(transfersTxt);
		stopTimesAndTransferFile = new Scanner(transfers);
		stopTimesAndTransferFile.nextLine();
		
		while(stopTimesAndTransferFile.hasNextLine()) {
			currentLineStopTimes = stopTimesAndTransferFile.nextLine(); 
			lineStopTimes = new Scanner(currentLineStopTimes); 
			lineStopTimes.useDelimiter(",");
			
			startDestination = lineStopTimes.nextInt();
			finalDestination = lineStopTimes.nextInt();
			transferNeeded = lineStopTimes.nextInt();
			
			if(transferNeeded == 0) { //with immediate transfer possible (type 0) cost is 2
				adjMatrix[startDestination][finalDestination] = 2;
			}
			else if(transferNeeded == 2) { //for type 2, cost is the minimum transfer time divided by 100.
				minTransferTime = lineStopTimes.nextDouble();
				adjMatrix[startDestination][finalDestination] = (minTransferTime / 100);
			}
			lineStopTimes.close();
		}
		stopTimesAndTransferFile.close();
		
	}
	
	//define ShortestPath
	public ShortestPath(String stop_timesTxt, String transfersTxt) throws FileNotFoundException{
		this.stop_timesTxt = stop_timesTxt;
		this.transfersTxt = transfersTxt;
		createGraph();
	}
	
	//need to find the shortest path, shortestPath. use startDestination and finalDestination
	public String shortestPath(int startDestination, int finalDestination) {
		//nodes, distanceTo, edgeTo, current, noStopping, location, shortDistance, distancesLength
		int edgeTo[]= new int[10000];
		int current[] = new int[10000];
		double distanceTo[] = new double[10000];
		current[startDestination]=1;
		distanceTo[startDestination] = 0;
		int currentLocation = startDestination;
		int noStopping = 0;
		double shortDistance = Math.pow(10000, 10000);
		
		double firstNode = startDestination;
		double secondNode = finalDestination;
		int distancesLength = distanceTo.length;
		String route = "";
		
		for(int i = 0; i<distancesLength; i++) {
			if(i != startDestination) {
				distanceTo[i] = Double.POSITIVE_INFINITY; //constant holding infinity
			}
		}
		if(finalDestination == startDestination) {
			return "" + adjMatrix[startDestination][finalDestination]; //check this out
		}
		
		while(noStopping < distancesLength) {
			for(int i = 0; i<distancesLength; i++) {
				if(current[i] != 1 && shortDistance > distanceTo[i]) {
					currentLocation = i;
					shortDistance = distanceTo[i];
				}
			}
			noStopping++;
			
			for(int i = 0; i < adjMatrix[currentLocation].length; i++) {
				if(current[i] == 0) {
					relaxingEdge(currentLocation, i, distanceTo, edgeTo); //define below
				}
			}
			current[currentLocation] = 1;
		}
		
		if(distanceTo[finalDestination] == Double.POSITIVE_INFINITY) {
			return "There is no route between these stops.";
		}
		
		while(secondNode != firstNode) {
			route = edgeTo[(int) secondNode] + route;
			secondNode = edgeTo[(int) secondNode];
		}
		route = route + "," +finalDestination;
		return distanceTo[finalDestination] + " between stops " + route;
				
	}
	
	//relaxingEdge
	private void relaxingEdge(int startDestination, int finalDestination, double[] distanceTo, int[] edgeTo) {
		if(distanceTo[finalDestination] > distanceTo[startDestination] + adjMatrix[startDestination][finalDestination]) {
			distanceTo[finalDestination] = distanceTo[startDestination] + adjMatrix[startDestination][finalDestination];
			edgeTo[finalDestination] = startDestination;
		}
	}


	
}
