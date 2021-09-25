package design.pattern.Decorator;

interface Cake {
	public  String getDescription();
	public int getPrice();
}

class SimpleCake  implements Cake   {
	private String description;
	private int price;
	
	public SimpleCake() {
		description = "Simple Cake";
		price = 100;
	}
	
	public String getDescription() {
		return description;
	}
	
	public   int getPrice() {
		return price;
	}
}

class CreamCake extends SimpleCake {
	private Cake cake;
	public CreamCake(Cake cake) {
		this.cake = cake;
	}
	
	public  String  getDescription() {
		return cake.getDescription()+", Cream Cake ";
	}
	
	public int getPrice() {
		return cake.getPrice()+50;
	}
}

class DesignerCake extends SimpleCake {
	private Cake cake;
	
	public DesignerCake(Cake cake)   {
		this.cake = cake;
	}
	
	public  String  getDescription() {
		return cake.getDescription()+", Designer Cake ";
	}
	
	public int getPrice() {
		return cake.getPrice()+70;
	}
}

public class CakeDecoratorExecutor {

	public static void main(String[] args) {
		Cake cake = new SimpleCake();
		cake = new CreamCake(cake);
		//cake = new DesignerCake(cake);
		
		System.out.println(cake.getDescription()+"\n"+cake.getPrice());
	}

}
