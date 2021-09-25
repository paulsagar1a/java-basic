package tree;

import tree.MaxSumPath.Node;

//lowest common ancestor
public class LCA {
	static class Node {
		int data;
		Node left;
		Node right;
		Node(int data)   {
			this.data = data;
			left = right = null;
		}
	}
	
	static Node findLCA(Node root, Node n1, Node n2) {
		if(root == null) return null;
		if(root.data == n1.data || root.data == n2.data) return root;
		Node  left = findLCA(root.left, n1, n2);
		Node right = findLCA(root.right, n1, n2);
		
		if(left != null  && right  !=null) return root;
		if(left == null && right == null) return null;
		return left!=null?left:right;
		
	}
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(2);
		root.left.left = new Node(8);
		root.left.right = new Node(8);
		root.left.left.left = new Node(-2);
		root.right.right = new Node(10);
		System.out.println(findLCA(root, new Node(10), new Node(8)).data);
	}

}
