package prog04;

/** FindOutput contains the output of find: boolean found and DLLEntry
 * entry.  If the entry is found, found is true and entry points to its
 * DLLEntry.  If the entry is not found, found if false and entry points
 * to the DLLEntry which would be next after the entry if it is added.
 * If the new entry should go at the end (tail), then entry is set to
 * null since no DLLEntry should come next after it.
 * @author vjm
 */

public class FindOutput {
	/** True if entry if found. */
	public final boolean found;

	/** The DLLEntry the entry if it is there or the next one after
	 * it if it is not.
	 */
	public final DLLEntry entry;

	FindOutput (boolean found, DLLEntry entry) {
		this.found = found;
		this.entry = entry;
	}
}

