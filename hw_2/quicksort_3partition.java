import java.util.Arrays;
public class quicksort_3partition{
	accessory_hw2 acc = new accessory_hw2();
	quicksort_randomized QR = new quicksort_randomized();

	/*
	input: Array(A) with the start(left) and end(end)
	output: the indexes where the middle part starts and ends
	basic function example: given A like [6,5,-3,6,9,4,8] if the random pivot is 6
							then reorganize it to [5,-3,4,6,6,9,8]
							output index array: [3,4]
	logic:
		index = get_pivot(A);
		exchange(A,index,end);
		cur = left, wall_left = left-1, wall_right = right
		while cur < wall_right:
  			if a[cur] < a[right], swap a[cur++,++wall_left];
  			else if a[cur] == a[right], swap a[cur,--wall_right];
  			else cur++
  		swap (larger_part,pivots_part)
		end
	*/
	public int[] partition(int[] A, int left, int right){
		int wall_left = left-1;
		int wall_right = right;
		int pivot = QR.get_pivot(left,right);
		acc.exchange(A,pivot,right);	
		int x = A[right];
		int cur = left;
		while (cur<wall_right) {
			if (A[cur]<x) {
				acc.exchange(A,cur++,++wall_left);
			}else if (A[cur] == x) {
				acc.exchange(A,cur,--wall_right);
			}else{
				cur++;
			}
		}
		acc.swap_consective(A,wall_left+1,cur,wall_right,right);
		return new int[] {wall_left+1,cur};
	}

	public void Quicksort(int[] A, int left, int right){
		if (left<right) {
			int[] result = partition(A,left,right);
			int wall_left = result[0];
			int wall_right = result[1];
			Quicksort(A,left,wall_left-1);
			Quicksort(A,wall_right+1,right);
		}
	}
}