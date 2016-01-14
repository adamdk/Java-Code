import java.text.DecimalFormat;
import java.util.Random;
import java.math.*;
/**
 * This class is used to run benchmarking of merge sort recursively and iteratively and 
 * display the comparative result table.
 * @author Adam Dkhili
 * @version 1
 *
 */
public class BenchmarkSorts {
	//Do 50 runs of each size list
	private int runs = 50;
	//These will be our 10 different sizes of random lists
	private static int[] sizes;
	//Two dimensional arrays which holds average and standard deviation for each set size
	//uses doubles because they will have decimal places and the math functions use doubles
	private static double[][] recursiveRuns;
	private static double[][] recursiveTimes;
	private static double[][] iterativeRuns;
	private static double[][] iterativeTimes;
	
	//initialize all of our lists with correct size passed in
	public void benchMarkSort(int[] sizeList){
		sizes = sizeList;
		recursiveRuns = new double[sizeList.length][2];
		recursiveTimes = new double[sizeList.length][2];
		iterativeRuns = new double[sizeList.length][2];
		iterativeTimes = new double[sizeList.length][2];
	}
	
	//carry out the sorts
	public void runSorts(){
		//random number generator
		Random randGen = new Random();
		//for each length of lists
		int index = 0;
		for (int n : sizes){
			//keep track of which run we are on, each loop we need a new counter so we initialize inside
			int run = 0;
			//do 50 different lists of given length
			int[] recursiveCountRuns = new int[runs];
			int[] iterativeCountRuns = new int [runs];
			long[] recursiveTimeRuns = new long[runs];
			long[] iterativeTimeRuns = new long[runs];
			
			//mergeSort object created
			MergeSort mSort = new MergeSort();
			while (run < runs){
				int[] randomList = new int[n];
				//fill the list with random numbers between 0 and 1000 inclusive
				for (int j = 0; j < n; j++){
					randomList[j] = randGen.nextInt(1000);
				}
				//run the sorting algorithms on the random list
				mSort.recursiveSort(randomList);
				//keep track of how the count for each run goes
				recursiveCountRuns[run] = mSort.getCount();
				recursiveTimeRuns[run] = mSort.getTime();
				//run the iterative sort
				mSort.iterativeSort(randomList);
				//keep track of how each run goes
				iterativeCountRuns[run] = mSort.getCount();
				iterativeTimeRuns[run] = mSort.getTime();
				run++;
			}
			//Fill in our lists for average and standard deviations for recursive cases
			//0 index is for averages, 1 index is for standard deviations
			recursiveRuns[index][0] = average(recursiveCountRuns);
			recursiveRuns[index][1] = standardDev(recursiveCountRuns, recursiveRuns[index][0]);
			recursiveTimes[index][0] = average(recursiveTimeRuns);
			recursiveTimes[index][1] = standardDev(recursiveTimeRuns, recursiveTimes[index][0]);
			//fill in our lists for average and standard deviation for iterative cases
			iterativeRuns[index][0] = average(iterativeCountRuns);
			iterativeRuns[index][1] = standardDev(iterativeCountRuns, iterativeRuns[index][0]);
			iterativeTimes[index][0] = average(iterativeTimeRuns);
			iterativeTimes[index][1] = standardDev(iterativeTimeRuns, iterativeTimes[index][0]);
			index++;
		}
	}
	/**
	 * Displays the results obtained in a table like format.
	 * @param none
	 * 
	 */
	public void displayReports(){
		//Print out our results for recursive runs and times for each size list
		int index = 0;
		//we only want accuracy to two decimal places
		DecimalFormat df = new DecimalFormat("#.##");
		//round up
		df.setRoundingMode(RoundingMode.CEILING);
		System.out.println("\nTable of output results:");
		System.out.println("--------------------------");
		System.out.println(" Data set |                             Iterative                                   |                                 Recursive                                 |");
		System.out.println("  Size n  |                                                                         |                                                                           |");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------|");
		System.out.format("%9s%19s%19s%19s%19s%19s%19s%19s%19s", "", "Average Critical |", "Standard|", "Average |", "Standard |"," Average Critical |", "Standard |", "Average |", "Standard |");
		System.out.println();
		System.out.format("%9s%19s%19s%19s%19s%19s%19s%19s%19s", "", "Operation Count |", "Deviation|", "Execution Time |", "Devation |", "Operation Count |", "Deviation |", "Execution Time |", "Deviation |");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------|");
		for(int size : sizes){
			System.out.println();
			System.out.format("%9d%19s%19s%19s%19s%19s%19s%19s%19s", size, df.format(iterativeRuns[index][0]), df.format(iterativeRuns[index][1]), df.format(iterativeTimes[index][0]), df.format(iterativeTimes[index][1]), df.format(recursiveRuns[index][0]), df.format(recursiveRuns[index][1]), df.format(recursiveTimes[index][0]), df.format(recursiveTimes[index][1]) + "/n");
			index++;
		}
	}
	
	//find the average value
	private double average(int[] counts){
		double temp = 0;
		for (double i : counts){
			temp += i;
		}
		return temp / counts.length;
	}
	private double average(long[] counts){
		double temp = 0;
		for (double i : counts){
			temp += i;
		}
		return temp / counts.length;
	}
	//finds standard deviation
	private double standardDev(int[] counts, double avg){
		double temp = 0;
		for(double i : counts){
			temp += Math.pow(i-avg, 2);
		}
		return Math.sqrt(temp / counts.length);
	}
	private double standardDev(long[] counts, double avg){
		double temp = 0;
		for(double i : counts){
			temp += Math.pow(i-avg, 2);
		}
		return Math.sqrt(temp / counts.length);
	}
}
