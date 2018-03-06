/*
time complexity: O(nlogn),except for the worst case(sorted)
space complexity: O(logn)
*/
public class quicksort_deterministic{
	accessory_hw2 acc = new accessory_hw2();

	private int partition(int[] A, int left, int right){
		int wall = left-1;	
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