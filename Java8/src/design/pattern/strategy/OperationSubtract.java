package design.pattern.strategy;

public class OperationSubtract implements Strategy {

	@Override
	public int doOperation(int a, int b) {
		return a-b;
	}

}
