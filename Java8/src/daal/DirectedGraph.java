package daal;

import java.util.ArrayList;
import java.util.List;
//detect cycle
public class DirectedGraph {
	int V;
	List<List<Integer>> adj;
	
	DirectedGraph(int v) {
		V=v;
		adj = new ArrayList<>(V);
		for(int i=0; i<V; i++) {
			adj.add(new ArrayList<Integer>());
		}
	}
	
	private void addEdge(int s, int d) {
		adj.get(s).add(d);
	}
	
	private void print() {
		for(int i=0; i<V; i++) {
			for(Integer e : adj.get(i)) {
				System.out.println(i+" "+e);
			}
		}
	}
	

	public static void main(String[] args) {
		DirectedGraph dg = new DirectedGraph(5);
		dg.addEdge(0, 1);
		dg.addEdge(1, 2);
		dg.addEdge(2, 3);
		dg.addEdge(3, 4);
		dg.addEdge(4, 2);
		dg.addEdge(4, 1);
		dg.print();
	}

}
