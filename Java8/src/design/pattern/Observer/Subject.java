package design.pattern.Observer;

public interface Subject {
	//attach
	//detach
	//notify
	//get
	//set
	
	public void attach(Observer observer);
	public void detach(Observer Observer);
	public void report();
	public String getNews();
	public void setNews(String news);
}
