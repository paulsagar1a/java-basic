package design.pattern.strategy;

public class OperationAdd implements Strategy {
	public int doOperation(int a,  int b) {
		return a+b;
	}
}
