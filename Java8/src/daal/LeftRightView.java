package daal;

import java.util.Queue;
import java.util.LinkedList;
//breath first search of a binary tree a) in line printing  b) level order printing
public class LeftRightView {
	static class Node {
		int data;
		Node left, right;
		Node(int data) {
			this.data=data;
		}
	}
	
	public static void levelOrderTraverseA(Node root) {
		if(root == null) return;
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		
		while(!q.isEmpty()) {
			Node tmp = q.poll();
			System.out.print(tmp.data+" ");
			
			if(tmp.left !=null) {
				q.add(tmp.left);
			}
			if(tmp.right !=null) {
				q.add(tmp.right);
			}
		}
	}
	
	public static void levelOrderTraverseB(Node  root) {
		if(null == root) return;
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(null);
		
		while(!q.isEmpty()) {
			Node tmp = q.poll();
			if(null != tmp) {
				System.out.print(tmp.data+" ");
				if(tmp.left != null) {
					q.add(tmp.left);
				}
				if(tmp.right != null) {
					q.add(tmp.right);
				}
			} else {
				System.out.println();
				if(q.size() != 0) {
					q.add(null);
				}
			}
		}
		
	}
	
	public static void leftViewIterator(Node root) {
		if(root == null) return;
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				Node tmp = q.poll();
				if(i==0) {
					System.out.print(tmp.data+" ");
				}
				if(tmp.left != null) {
					q.add(tmp.left);
				}
				if(tmp.right != null) {
					q.add(tmp.right);
				}
			}
		}
	}
	
	public static void rightViewIterator(Node root) {
		if(root == null) return;
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				Node tmp = q.poll();
				if(i==size-1) {
					System.out.print(tmp.data+" ");
				}
				if(tmp.left != null) {
					q.add(tmp.left);
				}
				if(tmp.right != null) {
					q.add(tmp.right);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(9);
		root.right = new Node(3);
		root.right.left = new Node(4);
		root.right.right = new Node(5);
		root.right.right.left = new Node(6);
		root.right.right.right = new Node(7);
		root.right.right.right.right = new Node(8);
		System.out.println("\nLevel order traversal (inline)::");
		levelOrderTraverseA(root);
		System.out.println("\nLevel order traversal (level-by-level)::");
		levelOrderTraverseB(root);
		System.out.println("\nLeft view::");
		leftViewIterator(root);
		System.out.println("\nRight View::");
		rightViewIterator(root);
	}

}
