heap = [2,6,1,8,25,435,8,10]

def max_heapify(A, i):
	left = 2 * i + 1
	right = 2 * i + 2
	largest = i
	if left < len(A) and A[left] < A[largest]:
		largest = left
	if right < len(A) and A[right] < A[largest]:
		largest = right
	if largest != i:
		A[i], A[largest] = A[largest], A[i]
		max_heapify(A, largest)

def build_max_heap(A):
	for i in range(len(A) // 2, -1, -1):
		max_heapify(A, i)

build_max_heap(heap)
length = len(heap)
heap.insert(0,0)
print(heap)
result = []

"""
input: heapArray array, threshold x, index i
output: array of numbers which are smaller than x in heap
assumption: the first item in heap is 0 
            so that we can start from the "1" index in programming
basic logic:
	so simple and easily understandable as below.
"""
def SmallerThanX(array, x,i=1):
	global length
	global result
	if array[i]>=x:
		return
	result.append(array[i])
	if 2*i <= length:
		SmallerThanX(array,x,i=2*i)
	if 2*i+1 <= length:
		SmallerThanX(array,x,i=2*i+1)

SmallerThanX(heap,80)
print(result)



