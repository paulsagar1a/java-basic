package daal;

import java.util.Stack;

class NextHotterDay{

static void nextHotterDay(int[] arr) {
	int length = arr.length;
	int[] nhdArr = new int[length];
	Stack<Integer> stack = new Stack<>();
	
	for(int  i=0;i<length;i++) {
		
		while(!stack.isEmpty() && arr[stack.peek()]<arr[i]) {
			nhdArr[stack.peek()] = i - stack.peek();
			stack.pop();
		}
		
		stack.push(i);
	}
	
	for(int i=0; i<length;i++) {
		System.out.println(nhdArr[i]);
	}
}
	
//Driver Code
public static void main (String[] args)
{
	int[] arr = { 73, 74, 75, 71,69, 72, 76, 73 };
	nextHotterDay(arr);
}

}

