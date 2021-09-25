package design.pattern.Decorator;

//Decorator is a Structural Design type, which works on Open-Close principle
abstract class Pizza {
	private String description = "Unknown Pizza";
	
	public String getDescription() {
		return description;
	}
	
	public abstract int getCost();
}

abstract class ToppingDecorator extends Pizza {
	public abstract String getDescription();
}

class SimplePizza extends Pizza {

	public String getDescription() {
		return "Simple Pizza";
	}
	
	@Override
	public int getCost() {
		return 200;
	}
	
}

class FreashTomato extends ToppingDecorator {
	Pizza pizza;
	public FreashTomato(Pizza pizza) {
		this.pizza = pizza;
	}
	
	@Override
	public String getDescription() {
		return pizza.getDescription()+", Fresh Tomato ";
	}

	@Override
	public int getCost() {
		return pizza.getCost()+50;
	}
	
}

class Barbeque extends ToppingDecorator  {
	Pizza pizza;
	public Barbeque(Pizza pizza) {
		this.pizza = pizza;
	}
	
	@Override
	public String getDescription() {
		return pizza.getDescription()+", Barbeque ";
	}

	@Override
	public int getCost() {
		return pizza.getCost()+200;
	}
	
}



public class DecoratorExecutor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pizza pizza   = new SimplePizza();
		System.out.println(pizza.getDescription()+"\n"+pizza.getCost());
		
		//pizza = new FreashTomato(pizza);
		pizza = new Barbeque(pizza);
		
		System.out.println(pizza.getDescription()+"\n"+pizza.getCost());
	}

}
