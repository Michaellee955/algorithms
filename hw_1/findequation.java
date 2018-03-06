import java.util.Arrays;
public class findequation {

	public static boolean find_equation(int[] A, int[] B, int n,int c){
		int i = 0;
		int j = 0;
		int middle = -1;     //define the middle index which should point to the last negative number in A
		if (A[n-1]<=0) {	 //set the middle point to tail if all A is non-positive.
			middle = n;
		}
		int[] A_new = new int[n];
		int[] B_new = new int[n];
		int[] pos_temp = new int[] {-1,-1};		   //set an flag position, for comparison purpose.
		for (i=0;i<=n-1;i++) {                     //construct the A_new[n] s.t. A_new[n] = (A[n])^2
			A_new[i] = (A[i])*(A[i]);
			if (A[i]>0 && i>0) {
				if (A[i-1]<0) {
					middle = i-1;                 //find the middle index if A which includes both negative and positive numbers.
				}
			}
		}
		for (j=0;j<=n-1;j++) {                    //construct the B_new[n] s.t. B_new[n] = B[n]+c
			B_new[j] = B[j]+c;
		}
		int[] pos = {-1,-1};                        
		// The three cases are discusses below: 
		//case 1: middle==-1, which means A is all non-negative, so we don't need to divide A_new.
		//		  apply the find_increased_equation function which compares two increasing arrays.
		//case 2: middle==n which means A is all non-positive, so A_new is decreasing
		//        apply the find_decreased_equation which compare a increasing array with decreasing one
		//case 3: A_new is firstly decreasing and then increasing
		//        divide the A_new into two parts, one is decreasing and the other increasing to apply corresponding functions.
		if (middle == -1){                                                    
			pos = find_increased_equation(A_new,B_new,0,n-1,n);                 
		}else if(middle == n) {                                               
			pos = find_decreased_equation(A_new,B_new,0,n-1,n);			      
		}else{                                                                
			int pos_de[] = find_decreased_equation(A_new,B_new,0,middle,n);   
			int pos_in[] = find_increased_equation(A_new,B_new,middle+1,n-1,n); 
			if (!Arrays.equals(pos_de,pos_temp)) {
				pos = pos_de;
			}
			if (!Arrays.equals(pos_in,pos_temp)) {
				pos = pos_in;
			}
		}

		if (Arrays.equals(pos,pos_temp)) {
			return false;
		}
		return true;
		
		//return pos;
	}	

	//Use double pointers to find the index for an increasing array, if not exists, return [-1,-1]
	private static int[] find_increased_equation(int[] A, int[] B, int A_start, int A_end, int B_length){
		int pos_i = A_start;
		int pos_j = 0;
		while (pos_i<=A_end && pos_j<=B_length-1) {
			if (A[pos_i] == B[pos_j]) {
				return new int[] {pos_i,pos_j};
			}else if (A[pos_i]>B[pos_j]) {
				pos_j = pos_j + 1; 
			}else if (A[pos_i]<B[pos_j]) {
				pos_i = pos_i + 1;
			}
		}
		return new int[] {-1,-1};
	}

	//Use double pointers to find the index for a decreasing array, if not exists, return [-1,-1]
	private static int[] find_decreased_equation(int[] A, int[] B, int A_start, int A_end, int B_length){
		int pos_i = A_end;
		int pos_j = 0;
		while (pos_i>=A_start && pos_j<=B_length-1) {
			if (A[pos_i] == B[pos_j]) {
				return new int[] {pos_i,pos_j};
			}else if (A[pos_i]>B[pos_j]) {
				pos_j = pos_j + 1; 
			}else if (A[pos_i]<B[pos_j]) {
				pos_i = pos_i - 1;
			}
		}
		return new int[] {-1,-1};
	}

	public static void main(String[] args) {
		int[] A = new int[] {-19,-18,9,10,11,12,13};
		int[] B = new int[] {1,3,5,6,7,100,143};
		int n = 7;
		int c = 1;
		boolean result = find_equation(A,B,7,1);
		System.out.println(result);
	}
}