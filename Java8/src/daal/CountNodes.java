package daal;

public class CountNodes {
	
	static class Node {
		int key;
		Node left, right;
		Node(int item) {
			key = item;
			left = right = null;
		}
	}
	
	static int countNodes(Node root) {
		if(root == null) return 0;
		return   1+countNodes(root.left)+countNodes(root.right);
	}
	
	static int getHeight(Node root) {
		if(root == null) return 0;
		int lh = getHeight(root.left);
		int rh = getHeight(root.right);
		
		return lh>rh?lh+1:rh+1;
	}

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.right = new Node(5);
		root.left.left = new Node(4);
		//root.left.left.left= new Node(8);
		root.right = new Node(3);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		System.out.println("Number of Nodes = "+countNodes(root));
		int h = getHeight(root);
		System.out.println("Height  of the tree = "+h);
		System.out.println((int)(Math.pow(2, h) - 1));
	}

}
