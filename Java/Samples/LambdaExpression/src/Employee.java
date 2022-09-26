import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee john = new Employee("jhon", 29);
		Employee mark = new Employee("mark", 50);
		Employee snow = new Employee("snow", 33);
		Employee albert = new Employee("albert", 43);

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
//
//		for (Employee employee : employees) {
//			System.out.println(employee.getName());
//		}

		// using forEach
		employees.forEach(employee -> {
			System.out.println(employee.getName());
		});

		printEmployees(employees, 40);

		printEmployeesWithPredicate(employees, employee -> employee.getAge() < 40);

		// Supplier
		Supplier<Integer> randomSupplier = () -> new Random().nextInt();
		for (int i = 0; i < 10; i++) {
			System.out.println(randomSupplier.get());
		}

		// Function interface
		Function<Integer, String> getNameAtIndex = (Integer i) -> {
			System.out.println("Employee at index " + i);
			return employees.get(i).getName();
		};
		
		String name = getNameAtIndex.apply(2);
		System.out.println(name);

	}

	private static void printEmployees(List<Employee> employees, int age) {
		employees.forEach(employee -> {
			if (employee.getAge() <= age)
				System.out.println(employee.getName());
		});
	}

	private static void printEmployeesWithPredicate(List<Employee> employees, Predicate<Employee> ageCondition) {
		System.out.println("using predicates to generalize the method");
		employees.forEach(employee -> {
			if (ageCondition.test(employee))
				System.out.println(employee.getName());
		});
	}

	IntPredicate testANumberIntPredicate = i -> i > 10;

}

interface DoSomething {
	public String add2Strings(String s1, String s2);
}

class Main2 {

	public static void main(String[] args) {

		DoSomething func = (s1, s2) -> {
			String result = s1.toUpperCase() + s2.toUpperCase();
			return result;
		};

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

}
