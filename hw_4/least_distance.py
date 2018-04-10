A = [4,3,8,8,1]
B = [3,4,8,2]

def brutal_distance(A,B):
    i = len(A)
    j = len(B)

    result = []
    dist_opt = float("inf")
    for a in range(j):
        for b in range(j):
            for c in range(j):
                for d in range(j):
                    for e in range(j):
                        if a<=b and b<=c and c<=d and d<=e:
                            dist = abs(A[0]-B[a])+abs(A[1]-B[b])+abs(A[2]-B[c])+abs(A[3]-B[d])+abs(A[4]-B[e])
                            if dist<dist_opt:
                                dist_opt = dist
                                result.append([a,b,c,d,e])
    return dist_opt,result[len(result)-1]

def opti_simi(A,B):
    n = len(A)
    m = len(B)
    C = [[float("inf") for j in range(m)] for i in range(n)]
    C[0][0] = abs(A[0]-B[0])

    for i in range(1,n):
        C[i][0] = C[i-1][0] + abs(A[i]-B[0])
    for j in range(1,m):
        C[0][j] = min(C[0][j-1],abs(A[0]-B[j]))

    for i in range(1,n):
        for j in range(1,m):
            C[i][j] = C[i][j-1]
            dist = sum(abs(A[w]-B[j]) for w in range(i+1))
            for k in range(0,i):
                dist = dist - abs(A[k]-B[j])
                C[i][j] = min(C[i][j],C[k][j]+dist)
    return C[n-1][m-1]

def opti_simi_2(A,B):
    n = len(A)
    m = len(B)
    C = [[float("inf") for j in range(m)] for i in range(n)]
    C[0][0] = abs(A[0]-B[0])

    for i in range(1,n):
        C[i][0] = C[i-1][0] + abs(A[i]-B[0])
    for j in range(1,m):
        C[0][j] = min(C[0][j-1],abs(A[0]-B[j]))

    

    for i in range(1,n):
        for j in range(1,m):
            C[i][j] = C[i][j-1]
            dist = sum(abs(A[w]-B[j]) for w in range(i+1))
            for k in range(0,i):
                dist = dist - abs(A[k]-B[j])
                C[i][j] = min(C[i][j],C[k][j]+dist)
    return C[n-1][m-1]

print(opti_simi(A,B))



