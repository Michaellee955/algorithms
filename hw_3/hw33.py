import tree
class TreeNode(object):
	def __init__(self,val):
		self.val = val
		self.left = None
		self.right = None
		self.height = 1
T = tree
def balance(x):
	balance = x.left.height - x.right.height

	while balance > 1 or balance < -1:
		if balance >1:
			rightRotate(T,x)
		else:
			leftRotate(T,x)
		balance(x.left)
		balance(x.right)

def AvlInsert(x,z):
	w = x
	while w != None:
		y = w
		if z.key>y.key:
			w = w.right
		else:
			w = w.left

	if z.key>y.key:
		y.right = z
		if y.left==None:
			y.height = 1
	while y!= x:
		y.height = 1+max(y.left.h+y.right.h)
		if y.left.height>y.right.height+1:
			rightRotate(T,y)
		if y.right.height>y.left.height+1:
			leftRotate(T,y)
			y = y.parent

def rightRotate(T,x):
	pass
def leftRotate(T,x):
	pass