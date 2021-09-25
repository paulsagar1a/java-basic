package tree;

public class MaxSumPath2 {

	static int max = Integer.MIN_VALUE;
	static class Node {
		int data;
		Node left;
		Node right;
		
		Node(int data) {
			this.data = data;
			left = right = null;
		}
	}
	
	public static int maxPathSum(Node root) {
		postOrder(root);
		return max;
	}
	
	private static int postOrder(Node root) {
		if(root ==null)  return 0;
		int left = Math.max(postOrder(root.left), 0);
		int right = Math.max(postOrder(root.right), 0);
		max  =  Math.max(max, left+right+root.data);
		return Math.max(left, right)+root.data;
		
	}

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(2);
		root.left.left = new Node(8);
		root.left.right = new Node(8);
		root.left.left.left = new Node(-2);
		root.right.right = new Node(10);
		maxPathSum(root);
		System.out.println(max);
	}

}
