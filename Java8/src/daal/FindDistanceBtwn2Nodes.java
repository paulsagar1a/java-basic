package daal;

public class FindDistanceBtwn2Nodes {
	
	static class Node {
		int data;
		Node left;
		Node right;
		
		Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}
	
	static Node findLCA(Node root,Node n1,Node n2) {
		if(root ==  null) return null;
		if(root.data == n1.data ||    root.data == n2.data) return root;
		Node left = findLCA(root.left,n1,n2);
		Node  right = findLCA(root.right, n1,n2);
		if(left == null && right== null) return null;
		if(left !=null && right !=    null) return root;
		return left!=null?left:right;
	}
	
	static int findDistance(Node root, Node n1,  Node n2) {
		Node lca = findLCA(root,n1,n2);
		System.out.println("LCA = "+lca.data);
		int a = findLevel(lca,n1,0);
		System.out.println("a = "+a);
		int b = findLevel(lca,n2,0);
		System.out.println("b = "+b);
		return a+b;
	}

	private static int findLevel(Node root, Node n, int i) {
		if(root == null) return -1;
		if(root.data == n.data) return i;
		int left = findLevel(root.left,n,i+1);
		if(left == -1) 
			return findLevel(root.right,n,i+1);
		return left;
	}

	public static void main(String[] args) {
		Node root = new Node(10);
		root.left = new Node(20);
		root.left.left = new Node(2);
		root.left.right = new Node(19);
		root.right = new Node(30);
		root.right.left = new Node(3);
		root.right.right = new Node(18);
		root.right.right.right = new  Node(92);
		int r = findDistance(root, new Node(2),   new Node(92));
		System.out.println(r);

	}

}
