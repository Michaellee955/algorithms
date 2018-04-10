from collections import defaultdict

class BiPartite(object):
    """"description"""
    """
    input 
        graph
        color dictionary: record which set one node belongs to  
        visited set: record nodes which have been visited 
        previous dictionary: record the previous visited node of one node
        is_bipartite: record the graph is bipartite or not
        odd_cycle: record one odd cycle if exists,  empty otherwise.
        
    main function
        For nodes which are not visited, conduct "dfs" function on the node.
        This is in case of the graphs having several connected components.
    """
    def __init__(self,graph):
        self.graph = graph
        self.color = defaultdict(bool)
        self.visited = set()
        self.prev_to = dict()
        self.is_bipartite = True
        self.odd_cycle = []

        for n in self.graph:
            if n not in self.visited:
                self.color[n] = True
                self.dfs(n)

    """
    basic logic
        step 1: for a vertex called node, make it visited by putting it into visited set. 
        step 3: for each neighbor called adj of node, there are three cases:
            case 1: if the graph has already been proved unbipartite, return.
            case 2: color it with different color with node if it hasn't been visited. 
                    form a previous dictionary which is like {adj,node}
                    recursively call dfs with input of "adj"
            case 3: if "adj" has already been visited and its color is equal to "node"
                    get the odd cycle using above previous dictionary, as well as set the "bipartite" to False 
    """

    def dfs(self,node):
        self.visited.add(node)
        for adj in self.graph[node]:
            if self.is_bipartite==False:
                return
            if adj not in self.visited:
                self.prev_to[adj] = node
                self.color[adj] = not self.color[node]
                self.dfs(adj)
            elif self.color[adj] == self.color[node]:
                self.is_bipartite = False
                cycle = []
                cycle.append(node)
                n = node
                while n != adj:
                    n = self.prev_to[n]
                    cycle.append(n)
                cycle.append(node)
                self.odd_cycle.append(cycle)
                return
if __name__ == '__main__':
    graph = {
        0: {1, 4},
        1: {0, 4, 2, 3},
        2: {1, 3},
        3: {1, 4, 2},
        4: {3, 0, 1},
        5: {6},
        6: {5, 7},
        7: {6},
        100: {102, 103},
        102: {100, 103},
        103: {100, 102}
    }

    graph_1= {
        0: {1,3},
        1: {0,2},
        2: {3,1},
        3: {0,2}
    }

    graph_2 = {
        100: {102, 103},
        102: {100, 103},
        103: {100, 102}
    }

    bipartition = BiPartite(graph_1)
    print(bipartition.is_bipartite)
    print(bipartition.color)
    print(bipartition.odd_cycle)
