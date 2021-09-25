package lambda;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestFunctionalInterf {
	
	public static char getGrade(Student s) {
		if(s.marks >= 80) return 'A';
		else   if(s.marks  >=   60) return 'B';
		else  if(s.marks  >= 40) return 'C';
		else  if(s.marks  >= 30) return 'D';
		else return 'F';
	}
	
	public static void main(String[] args) {
		List<Student> l  = new ArrayList<>();
		l.add(new Student("Anmol", 92));
		l.add(new Student("Palki", 80));
		l.add(new Student("Pradip", 29));
		l.add(new Student("Sumit", 41));
		l.add(new Student("Kajal", 62));
		l.add(new Student("Mallika", 87));
		
		//list of students whose grade is  B or greater
		//Predicate, Function, Consumer, Supplier functional interface
		//use functional reference (::)
		
		
		/*Function<Student, Character> f = s -> {
			if(s.marks >= 80) return 'A';
			else   if(s.marks  >=   60) return 'B';
			else  if(s.marks  >= 40) return 'C';
			else  if(s.marks  >= 30) return 'D';
			else return 'F';
		};*/
		
		Function<Student, Character> f = TestFunctionalInterf::getGrade;
		
		
		Predicate<Student> p = s -> {
			if(f.apply(s) == 'A' || f.apply(s) == 'B') return true;
			else return false;
		};
		
		Consumer<Student> c = s -> System.out.println(s.name);
		
		Supplier<String> s =  () -> "List of students whose grade is  B or greater are";
		
		System.out.println(s.get());
		for(Student st : l) {
			if(p.test(st)) {
				c.accept(st);
			}
		}
		
	}
	
}

class Student {
	String name;
	int marks;
	
	public Student(String name, int marks) {
		this.name = name;
		this.marks =  marks;
	}
}