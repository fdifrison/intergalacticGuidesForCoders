import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class Employee {
	private String name;
	private int age;

	public Employee(String nameString, int age) {
		this.name = nameString;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

class Run {
	public static void main(String[] args) {
		Employee john = new Employee("B", 29);
		Employee mark = new Employee("A", 50);
		Employee snow = new Employee("D", 33);
		Employee albert = new Employee("C", 43);

		List<Employee> employees = new ArrayList<>();
		employees.add(john);
		employees.add(mark);
		employees.add(snow);
		employees.add(albert);

//		// Sort using Comparator
//		Collections.sort(employees, new Comparator<Employee>() {
//			@Override
//			public int compare(Employee o1, Employee o2) {
//				return o1.getName().compareTo(o2.getName());
//			}
//		});

		// Sorting using lambda
//		Collections.sort(employees, (Employee empl1, Employee empl2) -> empl1.getName().compareTo(empl2.getName()));
		// The compiler is able to infer the types by the first parameters
		Collections.sort(employees, (o1, o2) -> o1.getName().compareTo(o2.getName()));

		for (Employee employee : employees) {
			System.out.println(employee.getName());
		}

	}

}

interface DoSomething {
	public String add2Strings(String s1, String s2);
}

class Main2 {

	public static void main(String[] args) {

		int i = 0;

		String initAnonymous = useLambda(new DoSomething() {

			@Override
			public String add2Strings(String s1, String s2) {
				System.out.println(i);
				return s1.toUpperCase() + s2.toUpperCase();
			}
		}, "i", "love");

		System.out.println(initAnonymous);

		String initLambdaString = useLambda(func, "u", "hate");
		System.out.println(initLambdaString);

	}

	public final static String useLambda(DoSomething toDo, String s1, String s2) {
		return toDo.add2Strings(s1, s2);
	}

	public static DoSomething func = (s1, s2) -> {
		String result = s1.toUpperCase() + s2.toUpperCase();
		return result;
	};

}
