package linkedlist;

public class NextNumber {
	static class Node {
		int data;
		Node next;
		
		Node(int data)   {
			this.data = data;
			next = null;
		}
	}

	//1. find the last node where node.data !=9
	//2. if last == null, add new node and add head => node.next = head
	//3. increase node.data++ & node = node.next
	//4. make rest of the node value 0
	static Node addOne(Node root) {
		Node tmp = root;
		Node last =  null;
		
		while(tmp != null) {
			if(tmp.data != 9) {
				last = tmp;
			}
			tmp = tmp.next;
		}
		
		if(null == last) {
			last = new   Node(0);
			last.next = root;
			root = last;
		}
		
		last.data++;
		last = last.next;
		
		while(last != null) {
			last.data = 0;
			last = last.next;
		}
		return root;
	}
	
	static void  print(Node node) {
		while(node !=null) {
			System.out.println(node.data);
			node  = node.next;
		}
	}
	
	public static void main(String[] args) {
		Node root   =   new Node(1);
		root.next = new Node(2);
		root.next.next = new Node(9);
		root = addOne(root);
		print(root);
	}

}
