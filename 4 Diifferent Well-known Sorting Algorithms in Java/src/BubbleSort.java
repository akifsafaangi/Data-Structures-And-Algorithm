public class BubbleSort extends SortAlgorithm {

	public BubbleSort(int input_array[]) {
		super(input_array);
	}
	
    @Override
    public void sort() {
        int n = arr.length; // Get the length of the array
        boolean swapped; // Flag to check if any swap occurred
        for (int i = 0; i < n - 1; i++) { // Outer loop to iterate through the array
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) { // Inner loop to compare adjacent elements
                ++comparison_counter;
                if (arr[j] > arr[j + 1]) { // If current element is greater than next
                    // Swap arr[j] and arr[j+1]
                    swap(j, j + 1);
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }
    
    @Override
    public void print() {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}
