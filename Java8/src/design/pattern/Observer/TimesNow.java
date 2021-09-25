package design.pattern.Observer;

public class TimesNow implements Observer {

	@Override
	public void update(String news) {
		System.out.println("BREAKING NEWS "+news);
	}
	

}
