import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

public class Main {
	public static void main(String[] args) {
		// Path filePath1 = FileSystems.getDefault().getPath("WorkingDirFile.txt");
		// // Path file2 = FileSystems.getDefault().getPath("..\\SubDirFile.txt");
		// //Path file2 = FileSystems.getDefault().getPath("files", "SubDirFile.txt");
		// Path filePath2 =
		// Paths.get("C:\\Users\\gfrison\\Documents\\GitHub\\PyFry-v1\\Java\\Samples\\Paths\\files\\SubDirFile.txt");
		// printFile(filePath1);
		// printFile(filePath2.normalize());
		walkTree();
	}

	private static void printFile(Path path) {
		try (BufferedReader fileReader = Files.newBufferedReader(path)) {
			String line;
			while ((line = fileReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void walkDir() {
		Path dir = FileSystems.getDefault().getPath(".\\Paths");
		try (DirectoryStream<Path> content = Files.newDirectoryStream(dir)) {
			for (Path file : content) {
				System.out.println(file.getFileName());
			}
		} catch (IOException | DirectoryIteratorException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void walkTree() {
		Path dir = FileSystems.getDefault().getPath(".");
		try {
			Files.walkFileTree(dir, new myWalkFilesTree());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
