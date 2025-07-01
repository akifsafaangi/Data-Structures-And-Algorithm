public class QuickSort extends SortAlgorithm {

	public QuickSort(int input_array[]) {
		super(input_array);
	}
	
    private int partition(int arr[], int start, int end){
        // Choosing the pivot
        int pivot = arr[end];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (start - 1);

        for (int j = start; j <= end - 1; j++) {

            ++comparison_counter;
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {

                // Increment index of smaller element
                i++;
                swap(i, j); // Swap current element with the element at i
            }
        }
        swap(i + 1, end);
        return (i + 1);
    }

    private void sort(int arr[], int start, int end){
        if (start < end) {
            int p = partition(arr, start, end);  //p is partitioning index  
            sort(arr, start, p - 1); // Sort elements before partition
            sort(arr, p + 1, end); // Sort elements after partition
        } 
    }

    @Override
    public void sort() {
    	sort(arr, 0, arr.length - 1);
    }

    @Override
    public void print() {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}
