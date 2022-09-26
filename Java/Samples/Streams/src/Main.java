import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;

public class Main {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(3, 6, 5, 2, 7, 23, 75, 35);

		// we want to sort and filter the list
		// we could do it with multiple lambda expression
//		list.sort((o1, o2) -> o1.compareTo(o2));
//		
//		List<Integer> filteredList = new ArrayList<>();
//	
//		int filter = 10;
//	
//		list.forEach((item) -> {
		
//			if (item > filter) {
//				filteredList.add(item);
//			}
//		});
//		
//		System.out.println(filteredList);

		// more easily we can do in one step usign streams

		//list.stream().sorted().map(item -> item + 10).filter(item -> item > 10).forEach(System.out::println);
		// list.stream().sorted().filter(item -> item > 10).forEach(item ->
		// System.out.println(item));
		
		int minValue =  list.stream().reduce((Integer i, Integer j)-> i<j ? i : j).get();
		System.out.println(minValue);

	}

}
