/*
time complexity: O(n+domain)
*/
public class counting_sort{
	accessory_hw2 acc = new accessory_hw2();
	public int[] sort(int[] A){
		// max and min and length of array
		int len = A.length;
		if (len<=1) {
			return A;
		}
		int max = acc.find_max(A);
		int min = acc.find_min(A);
		// move min to the zero point based on min
		if (min!=0) {
			acc.move(A,min);
			max = max - min;
		}
		// counting sort process
		int temp[] = new int[max+1];
		for (int i=0;i<=len-1;i++) {
			temp[A[i]] += 1; 
		}
		int result[] = new int[len];
		int m = 0;
		for (int domain=0;domain<=max;domain++) {
			for (int count=1;count<=temp[domain];count++) {
				result[m++] = domain;
			}
		}
		// move back the array based on switch value (min)
		if (min!=0) {
			acc.move(result,-min);
		}
		return result;
	} 
}