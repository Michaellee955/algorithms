public class find_median{
	int[][] array;
	int col_num;
	int row_num;
	int mid;

	public find_median(int[][] A){
    	this.array = A;
    	this.col_num = A[0].length;
    	this.row_num = A.length;
	}


	/*
	input: a number x within the range [1,n^4]
	output: the number of entries which are smaller than the given x
	basic logics:
		step 1: set the initial point at the right-up corner, and the result as 0
		step 2: iterate to the left-bottom corner:
				while:
					if(current_number>x): 	
						set col=col+1;
					elseif(current<=x):
						result=result+current_column_number;
						set row=row+1;
				when the row or column goes out of bound, end the while loop
				return result
	*/
	public int find_entries(int x){
		int col = this.col_num-1;
		int row = 0;
		int entries = 0;
		while(col>=0 && row>=0 && row<this.row_num){
			if (this.array[row][col]>x) {
				col = col-1;
			} else {
				entries += col+1;
				row += 1;	
			}
		}
		return entries;
	}

	/*
	input: array
	output: the median value
	function description: firstly through binary search, choose two boundaries which can 
						   can restrict out only one number: smaller_bound<x<=larger_bound
						  secondly I iterate from the left-bottom to right-up to find this x
	basic logics:
		step 1: set the initial value for some parameters listed below.
		       {
					larger_bound: a number that is larger than or equal to median
					smaller_bound: a number that is smaller than median
					mid: a number which is the mean of above 2 values
					larger_count: number of entries based on larger_bound
					smaller_count: number of entries based on smaller_bound
					mid_count: number of entries based in mid
		       }
		step 2: the binary search method to recursively locate smaller_bound and larger_bound
				  while:
				    mid = (larger_bound+smaller_bound)/2;
			        mid_count = find_entries(mid);
				    a. if the entry number of mid is larger than or equal to n^2/2
				  	   then set the new larger_bound=mid and larger_count=mid_count;
				    b. if the entry number of mid is smaller than n^2/2
				       then set the new smaller_bound=mid and smaller_count=mid_count;
                  do the while loop until there's only one item left between two bounds
        step 3: according to the bounds, iterate from the left-bottom corner to left-up 
                corner to find the coorect value, which will be the median.
	*/
	public int find_medians(){
		int larger_bound = this.array[row_num-1][col_num-1]; 
		int smaller_bound = this.array[0][0];
		int larger_count = this.col_num*this.row_num;
		int smaller_count = 1;
		int mid;
		int mid_count;
		int object = (1+this.col_num*this.row_num)/2;
		while (larger_count-smaller_count>1) {
			mid = (larger_bound+smaller_bound)/2;
			mid_count = find_entries(mid);
			if (mid_count>=object) {
				larger_bound = mid;
				larger_count = mid_count;
			}else {
				smaller_bound = mid;
				smaller_count = mid_count;
			}
		}

		int i = this.row_num-1;
		int j = 0;
		while (this.array[i][j]<=smaller_bound || this.array[i][j]>larger_bound) {
			if (this.array[i][j]<=smaller_bound){
				j += 1;
			}else{
				i -= 1;
			}
		}
		return this.array[i][j];
	}
}