package prog02;

/** FindOutput contains the output of find: boolean found and int
 * index.  If the entry is found, found is true and index is set to
 * its index.  If the entry is not found, found if false and index is
 * set to the index where it should go.
 * @author vjm
 */

public class FindOutput {
	/** True if entry if found. */
	public final boolean found;

	/** The index of the entry if it is there or where it should be if
	 * it is not.
	 */
	public final int index;

	FindOutput (boolean found, int index) {
		this.found = found;
		this.index = index;
	}
}

