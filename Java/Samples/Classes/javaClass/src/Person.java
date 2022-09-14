public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person() {
        System.out.println("Empty constructor");
    }
    public Person(String firstName, String lastName) {
        System.out.println("Constructor called");
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public void setAge(int age) {
        if (age >= 0)  {this.age = age;} else this.age = 0;
    }

    public boolean isTeen() {
        return (this.age > 12 && this.age < 20);
    }

    public String getFullName() {
        if (this.firstName.isEmpty() && this.lastName.isEmpty()) {
            return "";
        } else if (this.firstName.isEmpty()) {
            return this.lastName;
        } else if (this.lastName.isEmpty()) {
            return this.firstName;
        } else return this.firstName + " " + this.lastName;

    }

}
