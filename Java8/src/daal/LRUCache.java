package daal;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    Node front, rear;
    int capacity,size;
    Map<Integer, Node> map = null;
    static class Node{
        int key, value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            prev = next = null;
        }
    }
    
    private void addToFront(Node node) {
        if(front == null) {
            front = rear = node;
        } else {
            front.next = node;
            node.prev = front;
            front = node;
        }
        size++;
    }
    
    private void deleteRearNode() {
        if(rear != null) {
            rear = rear.next;
            rear.prev = null;
        }
        size--;
    }
    
    private void deleteNode(Node node) {
        if(node.prev != null && node.next != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        } else {
            //last node
            if(node.prev != null) {
                node.prev.next = null;
            } else if(node.next != null) { //first node
                rear = node.next;
                rear.prev = null;
            }
        }
        map.remove(node.key);
        size--;
    }
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        size = 0;
    }
    
    public int get(int key) {
        //find the position of the node
        //remove the node from the position
        //add it to the front
        if(map.containsKey(key)) {
            Node node = map.get(key);
            deleteNode(node);
            addToFront(node);
            System.out.println("get: "+node.value);
            return node.value;
        }
        System.out.println("get: "+(-1));
        return -1;
    }
    
    public void put(int key, int value) {
        //check if key present in the map
        //if true - delete the Node from the linkedlist
        //if false & size == capacity delet the rear node 
        //add the new node in the front
        //update the map with the new node
        if(map.containsKey(key)) {
            Node tmp = map.get(key);
            tmp.value = value;
            deleteNode(tmp);
        } else {
            if(size == capacity) {
                deleteRearNode();
            }
        }
        Node node = new Node(key, value);
        addToFront(node);
        map.put(key, node);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//["LRUCache","put","put","get","put","get","put","get","get","get"]
		//[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
		LRUCache lru = new LRUCache(2);
		lru.put(1, 1);
		lru.put(2, 2);
		lru.get(1);
		lru.put(3, 3);
		lru.get(2);
		lru.put(4, 4);
		lru.get(1);
		lru.get(3);
		lru.get(4);
		System.out.println(lru.map);
	}

}
