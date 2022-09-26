import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Exercizes {

	public static void main(String[] args) {

		Runnable runnable = () -> {
			String myString = "this is a string to split";
			String[] partStrings = myString.split(" ");
			for (String s : partStrings) {
				System.out.println(s);
			}
		};

		runnable.run();

		System.out.println("---------------------");
		System.out.println("Using Interface");

		everySecondChar lambdaFunc = (String source) -> {
			StringBuilder returnValBuilder = new StringBuilder();
			for (int i = 0; i < source.length(); i++) {
				if (i % 2 == 1) {
					returnValBuilder.append(source.charAt(i));
				}
			}
			return returnValBuilder.toString();
		};

		String resultString = everySecondCharacter(lambdaFunc, "1234567890");
		System.out.println(resultString);

		System.out.println("---------------------");
		System.out.println("Using Function");

		Function<String, String> usignLambdaFunction = (String source) -> {
			StringBuilder returnValBuilder = new StringBuilder();
			for (int i = 0; i < source.length(); i++) {
				if (i % 2 == 1) {
					returnValBuilder.append(source.charAt(i));
				}
			}
			return returnValBuilder.toString();
		};

		String resultString2 = usignLambdaFunction.apply("1234567890");
		System.out.println(resultString2);

		System.out.println("---------------------");

		Supplier<String> supplier = () -> {
			return "I love Java";
		};

		String iLoveJava = supplier.get();
		System.out.println(iLoveJava);

		System.out.println("---------------------");

		List<String> toNames = Arrays.asList("Amelia", "Olivia", "emily", "Isla", "Ava", "oliver", "Jack", "Charlie",
				"harry", "Jacob");
		
		List<String> newList =  toNames.stream().map((s) -> s.substring(0, 1).toUpperCase() + s.substring(1)).sorted(String::compareTo).collect(Collectors.toList());
		newList.stream().forEach(System.out::println);
		
		System.out.println("---------------------");

		long howMany = toNames.stream().map((s) -> s.substring(0, 1).toUpperCase() + s.substring(1)).sorted(String::compareTo).filter((s)-> s.startsWith("A")).count();
		System.out.println(howMany);
		
		System.out.println("---------------------");
		
		toNames.stream().map((s) -> s.substring(0, 1).toUpperCase() + s.substring(1)).sorted(String::compareTo).peek(System.out::println);
	}

	private static String everySecondCharacter(everySecondChar esc, String s1) {
		return esc.appendNoEnveChar(s1);
	}

}

interface everySecondChar {
	public String appendNoEnveChar(String s1);
}
