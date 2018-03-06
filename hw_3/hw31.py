origin = ["","bcd","bcm","abcd","bbb","b","riueg","wvfe","abc","ane","abd","abb","ab","a"]
result = []

"""
input: string array, digit r
output: sorted array based on alphabet order
assumption: distinct strings in the array
basic logic: 
	BucketSort(array,r=0)
	
	BucketSort(array,r=0)
		if(len(array)==0) 
			result.append(array)
			return
		Buckets = [26]
		Buckets = CountSortByDigit(Array)
		Buckets = filter(len(x)>0,Buckets)
		for item in Buckets:
			BucketSort(item,r=r+1)
"""

def bucketSort(array,r=0):
	global result

	if(len(array)==1):
		result.append(array[0])
		return

	n = len(array)
	temp = [None]*26
	for i in range(26):
		temp[i] = []
	for i in range(n):
		try:
			k = ord(array[i][r])-ord("a")
			temp[k].append(array[i])
		except:
			result.append(array[i])
	temp_new = list(filter(lambda x: len(x)>0, temp))
	for item in temp_new:
		bucketSort(item,r=r+1)
bucketSort(origin)
print(result)


