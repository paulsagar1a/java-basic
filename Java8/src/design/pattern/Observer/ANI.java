package design.pattern.Observer;
import java.util.List;
import java.util.ArrayList;

public class ANI implements Subject{
	List<Observer> observers;
	String news;
	
	public ANI() {
		observers = new ArrayList<>();
	}
	
	public String getNews() {
		return news;
	}
	
	public void setNews(String news) {
		this.news = news;
		report();
	}
	
	@Override
	public void attach(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void report() {
		for(Observer observer : observers)  {
			observer.update(news);
		}
	}

}
