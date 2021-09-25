package design.pattern.strategy;

public class Context {
	Strategy strategy;
	
	Context(Strategy strategy)  {
		this.strategy = strategy;
	}
	
	public int executeOperation(int a, int b) {
		return strategy.doOperation(a, b);
	}
}
