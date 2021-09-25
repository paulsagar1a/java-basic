package design.pattern.Iterator;

interface Container2 {
	public Iterator2 iterator();
}

interface Iterator2 {
	public boolean hasNext();
	public Object next();
}

class CollectionOfAnimalIterate implements Iterator2 {
	int i;
	String[] arr;
	CollectionOfAnimalIterate(String[] arr) {
		this.arr = arr;
	}
	
	@Override
	public boolean hasNext() {
		if(i<arr.length) {
			return true;
		}
		return false;
	}

	@Override
	public Object next() {
		if(this.hasNext()) {
			return arr[i++];
		}
		return null;
	}
	
}

class CollectionOfAnimal implements Container2 {
	String[] name  =   {"TIGER", "LION",  "ELEPHENT", "HORSE", "CAT"};

	@Override
	public Iterator2 iterator() {
		return new CollectionOfAnimalIterate(name);
	}
	
}


public class IteratorExecutor2 {

	public static void main(String[] args) {
		CollectionOfAnimal collectionOfAnimal = new CollectionOfAnimal();
		
		Iterator2 itr = collectionOfAnimal.iterator();
		
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}

	}

}
