import java.util.Random;
/*
time complexity: always O(nlogn)
space complexity: O(logn)
*/
public class quicksort_randomized{
	accessory_hw2 acc = new accessory_hw2();

	public int get_pivot(int left, int right){
		Random rand = new Random();
		int pivot = rand.nextInt(right-left+1)+left;
		return pivot;
	}
	public int partition(int[] A, int left, int right){
		int wall = left-1;
		int pivot = get_pivot(left,right);
		acc.exchange(A,pivot,right);	
		int x = A[right];
		int cur = left;
		for (cur=left;cur<right;cur++) {
			if (A[cur]<=x) {
				wall += 1;
				acc.exchange(A,cur,wall);
			}
		}
		acc.exchange(A,right,wall+1);
		return wall+1;
	}

	public void Quicksort(int[] A, int left, int right){
		if (left<right) {
			int wall = partition(A,left,right);
			Quicksort(A,left,wall-1);
			Quicksort(A,wall+1,right);
		}
	}
}
