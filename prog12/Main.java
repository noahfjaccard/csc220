package prog12;

import java.util.*;

import javax.xml.stream.events.StartDocument;

public class Main {
	public static void main(String[] args) {
		Browser browser = new BetterBrowser();
		SearchEngine newgle = new Tinge();

		List<String> startingURLs = new ArrayList<String>();
		//startingURLs.add("http://www.cs.miami.edu");
		startingURLs.add("http://www.cs.miami.edu/~vjm/csc220/google/mary.html");

		List<String> temp = new ArrayList<String>();

		for (int i = 0; i < startingURLs.size(); i++) {
			temp.add(BetterBrowser.reversePathURL(startingURLs.get(i)));
		}

		startingURLs = temp;

		newgle.gather(browser, startingURLs);

		List<String> keyWords = new ArrayList<String>();
		if (true) {
			keyWords.add("mary");
			keyWords.add("jack");
			keyWords.add("jill");
		} else {
			keyWords.add("Victor");
			keyWords.add("Milenkovic");
		}

		String[] urls = newgle.search(keyWords, 10);

		System.out.println("Found " + keyWords + " on");
		for (int i = 0; i < urls.length; i++)
			System.out.println(BetterBrowser.inverseReversePathURL(urls[i]));
	}
}
