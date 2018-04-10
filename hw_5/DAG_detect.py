"""code description"""
"""
getGraph: get the required graph from input array.
input: array like [[a1,b1,c1],[a2,b2,c2]...] which means a1 is c1 than b1.
       c1=1 for "smaller than" and 0 for "equal to or smaller than"
output: graph like {node1:{(adj1,wei1),(adj2,wei2)}}
        where wei1 is "c" described above.  
"""

def getGraph(ineq):
    graph = {}
    for item in ineq:
        key = item[0]
        adj = item[1]
        weight = item[2]
        app = (adj,weight)
        if(None == graph.get(key)):
            graph[key] = {app}
        else:
            graph[key].add(app)
    return graph

"""
class parameters: 
    graph: graph obtained by getGraph function
    black: a set recording finished nodes
    grey: a set recording currently being explored nodes
    detect: boolean parameter recording if it's consistent or not
"""

class DAG(object):
    def __init__(self,ineq):
        self.graph = getGraph(ineq)
        self.black = set()
        self.grey = set()
        self.detect = True
        self.prev_to = {}
        self.value = {}
        self.edges = {}

    """
    main function:
        step1: for each possible connected components in graph, execute the visit function from one node.
        step2: there are four cases below when detecting the cycle.
            case1: if the status has already been changed to false, then return without any further operation
            case2: else if this node has already been finished, return without any further operation
            case3: else if the node is currently being explored, then there is a cycle:
                a) if the weights around this cycle sum to 0, leave it alone since they can be equal values.
                b) if the sum is greater than 0, change "detect" to False, meaning it's not consistent
            case4: else if this node has some adjacent, apply visit function on this node and current sum_weight
        step3: add the node to "grey" and "black" before and after the case4 operation 
    """

    """
    function: 1. detect all cycles in this graph and represent this cycle using a node
              2. obtain an array which stores nodes with no incoming.  
    Note: The SCC has been discussed in class in detail, so I introduce it here as a black box.
    """
    def SCC(self):
        pass
    """
    function: get the value for one node.
    basic logic: step1: initialize the max as 1
                 step2: for each node called prev, which is parent of current node, get its values
                 step3: if the value hasn't been calculated out, recursively apply update function on prev
                 step4: compare the (value[prev]+weight[prev,node]) with max, if latter is smaller, update max.
                 step5: give value[node] with max and return  
    """
    def update(self,node):
        max = 1
        for prev in self.prev_to[node]:
            if None == self.value.get(prev):
                self.update(prev)
            weight = self.edges[prev,node]
            if weight+self.value[prev] > max:
                max = weight+self.value[prev]
        self.value[node] = max
        return



    def judge(self):
        for n in self.graph:
            if n not in self.black:
                self.visit(n)

    def visit(self,node,sum_weight=0):
        if self.detect == False:
            return
        if node in self.black:
            return
        if node in self.grey:
            if sum_weight>0:
                self.detect = False
                return
            elif sum_weight == 0:
                return
        self.grey.add(node)
        if (None!=self.graph.get(node)):
            for adj in self.graph[node]:
                sum_weight += adj[1]
                self.visit(adj[0],sum_weight)
        self.black.add(node)

if __name__ == '__main__':
    ineq_1 = [[2,1,1],[1,3,0]]
    ineq_2 = [[1,2,1],[2,3,0],[3,1,0],[4,3,1]]
    ineq_3 = [[1,2,1],[3,2,1]]
    model = DAG(ineq_2)
    model.judge()
    print(model.detect)
    print(model.graph)

    """
    Initialization and main function
    """
    for node in (no_parent_array):
        model.value[node] = 1
    for node in (no_children_array):
        model.update(node)






