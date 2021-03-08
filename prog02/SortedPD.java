package prog02;

/**
 *
 * @author vjm
 */
public class SortedPD extends ArrayBasedPD {

	/**
	 * Add an entry or change an existing entry.
	 * 
	 * @param name
	 *            The name of the person being added or changed
	 * @param number
	 *            The new number to be assigned
	 * @return The old number or, if a new entry, null
	 */
	
	public String addOrChangeEntry(String name, String number) {
		String oldNumber = null;
		FindOutput fo = find(name);
		int insertionVariable = fo.index;
		if (insertionVariable < size && name.equals(theDirectory[insertionVariable].getName())) {
			oldNumber = theDirectory[insertionVariable].getNumber();
			theDirectory[insertionVariable].setNumber(number);
		} 
		else {
			if (size >= theDirectory.length)
				reallocate();

			for (int i = size; i > insertionVariable; i--) {
				theDirectory[i] = theDirectory[i - 1];
			}
			theDirectory[insertionVariable] = new DirectoryEntry(name, number);
			size++;

			return null;
		}
		

		return oldNumber;
	}

	/**
	 * Look up an entry.
	 * 
	 * @param name
	 *            The name of the person
	 * @return The number. If not in the directory, null is returned
	 */

	// use findOutput to complete hw, use same code from lookup entry

	/**
	 * Find an entry in the directory.
	 * 
	 * @param name
	 *            The name to be found
	 * @return A FindOutput object containing the result of the search.
	 */
	

	protected FindOutput find(String name) {
		int first = 0;
		int last = size-1;
		int middle = 0;
		while (last >= first) {
			middle = (last + first) / 2;
			int cmp = theDirectory[middle].getName().compareTo(name);
			if (cmp < 0)												 
				first = middle + 1;									 
			else if (cmp > 0)
				last = middle - 1;
			if (cmp == 0)		
				return new FindOutput(true, middle);
		}
		return new FindOutput(false, first);
	}
	
	/** FindOutput contains the output of find: boolean found and int
	 * index.  If the entry is found, found is true and index is set to
	 * its index.  If the entry is not found, found if false and index is
	 * set to the index where it should go.
	 * @author vjm
	 */
	
	/**
	 * Remove an entry from the directory.
	 * 
	 * @param name
	 *            The name of the person to be removed
	 * @return The current number. If not in directory, null is returned
	 */
	public String removeEntry(String name) {
		FindOutput fo = find(name);
		if (!fo.found)
			return null;
		DirectoryEntry entry = theDirectory[fo.index];
		for (int i = fo.index; i < size; i++) {
			theDirectory[i] = theDirectory[i + 1];
		}
		size--;
		modified = true;
		return entry.getNumber();
	}
}