package prog06;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import prog02.GUI;
import prog02.UserInterface;

public class WordStep {
	public WordStep() {

	}

	static UserInterface ui = new GUI();

	private static class Node {
		String word;
		Node previous;

		Node(String word) {
			this.word = word;
		}
	}

	List<Node> nodes = new ArrayList<Node>();

	public static void main(String args[]) {
		WordStep game = new WordStep();
		String fileName = ui.getInfo("Enter dictionary file:");
		game.loadDictionary(fileName);
		
		// start word
		String startInput = ui.getInfo("Enter the starting word: ");
		if (startInput.length() == 0) {
			ui.sendMessage("No entry for starting word.");
			return;
		} else if (game.find(startInput) == null) {
			ui.sendMessage(startInput + " is not a valid word.");
			return;
		}
		
		//end word
		String endInput = ui.getInfo("Enter the ending word: ");
		if (endInput.length() == 0) {
			ui.sendMessage("No entry for ending word.");
			return;
		} else if (game.find(endInput) == null) {
			ui.sendMessage(endInput + " is not a valid word.");
			return;
		} else if (startInput.length() != endInput.length()) {
			ui.sendMessage("Words are not the same length.");
			return;
		}
		
		// commands
		String[] commands = { "Human plays.", "Computer plays." };
		int c = ui.getCommand(commands);
		if (c == 1)
			game.solve(startInput, endInput);
		else
			game.play(startInput, endInput);
	}

	void play(String start, String end) {
		String newCurrent = start;
		while (true) {
			ui.sendMessage("Current word: " + newCurrent + "\n" + "Target word: " + end); // first slot
			newCurrent = ui.getInfo("Enter next word: ");
			if (find(newCurrent) == null) {
				ui.sendMessage(newCurrent + " is not a valid word.");
				return;
			} else if (oneLetter(newCurrent, newCurrent) == false) { // second slot
				ui.equals("The two words are different by more than one letter!");
			} else if (newCurrent.equals(end)) { // first slow
				ui.sendMessage("You win!");
				return;
			}
			start = newCurrent;
		}
	}

	void loadDictionary(String file) {
		try {
			Scanner n = new Scanner(new File(file));
			while (n.hasNextLine()) {
				String word = n.nextLine();
				Node node = new Node(word);
				nodes.add(node);
			}
		} catch (Exception e) {
			ui.sendMessage("Error: " + e);
		}
	}

	void solve(String start, String end) {
		Queue<Node> queue = new LinkedList();
		Node startNode = find(start);
		queue.offer(startNode);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (int i = 0; i < nodes.size(); i++) {
				Node finder = nodes.get(i);
				if (!finder.word.equals(startNode.word) && finder.previous == null
						&& oneLetter(finder.word, node.word)) {
					finder.previous = node;
					queue.offer(finder);
					if (finder.word.equals(end)) {
						ui.sendMessage("You win!");
						String s = node.word + "\n" + end;
						while (node != startNode) {
							node = node.previous;
							s = node.word + "\n" + s;
						}
						ui.sendMessage(s);
						return;
					}
				}
			}
		}

	}

	Node find(String word) {
		for (int i = 0; i < nodes.size(); i++) {
			if (word.equals((nodes.get(i)).word)) {
				return nodes.get(i);
			}
		}
		return null;
	}

	static boolean oneLetter(String a, String b) {
		int count = 0;
		if (a.length() == b.length()) {
			for (int i = 0; i < a.length(); i++) {
				char aSub = a.charAt(i);
				char bSub = b.charAt(i);
				if (aSub != bSub)
					count++;
			}
			if (count <= 1)
				return true;
			else
				return false;
		} else
			return false;

	}
}
