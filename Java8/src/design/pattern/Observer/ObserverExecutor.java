package design.pattern.Observer;

public class ObserverExecutor {

	public static void main(String[] args) {
		Subject subject = new ANI();
		
		Observer timesNow = new TimesNow();
		Observer indiaTV = new IndiaTV();
		
		subject.attach(timesNow);
		subject.attach(indiaTV);
		
		subject.setNews("Man cought 5 ft long fish!!!");
	}

}
