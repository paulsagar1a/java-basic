package sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = {3,5,1,6,2,8,9,9};
		int l=0;
		int h=arr.length-1;
		quickSort(arr,l,h);
		
		for(int i=0; i<=h;i++) {
			System.out.println(arr[i]);
		}
	}

	private static void quickSort(int[] arr, int l, int h) {
		if(l<h) {
			int p = partition(arr, l,h);
			quickSort(arr, l,  p-1);
			quickSort(arr, p+1, h);
		}
	}

	private static int partition(int[] arr, int i, int j) {
		int pivot = arr[i];
		int k = i;
		
		while(i<j) {
			while(i<j && arr[i]<=pivot) {
				i++;
			}
			while(arr[j]>pivot) {
				j--;
			}
			
			if(i<j) {
				int t1 = arr[i];
				arr[i] = arr[j];
				arr[j] = t1;
			}
		}
		int t2 = arr[k];
		arr[k] = arr[j];
		arr[j] = t2;
		return j;
	}

}
