import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
		Employee john = new Employee("John", 33);
		Employee mark = new Employee("Mark", 33);
		Employee snow = new Employee("Snow", 33);
		
		List<Employee> employees = new ArrayList<>();
		employees.add(john);
		employees.add(mark);
		employees.add(snow);
		
		// Sort using Comparator
		Collections.sort(employees, new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o1.getName());
			}
		});
		
		for (Employee employee : employees) {
			System.out.println(employee.getName());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}