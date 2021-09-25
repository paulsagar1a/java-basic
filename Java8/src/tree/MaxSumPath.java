package tree;

public class MaxSumPath {
	
	static class Node {
		int data;
		Node left;
		Node right;
		
		Node(int data) {
			this.data = data;
			left = right = null;
		}
	}
	
	static class Res {
	    public int val;
	}
	
	static int findMaxUtil(Node node, Res result) {
		if(node == null) return 0;
		int left = findMaxUtil(node.left, result);
		int right = findMaxUtil(node.right, result);
		
		int m1 = max(max(left, right)+node.data, node.data);
		int m2 = max(m1, left+right+node.data);
		result.val = max(m2, result.val);
		System.out.println("m1 = "+m1);
		System.out.println("m2 = "+m2);
		System.out.println("result.val = "+result.val+"\n");
		
		return m1;
	}
	

	private static int findMaxSum(Node root, int minValue) {
		Res res = new Res();
        res.val = Integer.MIN_VALUE;
        findMaxUtil(root, res);
        return res.val;
	}

	static int max(int a, int b) {
		return a>b?a:b;
	}
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(2);
		root.left.left = new Node(8);
		root.left.right = new Node(8);
		root.left.left.left = new Node(-2);
		root.right.right = new Node(10);
		System.out.println(findMaxSum(root, Integer.MIN_VALUE));
	}

}
