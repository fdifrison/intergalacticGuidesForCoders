public class testTestInterface implements MyTestInterface {

    @Override
    public void sayHello() {
        System.out.println("Hello there!");
    }

    @Override
    public boolean trueOrFalse(boolean answer) {
        return answer;
    }

    @Override
    public String whatsMyAgeAgain(int age, String name) {
        System.out.println("My name is " + name +" and I'm " + age + " years old");
        return null;
    }
}
