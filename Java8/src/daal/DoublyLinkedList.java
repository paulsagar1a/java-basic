package daal;

public class DoublyLinkedList {
	Node front, rear;
	static class Node{
		int val;
		Node next,prev;
		
		Node(int val) {
			this.val = val;
			next = prev = null;
		}
		
	}
	
	void addToFront(Node node) {
		if(front == null) {
			front = rear = node;
		} else {
			front.next = node;
			node.prev = front;
			front = node;
		}
	}
	
	void deleteFromRear() {
		if(rear != null) {
			rear = rear.next;
			rear.prev = null;
		}
	}
	
	void print() {
		Node tmp = rear;
		while(tmp != null) {
			System.out.print(tmp.val+" ");
			tmp = tmp.next;
		}
	}

	public static void main(String[] args) {
		DoublyLinkedList dll = new DoublyLinkedList();
		dll.addToFront(new Node(1));
		dll.addToFront(new Node(2));
		dll.addToFront(new Node(3));
		dll.addToFront(new Node(4));
		dll.addToFront(new Node(5));
		dll.print();
		dll.deleteFromRear();
		dll.deleteFromRear();
		dll.deleteFromRear();
		dll.addToFront(new Node(6));
		System.out.println();
		dll.print();
	}

}
