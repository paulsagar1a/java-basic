package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
	
	public static int findMin(int i1,  int i2) {
		return i1>i2?1:i1<i2?-1:0;
	}
	
	public static void getNewVal(double d) {
		System.out.println(d*10);
	}
	
	public static void main(String[] args) {
		List<Integer> l = new ArrayList<>();
		l.add(4);
		l.add(3);
		l.add(1);
		l.add(2);
		l.add(5);
		l.add(6);
		l.add(8);
		l.add(7);
		l.add(9);
		l.add(0);
		
		Stream<Integer> s  =  l.stream().map(n -> n*2);
		//System.out.println("Sort value= "+s.sorted((i1, i2) -> i1>i2?-1:i1<i2?1:0).collect(Collectors.toList()));
		//System.out.println("stream count= "+s.count());
		//System.out.println("max= "+s.max((i1, i2) -> i1.compareTo(i2)).get());
		System.out.println("min= "+s.min(TestStream::findMin).get());
		
		
		l = l.stream().map(n -> n*n).collect(Collectors.toList());
		System.out.println(l);
		l = l.stream().filter(n -> n%2 == 0).collect(Collectors.toList());
		l.stream().forEach(System.out::println);
		
		//stream().toArray
		Integer[] arr = l.stream().toArray(Integer[]::new);
		System.out.println(arr);
		
		//stream().of
		Stream<Integer> sg = Stream.of(9, 99, 999, 9999); //for group of value
		sg.forEach(System.out::println);
		
		Double[] darr = {10.0, 10.1, 10.2, 10.3, 10.4};
		Stream<Double> sdarr = Stream.of(darr);
		sdarr.forEach(TestStream::getNewVal);
		
	}

}
