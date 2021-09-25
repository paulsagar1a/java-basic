package daal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MinShortestPath {

	private class Node {
		int key; double val;
		Node(int key, double val) {
			this.key = key;
			this.val = val;
		}
	}
	public double minShortestPath(int n, int[][] edges, double[] succProb, int start, int end) {
		HashMap<Integer, ArrayList<Node>> graph = new HashMap<>();
		for(int i=0;i<n;i++) {
			graph.putIfAbsent(i, new ArrayList<Node>());
		}
		
		for(int i=0; i<edges.length; i++) {
			int a = edges[i][0];
			int b = edges[i][1];
			graph.get(a).add(new Node(b, succProb[i]));
		}
		
		HashSet<Integer> set = new HashSet<>();
		PriorityQueue<Node> queue = new PriorityQueue<>((a,b) -> (int)(a.val-b.val));
		queue.offer(new Node(start, 0.0));
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(set.contains(node.key)) {
				continue;
			}
			if(node.key == end) {
				return node.val;
			}
			set.add(node.key);
			for(Node e : graph.get(node.key)) {
				if(!set.contains(e.key)) {
					double sum = node.val+e.val;
					queue.offer(new Node(e.key, sum));
				}
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		int n = 5;
		int[][] edges = {{0,1}, {0,2}, {1,3}, {2,4}, {3,4}};
		double[] succProb = {3.0, 2.0, 1.0, 6.0, 5.0};
		int start = 0;
		int end = 4;
		MinShortestPath msp = new MinShortestPath();
		System.out.println(msp.minShortestPath(n, edges, succProb, start, end));
	}

}

