package prog04;

/**
 * This is an implementation of the prog02.PhoneDirectory interface that uses a
 * doubly linked list to store the data.
 * 
 * @author vjm
 */
public class SortedDLLPD extends DLLBasedPD {
	/**
	 * Add an entry or change an existing entry.
	 * 
	 * @param name
	 *            The name of the person being added or changed.
	 * @param number
	 *            The new number to be assigned.
	 * @return The old number or, if a new entry, null.
	 */
	public String addOrChangeEntry(String name, String number) {
		String oldNumber = null;
		FindOutput fo = find(name);
		if (fo.found) {
			oldNumber = fo.entry.getNumber();
			fo.entry.setNumber(number);
		} else {
			if (fo.entry == null) {
				DLLEntry newEntry = new DLLEntry(name, number);

				if (tail == null || head == null) {
					head = newEntry;
					tail = newEntry;
				} else {
					newEntry.setPrevious(tail);
					tail.setNext(newEntry);
					tail = newEntry;
				}
			}

			else if (!fo.entry.getName().equals(name)) {
				DLLEntry prev = fo.entry.getPrevious();
				DLLEntry next = fo.entry;
				DLLEntry newEntry = new DLLEntry(name, number);

				if (prev == null) {
					next.setPrevious(newEntry);
					newEntry.setNext(next);
					head = newEntry;
				}
				else {
					next.setPrevious(newEntry);
					prev.setNext(newEntry);
					newEntry.setNext(next);
					newEntry.setPrevious(prev);
				}
			}
		}
		return oldNumber;
	}

	/**
	 * Find an entry in the directory.
	 * 
	 * @param name
	 *            The name to be found.
	 * @return A FindOutput object describing the result.
	 */
	protected FindOutput find(String name) {
		// EXERCISE
		// Modify find so it also stops when it gets to an entry after the
		// one you want.
		int cmp;
		for (DLLEntry entry = head; entry != null; entry = entry.getNext()) {
			cmp = entry.getName().compareTo(name);
			if (cmp == 0)
				return new FindOutput(true, entry);
			else if (cmp > 0)
				return new FindOutput(false, entry);
		}
		return new FindOutput(false, null); // Name not found.
	}
}
