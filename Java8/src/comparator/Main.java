package comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

class Employee {
	int id;
	String name;
	
	Employee(int id, String name) {
		this.id = id;
		this.name=name;
	}
	
	public String toString() {
		return id+" "+name;
	}
}

public class Main {

	public static void main(String[] args) {
		Employee e1 = new Employee(1,"Simon");
		Employee e2  = new  Employee(3, "Adam");
		Employee e3 =new Employee(2, "Tom");
		
		Map<Employee, Integer> map = new HashMap<>();
		map.put(e1, 10000);
		map.put(e2, 13000);
		map.put(e3, 11000);
		System.out.println(map);
		map = sortByEmpID(map);
		System.out.println(map);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map<Employee, Integer> sortByEmpID(Map<Employee, Integer> map) {
		List<Map.Entry<Employee, Integer>> list = new ArrayList<>();
		for(Map.Entry<Employee, Integer> e:map.entrySet()) {
			list.add(e);
		}
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				Map.Entry<Employee, Integer> e1 = (Map.Entry<Employee, Integer>)o1;
				Map.Entry<Employee, Integer> e2 = (Map.Entry<Employee, Integer>)o2;
				//return e1.getKey().id<e2.getKey().id?-1:1;//sort by employee id
				return e1.getValue()<e2.getValue()?-1:1;//sort by map vallue
			}
			
		});
		
		Map<Employee, Integer> resMap = new LinkedHashMap<>();
		for(Map.Entry<Employee, Integer> e:list) {
			resMap.put(e.getKey(), e.getValue());
		}
		return resMap;
	}

}