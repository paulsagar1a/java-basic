package design.pattern.Iterator;

interface Iterator {
	public boolean hasNext();
	public Object next();
}

interface Container {
	public Iterator getIterator();
}

class CollectionOfName implements Container {
	String[] names = {"Suman", "Deepshikha", "Sonali", "Raddur", "Abir"};
	
	public Iterator getIterator() {
		return new CollectionOfNameIterate(names);
	}
}

class CollectionOfNameIterate implements Iterator  {
	private int i;
	private String[] arr;
	
	public CollectionOfNameIterate(String[] arr) {
		this.arr  =  arr;
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

public class IteratorExecutor {

	public static void main(String[] args) {
		CollectionOfName collectionOfName = new CollectionOfName();
		Iterator itr = collectionOfName.getIterator();
		
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

}
