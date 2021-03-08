package prog03;

import prog02.UserInterface;
import prog02.GUI;

/**
 *
 * @author vjm
 */
public class Main {
	/** Use this variable to store the result of each call to fib. */
	public static double fibn;

	/**
	 * Determine the time in microseconds it takes to calculate the n'th Fibonacci
	 * number.
	 * 
	 * @param fib
	 *            an object that implements the Fib interface
	 * @param n
	 *            the index of the Fibonacci number to calculate
	 * @return the time for the call to fib(n)
	 */
	public static double time(Fib fib, int n) {
		// Get the current time in nanoseconds.
		long start = System.nanoTime();

		// Calculate the n'th Fibonacci number. Store the
		// result in fibn.
		fibn = fib.fib(n);

		// Get the current time in nanoseconds.
		long end = System.nanoTime();

		// Return the difference between the end time and the
		// start time divided by 1000.0 to convert to microseconds.
		return (end - start) / 1000.0;
	}

	/**
	 * Determine the average time in microseconds it takes to calculate the n'th
	 * Fibonacci number.
	 * 
	 * @param fib
	 *            an object that implements the Fib interface
	 * @param n
	 *            the index of the Fibonacci number to calculate
	 * @param ncalls
	 *            the number of calls to average over
	 * @return the average time per call
	 */
	public static double averageTime(Fib fib, int n, int ncalls) {
		double totalTime = 0;

		// Add up the total call time for ncalls calls to time (above).
		for (int i = 0; i < ncalls; i++) {
			totalTime += time(fib, n);
		}

		// Return the average time.
		return totalTime / ncalls;
	}

	/**
	 * Determine the time in microseconds it takes to to calculate the n'th
	 * Fibonacci number. Average over enough calls for a total time of at least one
	 * second.
	 * 
	 * @param fib
	 *            an object that implements the Fib interface
	 * @param n
	 *            the index of the Fibonacci number to calculate
	 * @return the time it it takes to compute the n'th Fibonacci number
	 */
	public static double accurateTime(Fib fib, int n) {
		// Get the time in microseconds using the time method above.
		double t = time(fib, n);

		// If the time is (equivalent to) more than a second, return it.
		if (t > 1e6)
			return t;

		// Estimate the number of calls that would add up to one second.
		// Use (int)(YOUR EXPESSION) so you can save it into an int variable.
		int numcalls = (int) (1e6 / t);

		// Get the average time using averageTime above and that many
		// calls and return it.
		return averageTime(fib, n, numcalls);
	}

	private static UserInterface ui = new GUI();

	public static void doExperiments(Fib fib) {
		UserInterface ui = new GUI();
		int n = 0;
		double c = 0;
		double estTime;
		while (true) {
			try {
				String sNum = ui.getInfo("Enter an integer: ");
				if (sNum == null) {
					ui.sendMessage("Goodbye!");
					break;
				}
				n = Integer.parseInt(sNum);
				if (n < 0) {
					ui.sendMessage("Invalid Input!");
					break;
				}
			} catch (Exception ex) {
				ui.sendMessage("Invalid Input!");
		
				break;
			}
			estTime = 0;
			if (c != 0) {
				estTime = c * fib.o(n);
				ui.sendMessage("Estimated Time: " + estTime);
			}
			double time = accurateTime(fib, n);
			c = time / fib.o(n);
			double perError = ((estTime - time) / time) * 100;
			if (perError == -100.0)
				ui.sendMessage("n: " + n + "\n" + "fib(n): " + fib.fib(n) + "\n" + "Running Time: " + time);
			else
				ui.sendMessage("n: " + n + "\n" + "fib(n): " + fib.fib(n) + "\n" + "Running Time: " + time + "\n"
						+ "Percent Error: " + perError + "%");
		}
	}

	public static void doExperiments() {
		String[] options = {"Cancel", "ConstantFib", "ExponentialFib", "LinearFib", "LogFib", "MysteryFib" };
		while (true) {
			int choice = ui.getCommand(options);
			switch (choice) {
			case 0:
				ui.sendMessage("Goodbye!");
				return;
			case 1:
				doExperiments(new ConstantFib());
				break;
			case 2:
				doExperiments(new ExponentialFib());
				break;
			case 3:
				doExperiments(new LinearFib());
				break;
			case 4:
				doExperiments(new LogFib());
				break;
			case 5:
				doExperiments(new MysteryFib());
				break;
			default:
				return;
			}
		}
	}

	static void labExperiments() {
		// Create (Exponential time) Fib object and test it.
		Fib efib = new ExponentialFib();
		System.out.println(efib);
		for (int i = 0; i < 11; i++)
			System.out.println(i + " " + efib.fib(i));

		// Determine running time for n1 = 20 and print it out.
		int n1 = 20;
		double time1 = averageTime(efib, n1, 1000);
		System.out.println("n1 " + n1 + " time1 " + time1);
		int ncalls = (int) (1e6 / time1);
		time1 = averageTime(efib, n1, ncalls);
		System.out.println("The time1 was: " + time1 + " with " + ncalls + " calls");
		time1 = accurateTime(efib, n1);
		System.out.println("The time1 was: " + time1 + " with " + ncalls + " calls");

		// Calculate constant: time = constant times O(n).
		double c = time1 / efib.o(n1);
		System.out.println("c: " + c);

		// Estimate running time for n2=30.
		int n2 = 30;
		double time2est = c * efib.o(n2);
		System.out.println("n2 " + n2 + " estimated time: " + time2est);

		// Calculate actual running time for n2=30.
		double time2 = averageTime(efib, n2, 100);

		ncalls = (int) (1e6 / time2);
		time2 = averageTime(efib, n2, ncalls);
		System.out.println("The time2 was: " + time2 + " with " + ncalls + " calls");
		time2 = accurateTime(efib, n2);
		System.out.println("The time2 was: " + time2 + " with " + ncalls + " calls");

		int n3 = 100;
		double time3est = c * efib.o(n3);
		System.out.println("n3 " + n3 + " estimated time: " + time3est);
		double years = time3est / 1e6 / 3600 / 24 / 365.25;
		System.out.println("Years: " + years);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		doExperiments();
		labExperiments();
	}
}
