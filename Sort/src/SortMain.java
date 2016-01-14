/**
 * This class is the main entry point for the
 * @author Adam
 * @verson 1
 */
public class SortMain {
	
	
	public static void main(String[] args) {
		//this is a list of how big an experiment size we want to run our sorts on
		//edit this list for desired accuracy
		int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
		//let user know this may take  while
		System.out.println("Please wait a minute while lists are created, sorted, and validated...");
		BenchmarkSorts bmSorts = new BenchmarkSorts();
		//do a 'warm-up' run to get the java machine settled for more accurate results
		int[] warmUp = {20000};
		bmSorts.benchMarkSort(warmUp);
		bmSorts.runSorts();;
		//set up our list sizes we want to use
		bmSorts.benchMarkSort(sizes);
		//run the sorts
		bmSorts.runSorts();
		//display the findings
		bmSorts.displayReports();
	}

}
