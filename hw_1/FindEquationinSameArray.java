//Firstly I sort the given array A and A_new using merge sort algorithm, in which A_new is A^2 for each item in A.
//Then I traverse to take each item in A as c which is give in last problem, repeatly conduct the same method in findequation.java
//Since the last problem is O(n) complexity, this one is therefore O(n^2) because another outer traversing is required here.
import java.util.Arrays;
class mergeResult {
   private int l;
   private int[] c;
   public mergeResult(int l,int[] c){
   	this.l = l;
   	this.c = c;
   }
   public int getL() {
        return l;
   }
   public void setL(int l) {
       this.l = l;
   }
   public int[] getC() {
       return c;
   }
   public void setC(int[] c) {
       this.c = c;
   }    
}

public class FindEquationinSameArray{

	public static mergeResult mergeSort(mergeResult obj){
		int[] A = obj.getC();
		int n = obj.getL();
		if (n==1) {
			obj.setL(n);
			obj.setC(A);
			return obj;
		}
		int middle = (n-1)/2;
		int[] A_left = copyPartArray(A,0,middle);
		int[] A_right = copyPartArray(A,middle+1,n-1);
		
		mergeResult obj_left = new mergeResult(middle+1,A_left);
		mergeResult obj_right = new mergeResult(n-middle-1,A_right);

		obj_left = mergeSort(obj_left);
		obj_right = mergeSort(obj_right);

		obj = merge(obj_left,obj_right);
		return obj;
	}

	private static mergeResult merge(mergeResult obj_left,mergeResult obj_right){
		int[] A_left = obj_left.getC();
		int[] A_right = obj_right.getC();
		int A_left_length = obj_left.getL();
		int A_right_length = obj_right.getL();
		int k = A_left_length+A_right_length-1;
		int[] A = new int[k+1];

		int i = A_left_length-1;
		int j = A_right_length-1;
		while(i>=0 && j>=0){
			A[k--] = A_left[i] > A_right[j] ? A_left[i--]:A_right[j--]; 
		}
		while (j>=0) {
			A[k--] = A_right[j--];
		}
		while (i>=0) {
			A[k--] = A_left[i--];
		}
		mergeResult obj = new mergeResult(A_left_length+A_right_length,A);
		return obj;
	}

	private static int[] copyPartArray(int[] A, int start, int end){
		int length = end-start+1;
		int[] B = new int[length];
		for (int i = start;i<=end;i++) {
			B[i-start] = A[i];
		}
		return B;
	}
	//main function 
	public static int[] find_in_same_array(int[] A, int n){ //given array A and length n
		mergeResult obj = new mergeResult(n,A);             
		obj = mergeSort(obj);              //get the result of merge_sort in a object including sorted A_cur and length n
		int[] A_new = obj.getC();
		int[] result = {-1,-1};
		int[] pos_wrong = {-1,-1};
		for (int k = 0;k<=n-1;k++) {                        // outer traverse to set c=A[k], k from 0 to n-1
			int c = A_new[k];
			result = findequation.find_equation(A_new,A_new,n,c);  //apply the find_equation function in part a. to get result.
			if (!Arrays.equals(result,pos_wrong)) {       //if find the position, return the values in those positions
				return new int[] {A_new[result[0]],A_new[result[1]],A_new[k]};  
			}
		}
		return pos_wrong; //if none, return [-1,-1]
	}
	//find the index given the value in an array.
	private static int findIndex(int[] A, int length, int val){  //given the array A, length and value. 
		for (int i = 0;i<=length-1;i++) {                    //traverse to find where the val is and return its index if found.
			if (A[i]==val) {
				return i;
			}
		}
		return -1;   //if none, return -1. But in this specific question, it must exist because A and A_new share common values.
	}
	//testing function
	public static void main(String[] args) {
		int[] list = new int[10];
    	for (int i=0; i<10; i++){
        	int n = (int)(Math.random()*9 -5);
        	list[i] = n;
        }
		System.out.println(Arrays.toString(list));
		int[] result = find_in_same_array(list,10);
		int[] pos_wrong = {-1,-1};
		if (!Arrays.equals(result,pos_wrong)) {
			System.out.println(Arrays.toString(result));
			int output_1 = findIndex(list,10,result[0]);
			int output_2 = findIndex(list,10,result[1]);
			int output_3 = findIndex(list,10,result[2]);
			int[] output = {output_1,output_2,output_3};
			System.out.println(Arrays.toString(output));

		}else {
			System.out.println("no result found");
		}
	}
} 
