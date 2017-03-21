package main;

public class BinaryTree {
	private Node root;

	public BinaryTree() {
		this.root = new Node(0);
	}

	public Node root() {
		return root;
	}

	public Node addNode(Node node, int num) {
		if (node == null) {
			return new Node(num);

		} else {
			if (num <= node.data()) {
				node.setLeft(addNode(node.left(), num));
			} else {
				node.setRight(addNode(node.right(), num));
			}

			return node;
		}

	}
	
	public void insert(Node node, int num) {
		
		if(node == null){
			return;
		}
		
		if(node.data() < num){ //left
			if(node.left() != null){
				insert(node.left(), num);
			} else {
				Node ln = new Node(num);
				node.setLeft(ln);
				return;
			}
		
		} else {//right
			if(node.right() != null){
				insert(node.right(), num);
			} else {
				Node rn = new Node(num);
				node.setRight(rn);
				return;
			}
			
		}

	}

	public static class Node {
		private int data;
		private Node left;
		private Node right;

		public Node(int data) {
			this.data = data;
		}

		public int data() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node left() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node right() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		@Override
		public String toString() {
			return Integer.toString(this.data);
		}
	}

	void printPaths(Node node) { 
		  int[] path = new int[1000];
		  printPathsRecur(node, path, 0); 
		}

	void printPathsRecur(Node node, int path[], int pathLen) { 
		  if (node==null) return;

		  // append this node to the path array 
		  path[pathLen] = node.data(); 
		  pathLen++;

		  // it's a leaf, so print the path that led to here 
		  if (node.left()==null && node.right()==null) { 
		    printArray(path, pathLen); 
		  } 
		  else { 
		  // otherwise try both subtrees 
		    printPathsRecur(node.left(), path, pathLen); 
		    printPathsRecur(node.right(), path, pathLen); 
		  } 
		}

	void printArray(int ints[], int len) {
		int i;
		for (i = 0; i < len; i++) {
			System.out.print(String.format("%d ", ints[i]));
		}
		System.out.print("\n");
	}
}
