import java.util.LinkedList;

/*
 * @author http://www.softwareandfinance.com/Java/MergeSort_Recursive.html
 * @author http://www.softwareandfinance.com/Java/MergeSort_Iterative.html
 * @author Adam Dkhili
 */
public class MergeSort implements SortInterface {
//local variables to keep track of run count and elapsed time
private static int count;
private static long time;
	//Recursive merge sort
	public void recursiveSort(int[] list) throws UnsortedException{
		//reset our tracking variables
		count = 0;
		time = 0;
		//take the start time
		long start = System.nanoTime();
		//run the merge
		mergeSort_Recursive(list, 0, list.length -1);
		//take the stop time
		long stop = System.nanoTime();
		//make sure the list is actually sorted
		validate(list);
		//calculate the elapsed time
		time = stop - start;
	}
	
	//Using iteration
	public void iterativeSort(int[] list) throws UnsortedException{
		//reset our tracking variables
		count = 0;
		time = 0;
		//take the start time
		long start = System.nanoTime();
		//http://www.softwareandfinance.com/Java/MergeSort_Iterative.html
		//run the merge
		mergeSort_Iterative(list, 0, list.length-1);
		//take the stop time
		long stop = System.nanoTime();
		//make sure the list is really sorted
		validate(list);
		//calculate the elapsed time
		time = stop - start;
	}
	
	//what is the count of operations
	public int getCount(){
		return count;
	}
	
	//get the time it took
	public long getTime(){
		return time;
	}
	
	static private void mergeSort_Recursive(int [] numbers, int left, int right)
    {
      int mid;
      if (right > left)
      {
        mid = (right + left) / 2;
        mergeSort_Recursive(numbers, left, mid);
        mergeSort_Recursive(numbers, (mid + 1), right);  
        DoMerge(numbers, left, (mid+1), right);
      }
      //count++;
    }

	static private void DoMerge(int [] numbers, int left, int mid, int right)
    {
      int [] temp = new int[numbers.length];
      int i, left_end, num_elements, tmp_pos;
      left_end = (mid - 1);
      tmp_pos = left;
      num_elements = (right - left + 1);
      while ((left <= left_end) && (mid <= right))
      {
          if (numbers[left] <= numbers[mid])
              temp[tmp_pos++] = numbers[left++];
          else
              temp[tmp_pos++] = numbers[mid++];
          count++;
      }
      while (left <= left_end)
          temp[tmp_pos++] = numbers[left++];
      	count++;
      while (mid <= right)
          temp[tmp_pos++] = numbers[mid++];
      	count++;
      for (i = 0; i < num_elements; i++)
      {
          numbers[right] = temp[right];
          right--;
      }
  }
	
	static private void mergeSort_Iterative(int [] numbers, int left, int right)
    {
        int mid;
        if (right <= left){return;}
        
        LinkedList<MergePosInfo> list1 = new LinkedList<MergePosInfo>();
        LinkedList<MergePosInfo> list2 = new LinkedList<MergePosInfo>();

        MergePosInfo info = new MergePosInfo();
        info.left = left;
        info.right = right;
        info.mid = -1;
        list1.add(info);
        while(true)
        {
            if(list1.size() == 0){ break;}
            
            left = list1.get(0).left;
            right = list1.get(0).right;
            list1.remove(0);
            mid = (right + left) / 2;

            if(left < right)
            {
                MergePosInfo info2 = new MergePosInfo();
                info2.left = left;
                info2.right = right;
                info2.mid = mid + 1;
                list2.add(info2);
                info.left = left;
                info.right = mid;
                list1.add(info);
                info.left = mid + 1;
                info.right = right;
                list1.add(info);
            }
        } 
        for (int i = 0; i < list2.size(); i++)
        {
        	DoItMerge(numbers, list2.get(i).left, list2.get(i).mid, list2.get(i).right);
        	//count++;
        }       
    }
	
	static class MergePosInfo
    {
        public int left;
        public int mid;
        public int right;
    }
	
	//Merge function for iterative method
	static private void DoItMerge(int [] numbers, int left, int mid, int right)
    {
		int [] temp = new int[numbers.length];
		int i, left_end, num_elements, tmp_pos;
		left_end = (mid - 1);
		tmp_pos = left;
		num_elements = (right - left + 1);
		while ((left <= left_end) && (mid <= right))
		{
			if (numbers[left] <= numbers[mid])
	        	{temp[tmp_pos++] = numbers[left++];}
			else
				{temp[tmp_pos++] = numbers[mid++];}
			count++;
		}
		while (left <= left_end)
			{temp[tmp_pos++] = numbers[left++];
			count++;}
		while (mid <= right)
			{temp[tmp_pos++] = numbers[mid++];
			count++;}
		for (i = 0; i < num_elements; i++)
		{
			numbers[right] = temp[right];
			right--;
			//count++;
		}
    }

	//Make sure everything is actually sorted, throw an exception if it is not
	private void validate(int[] list) throws UnsortedException{
		for (int i =0; i < list.length-2; i++){
			 if(list[i] > list[i+1]){
				 throw new UnsortedException("The list was not properly sorted");
			 }
		 }
	 }
}
