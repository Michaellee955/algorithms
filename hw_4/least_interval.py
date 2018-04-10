import random

def exchange(A,p,q):
    k = A[p]
    A[p] = A[q]
    A[q] = k

def partition(A, left, right):
    wall = left-1
    pivot = random.randint(left,right)
    exchange(A,pivot,right)
    for cur in range(left,right):
        if A[cur]<A[right]:
            wall += 1
            exchange(A,wall,cur)
    exchange(A,wall+1,right)
    return wall+1

def random_quciksort(A,left,right):
    if(left<right):
        wall = partition(A,left,right)
        random_quciksort(A,left,wall-1)
        random_quciksort(A,wall+1,right)

A = [0,2,6,1,1.5,6,9]
random_quciksort(A,0,len(A)-1)
print(A)
result = []
while(len(A)!=0):
    x = A[0]
    i = 0
    try:
        while(A[i]<=x+1):
            i += 1
    except:
        pass
    for k in range(i):
        A.remove(A[0])
    result.append([x,x+1])
print(result)