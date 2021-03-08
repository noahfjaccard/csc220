package prog12;

import java.io.IOException;
import java.util.*;
import prog02.GUI;

import javax.xml.stream.events.StartDocument;

public class Main2 {
	public static void main(String[] args) throws IOException {
		
		Browser browser = new BetterBrowser();
		SearchEngine newgle = new Tinge2();

		List<String> startingURLs = new ArrayList<String>();
		//startingURLs.add("http://www.cs.miami.edu");
		//startingURLs.add("http://www.cs.miami.edu/~vjm/csc220/google/mary.html");
		startingURLs.add("http://web.cs.miami.edu/home/jgmaster/google/");
		
		List<String> temp = new ArrayList<String>();

		for (int i = 0; i < startingURLs.size(); i++) {
			temp.add(BetterBrowser.reversePathURL(startingURLs.get(i)));
		}

		startingURLs = temp;

		newgle.gather(browser, startingURLs);

		List<String> keyWords = new ArrayList<String>();
		if (false) {
                  keyWords.add("mary");
                  keyWords.add("jack");
                  keyWords.add("jill");

		} else {
                  //keyWords.add("Victor");
                  //keyWords.add("Milenkovic");
                  GUI gui = new GUI();
                  while (true) {
                    String input = gui.getInfo("Enter search words.");
                    if (input == null)
                      return;
                    String[] words = input.split("\\s", 0);
                    keyWords.clear();
                    for (String word : words)
                      keyWords.add(word);
                    String[] urls = newgle.search(keyWords, 101);
                    String res = "Found " + keyWords + " on";
                    for (int i = 0; i < urls.length; i++)
                      res = res + "\n" + BetterBrowser.inverseReversePathURL(urls[i]);
                    gui.sendMessage(res);
                  }
		}

		String[] urls = newgle.search(keyWords, 101);

		System.out.println("Found " + keyWords + " on");
		for (int i = 0; i < urls.length; i++)
			System.out.println(BetterBrowser.inverseReversePathURL(urls[i]));
		
	}
}
