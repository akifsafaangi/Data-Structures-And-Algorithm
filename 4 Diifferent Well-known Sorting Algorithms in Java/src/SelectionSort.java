public class SelectionSort extends SortAlgorithm {

	public SelectionSort(int input_array[]) {
		super(input_array);
	}

    @Override
    public void sort() {
        int n = arr.length; // Get the length of the array
        for (int i = 0; i < n-1; i++) // Outer loop to iterate through the array
        {
            int min = i; // Assume the first element is the smallest
            for (int j = i+1; j < n; j++) { // Inner loop to find the smallest element
                ++comparison_counter;
                if (arr[j] < arr[min]) { // If current element is smaller than the smallest element
                    min = j; // Update min to the index of the smallest element
                }   
            }
            swap(min, i); // Swap min index with current index
        }
    }

    @Override
    public void print() {
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}
