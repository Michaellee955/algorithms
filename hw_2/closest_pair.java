public class closest_pair{

	int[] array;
	int[] Px;
	int[] Py;
	int n;

	public closest_pair(int[] A){
		this.array = A;
		this.Px = QuicksortByX(A);
		this.Py = QuicksortByY(A);
		this.n = Px.length;
	}

	public int compare(int[] A, int[] B){
		int distance = sqrt((A[0]-B[0])^2+(A[1]-B[1])^2);
		return distance;
	}
	/*
	input: two lists formated like: R [[x1,y1],[x2,y2]...] and L[[x6,y6],[x8,y8]...] and r given in the question.
	output: the least distance in the two lists.
	basic logic: 
		step 1: combine the two lists together at random, which means connecting R to the tail of L is fine.     [31]
		step 2: sort the above result according to Y coordinates(yi), form a new list Py.                        [32]
		step 3: iterate Py from begining, which means iterate the points from bottom up.                         [37-51]
				when concentrating one specific item in Py, calculate its distances with above 7 items 
				if any of the distances is smaller than dmin, set dmin = distance.                              
		step 4: return dmin                                                                                      [52]
	*/
	public int find_dmin(int[] L,int [] R,int r){
		int [] corpus = L+R;
		int [] Py = QuicksortByY(corpus);
		int len = Py.length;
		int dmin = r;
		int distance;
		int end;
		for (int i=0;i<len;i++) {
			//in case of "out of bound"error
			if (i+7<=len){
				end = 7;
			}else{
				end = len - i;		
			}

			for (int j=1;j<=end;j++){
				distance = compare(Py[i],Py[i+j]);
				if(distance<dmin){
					dmin = distance;
				}
			}
		}
		return dmin;
	}

	public int find_dmin_sorted(int[] strip,int r){
		int len = strip.length;
		int dmin = r;
		int distance;
		int end;
		for (int i=0;i<len;i++) {
			//in case of "out of bound"error
			if (i+7<=len){
				end = 7;
			}else{
				end = len - i;		
			}

			for (int j=1;j<=end;j++){
				distance = compare(strip[i],strip[i+j]);
				if(distance<dmin){
					dmin = distance;
				}
			}
		}
		return dmin;
	}

	public int[] find_strip_domain(int[] Py, int mid_value,int dmin){
		int len = Py.length;
		int[] strip;
		for (int j=0;j<len;j++) {
			if (Py[j][0]-mid_value<=dmin || mid_value-Py[j][0]<=0) {
				strip += strip;	
			}
		}
		return strip;
	}

	/*
	input: Px and Py, respectively sorted based on x and y coordinates. left and right index for x.
	output: dmin, the minimum distance for the given points in Px or Py. 
	        They have the same points, only the orders are different. 
	basic logic:
		step 1: if there's only one item left in the given Px, return the maximum distance([1,1]-[n^5,n^5]).    [107-109]
		step 2: split the given Px from middle index point. construct Px_left and Px_right.                     [113-114]
		step 3: iterate through the Py, according to their x coordinates, construct Py_left and Py_right        [116-127]
		step 4: recursively apply yhe find_overall_dmin function to the left and right parts                    [129-130]
		        getting dmin_left and dmin_right                  
		step 5: find the dmin=dmin_left and dmin_right,                                                         [131]
		        construct the strip list storing the points within the range of dmin, alongside mid_value       [133]
		step 6: find the minimum distance in the strip list, using the modified question(b) function.           [134]
		step 7: return the dmin.                                                                                [136]
	*/

	public int find_overall_dmin(int[] Px, int[] Py,int x_start, int x_end){

		if (x_start==x_end) {
			return 2*((this.n^5)-1);
		}

		int mid = (x_start+x_end)/2;
		
		int[] Px_left = Px[x_start:mid];
		int[] Px_right = Px[mid+1:x_end];
		
		int[] Py_left;
		int[] Py_right;
		int y_left = 0;
		int y_right = 0;
		
		for (int j=0;j<Py.length;j++) {
			if (Py[j][0]<=mid) {
				Py_left[y_left++];
			}else{
				Py_right[y_right++];
			}
		}

		int dmin_left = find_overall_dmin(Px_left,Py_left,x_start,mid);
		int dmin_right = find_overall_dmin(Px_right,Py_right,mid+1,x_end);
		int dmin = min(dmin_left,dmin_right);

		int strip[] = find_strip_domain(Py,Px[mid][0],dmin);
		dmin = find_dmin_sorted(strip,dmin);

		return dmin;
	}

	public int get_dmin(){
		retun find_overall_dmin(this.Px,this.Py,0,n-1);
	}
}