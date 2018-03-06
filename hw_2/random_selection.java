/*
worst case: O(n^2)
best case: O(n)
average case: O(n)
*/
public class random_selection{
	private int num = 0;
	quicksort_randomized QR = new quicksort_randomized();

	public void randomized_selection(int[] A, int left, int right,int i){
		if (left==right) {
			num =  A[left];
			return;
		}else if (left<right) {
			int wall = QR.partition(A,left,right);
			if(wall==i-1){
				num = A[wall];
				return;
			}else if(wall<i-1){
				randomized_selection(A,wall+1,right,i);
				return;	
			}else{
				randomized_selection(A,left,wall-1,i);
				return;
			}
		} 
	}
	public int getNum(){
		return num;
	}
}