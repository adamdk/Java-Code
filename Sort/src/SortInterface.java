/*
 * Sort interface for using recursive or iterative merge sorting on lists of integers.
 */
public interface SortInterface {
	/*
	 * This method will take in a list of integers and sort them from least to greatest recursively
	 * @param int[]
	 */
	void recursiveSort(int[] list) throws UnsortedException;
	/*
	 * This method will take in a list of integers and sort them from least to greatest iteratively
	 */
	void iterativeSort(int[] list) throws UnsortedException;
	/*
	 * Returns the count of critical operations
	 * @return int
	 */
	int getCount();
	
	long getTime();
}
