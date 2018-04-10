graph = {
    0: {1, 4},
    1: {0, 4, 2, 3},
    2: {1, 3},
    3: {1, 4, 2},
    4: {3, 0, 1},
    5: {6},
    6: {5,7},
    7: {6},
    100:{102, 103},
    102:{100,103},
    103:{100,102}
}

def bfs_traversal(graph, start):
    queue = [start]
    visited = set()
    while queue:
        vertex = queue.pop(0)
        print(vertex)
        if vertex not in visited:
            visited.add(vertex)
            queue.extend(graph[vertex] - visited)
    return visited


def dfs_traversal(graph,start,count):
    global visited
    stack = [start]
    comp[count] = set(stack)
    while stack:
        vertex = stack.pop()
        if vertex not in visited:
            visited.add(vertex)
            comp[count].add(vertex)
            stack.extend(graph[vertex] - visited)

def cc(graph):
    global visited
    c = 0
    for key, item in enumerate(graph):
        if item not in visited:
            c += 1
            dfs_traversal(graph,item,c)

if __name__ == '__main__':

    visited = set()
    comp_list = [0 for x in range(len(graph))]
    comp = {}
    cc(graph)
    result = {}
    for key,nodes in enumerate(comp):
        for node in comp[nodes]:
            result[node] = list(comp[nodes])
    #print(result)
    print(comp)





