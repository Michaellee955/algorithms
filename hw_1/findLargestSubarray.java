import java.util.Arrays;
public class findLargestSubarray{
	/*
	maxmiddle: if i<middle<j, find the index i_pos and j_pos to find a max sum part: A[0:left]+A[right:length-1]
	input: {
		Array A
		left boundary: left (Specially, left>=0)
		right boundary: right
	}
	output{
		index in the left part: i_pos
		index in the right part: j_pos
		sum fromm left to i_pos: leftmax
		sum from j_pos to the end: rightmax
	}
	step 1: find the middle index to divide A into two parts, so that we can respectivly find i_pos and j_pos     line 22
	step 2: iterate i from left to the middle, find the leftmax and i_pos which makes the leftmax largest         line 29-35
	step 3: iterate j from right down to middle+1, find the rightmax and j_pos which makes the rightmax largest   line 36-42
	step 4: return the result in an array                                                                         line 43
*/
	public static int[] maxmiddle(int[] A, int left, int right){
		int middle = left+(right-left)/2;
		int i_pos = left;
		int j_pos = right;
		int leftmax = A[left];
		int rightmax = A[right];
		int cur_left = 0;
		int cur_right = 0;
		for (int i = left;i<=middle;i++) {
			cur_left = cur_left + A[i];
			if (cur_left>leftmax) {
				i_pos = i;
				leftmax = cur_left;
			}	
		}
		for (int j = right;j>=middle+1;j--) {
			cur_right = cur_right + A[j];
			if (cur_right>rightmax) {
				j_pos = j;
				rightmax = cur_right;
			}
		}
		return new int[] {i_pos,j_pos,leftmax, rightmax};	
	}
	public static void main(String[] args) {
		int[] A = {9,4,1,-3,-5,-1,4,6};
		int n = 8;
		int[] result = maxmiddle(A,0,n-1);
		System.out.println(Arrays.toString(result));
	}
}