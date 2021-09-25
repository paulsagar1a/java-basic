package daal;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class TreeLevelOrderTraversal {
	
	static class Node {
		int data;
		Node left, right;
		Node(int data) {
			this.data=data;
			left = right = null;
		}
	}
	
		// Iterative function to print the left view of a given binary tree
	    public static void leftView(Node root)
	    {
	        // return if the tree is empty
	        if (root == null) {
	            return;
	        }
	        // create an empty queue and enqueue the root node
	        Queue<Node> queue = new ArrayDeque<>();
	        queue.add(root);
	 
	        // to store the current node
	        Node curr;
	 
	        // loop till queue is empty
	        while (!queue.isEmpty())
	        {
	            // calculate the total number of nodes at the current level
	            int size = queue.size();
	            int i = 0;
	 
	            // process every node of the current level and enqueue their
	            // non-empty left and right child
	            while (i++ < size)
	            {
	                curr = queue.poll();
	 
	                // if this is the first node of the current level, print it
	                if (i == 1) {
	                    System.out.print(curr.data + " ");
	                }
	 
	                if (curr.left != null) {
	                    queue.add(curr.left);
	                }
	 
	                if (curr.right != null) {
	                    queue.add(curr.right);
	                }
	            }
	        }
	    }

	public static void main(String[] args) {
		Node  root=new Node(4);
		root.left = new  Node(5);
		root.right = new Node(2);
		root.left.left = new  Node(9);
		root.right.left = new Node(3);
		root.right.right = new Node(1);
		root.right.left.left = new Node(6);
		root.right.left.right = new Node(7);
		leftView(root);
	}

}
