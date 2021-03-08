package prog12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordTable extends HashMap<String, Long> {

	private static final String DIRECTORY = System.getProperty("user.dir")
			+ "/word.dir/";

	public WordTable() {
		try {
			Path path = Paths.get(DIRECTORY);
			if (!Files.exists(path))
				Files.createDirectory(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	void write(HardDisk<List<Long>> wordDisk) {
		for (String word : keySet()) {

			Charset charset = Charset.forName("UTF-8");
			try (BufferedWriter writer = Files.newBufferedWriter(
					Paths.get(DIRECTORY + word.replaceAll("/", " ")), charset)) {
				writer.write(get(word) + "\n");
				writer.write(join(wordDisk.get(get(word)), " "));
			} catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}
		}
	}

	void read(HardDisk<List<Long>> wordDisk) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths
				.get(DIRECTORY))) {

			for (Path file : stream) {
				String word = file.getFileName().toString()
						.replaceAll(" ", "/");

				Charset charset = Charset.forName("UTF-8");
				try (BufferedReader reader = Files.newBufferedReader(file,
						charset)) {
					Long index = Long.parseLong(reader.readLine());

					wordDisk.newFile(); // everytime we read a word from disk,
										// pretend like we've created a new
										// word file, that way if we index more
										// words along with the
										// words from disk,
										// we don't get duplicate indices

					List<Long> list = new LinkedList<Long>();
					for (String s : reader.readLine().split(" ")) {
						list.add(Long.parseLong(s));
					}
					put(word, index);
					wordDisk.put(index, list);
				} catch (IOException x) {
					System.err.format("IOException: %s%n", x);
				}
			}

		} catch (IOException | DirectoryIteratorException x) {
			// IOException can never be thrown by the iteration.
			// In this snippet, it can only be thrown by newDirectoryStream.
			System.err.println(x);
		}
	}

	private static String join(List list, String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i != 0)
				sb.append(s);
			sb.append(list.get(i));
		}
		return sb.toString();
	}
}
