import java.util.Random;
public class accessory_hw2{
	
	public int[] randomArray(int n,int min, int max){
		int[] integers = new int[n];
		Random rand = new Random();
		for (int i = 0; i <= n-1; i++) {
			integers[i] = rand.nextInt(max-min+1)+min;
		}
		return integers;
	}

	public void exchange(int[] A, int index_1,int index_2){
		int temp = A[index_1];
		A[index_1] = A[index_2];
		A[index_2] = temp;
	}

	public int find_max(int[] A){
		int len = A.length;
		int result = A[0];
		for (int i=0;i<=len-1;i++) {
			if (A[i]>result) {
				result = A[i];
			}
		}
		return result;
	}

	public int find_min(int[] A){
		int len = A.length;
		int result = A[0];
		for (int i=0;i<=len-1;i++) {
			if (A[i]<result) {
				result = A[i];
			}
		}
		return result;
	}

	public void move(int[] A, int min){
		int len = A.length;
		for (int i=0;i<=len-1;i++) {
			A[i] = A[i]-min;
		}
	}

	public void swap_consective(int[] A, int left_bound1, int left_bound2, int right_bound1, int right_bound2){
		int[] temp = new int[left_bound2-left_bound1+1];
		int w = 0;
		for (int count=left_bound1;count<=left_bound2;count++) {
			temp[w++] = A[count];
		}
		int i = left_bound1;
		int j = right_bound1;
		int start_right = 0;
		
		while(i<=right_bound2){
			if (j<=right_bound2) {
				A[i++] = A[j++];
			}else {
				A[i++] = temp[start_right++];
			}
		}
	}
}