import java.util.Arrays;
public class findLargestSubarrayDC{
	private static int max(int a, int b, int c){
		int max = a;
		if (b>max) {
			max = b;
		}
		if (c>max) {
			max = c;
		}
		return max;
	}
	private static int sum(int[] A, int left, int right){
		int sum = 0;
		for (int i = left;i<=right;i++) {
			sum = sum + A[i];
		}
		return sum;
	}
	/*
	maxbisides(): find the required left index and right index recursivly.
	input{
		Array A;
		left index: left;
		right index: right;
	}
	left and right index define the part of A in which to be found the required max sum. 
	output{
		left index after combination
		right index after combination
		sum of left part
		sum of right part
	}  
	*/
	/*
	step 0: if A is of length 2, return [left,right,A[left],A[right]]; else, operate next steps                 line 47-49
	step 1: find the middle index of the given array                                                            line 51
	step 2: calculate the sum of left half and right half respectively, named sum_left and sum_right            line 52-53
	step 3: do the maxbisides function on A[left] and A[right] recursivly                                       line 55-56               
	step 4: calculate the sum under each case(i<j<middle,i<middle<j,middle<i<j)                                 line 57-61
	step 4: get the return values and do the combination process. There are three cases when combining          line 63-90
			Case 1:  i<j<middle. return [i_left,j_left,sum(A[left:i_left],A[i_right]+sum_right]                              line 68-74
			Case 2: i<middle<j, return the results by pluging (A, left, right) in part b's function             line 75-81
			Case 3: middle<i<j, return [i_right,j_right,sum(A[middle:i_right])+sum_left,sum(A[j_right:right])]  line 82-88
	*/
	public static int[] maxbisides(int[] A, int left, int right){
		if (right-left==1) {
			return new int[] {left, right, A[left], A[right]};	
		}

		int middle = left+(right-left)/2;
		int sum_left = sum(A,left,middle);
		int sum_right = sum(A,middle+1,right);

		int[] left_para = maxbisides(A, left, middle);
		int[] right_para = maxbisides(A, middle+1, right);
		int[] middle_para = findLargestSubarray.maxmiddle(A, left, right);

		int max_left = left_para[2]+left_para[3]+sum_right;
		int max_right = right_para[2]+right_para[3]+sum_left;
		int max_middle = middle_para[2]+middle_para[3];

		int maximum = max(max_left,max_right,max_middle);
		int left_boundary = left-1;
		int right_boundary = right+1;
		int result_left_sum = 0;
		int result_right_sum = 0;
		if (maximum==max_left) {
			
			
			left_boundary = left_para[0];
			right_boundary = left_para[1];
			result_left_sum = left_para[2];
			result_right_sum = left_para[3]+sum_right;
		}else if (maximum==max_middle) {
			
			
			left_boundary = middle_para[0];
			right_boundary = middle_para[1];
			result_left_sum = middle_para[2];
			result_right_sum = middle_para[3];
		}else if (maximum==max_right) {
			
			
			left_boundary = right_para[0];
			right_boundary = right_para[1];
			result_left_sum = right_para[2]+sum_left;                                                                       //
			result_right_sum = right_para[3];
		}
		return new int[] {left_boundary,right_boundary,result_left_sum,result_right_sum};
	}
	public static void main(String[] args) {
		int[] test= {1,9,88,1,5,-200,4,8};
		int[] result = maxbisides(test,0,7);
		System.out.println(Arrays.toString(result));
	}
}