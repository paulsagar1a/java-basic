package design.pattern.Observer;

public class IndiaTV implements Observer {

	@Override
	public void update(String news) {
		System.out.println("BIG NEWS COMMING IN "+news);
	}

}
