package design.pattern.strategy;

public class StrategyExecutor {

	public static void main(String[] args) {
		Context context = new Context(new OperationAdd());
		System.out.println(context.executeOperation(2, 3));
		context = new Context(new OperationSubtract());
		System.out.println(context.executeOperation(5, 2));
	}

}
