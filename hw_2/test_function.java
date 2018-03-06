import java.util.Arrays;

public class test_function{
	public static void main(String[] args) {
		quicksort_randomized QR = new quicksort_randomized();
		quicksort_deterministic QD = new quicksort_deterministic();
		accessory_hw2 acc = new accessory_hw2();
		random_selection RS = new random_selection();
		counting_sort CS = new counting_sort();
		quicksort_3partition Q3P = new quicksort_3partition();

		//int[] test = acc.randomArray(10,-5,5);
		int[] test = new int[] {1, 3, 5, 29, 49, 8, 38, 42, 51, 66, 54, 69, 79, 105, 111, 76, 85, 88, 110, 123, 97, 99, 100, 116, 124};
		System.out.println(Arrays.toString(test));

		int[][] multi = new int[][]{
  			{ 1, 3, 5, 29, 49},
  			{ 8, 38, 42, 51, 66},
  			{ 54, 69, 79, 105, 111},
  			{ 76, 85, 88, 110, 123},
  			{ 97, 99, 100, 116, 124}
		};
		int[][] multi1 = new int[][]{
			{ 1, 3},
  			{ 8, 38},
		};
		find_median FM = new find_median(multi);
		System.out.println(FM.find_medians());
		//test quicksort_randomized
		
		QR.Quicksort(test,0,24);
		System.out.println(Arrays.toString(test));
/*
		//test quicksort_deterministic 
		QD.Quicksort(test,0,9);
		System.out.println(Arrays.toString(test));
		
		//test random_selection
		int k = 2;
		RS.randomized_selection(test,0,9,k);
		System.out.println(RS.getNum());

		//test counting_sort
		int[] result = CS.sort(test);
		System.out.println(Arrays.toString(result));
		
		//test swap part
		acc.swap_consective(A,1,4,5,9);
		System.out.println(Arrays.toString(A));
		*/

		//test three partition
	}
}