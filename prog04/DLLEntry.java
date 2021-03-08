package prog04;
import prog02.DirectoryEntry;

/** Entry in doubly linked list
 */
public class DLLEntry extends DirectoryEntry {
  /** The next entry in the list. */
  private DLLEntry next;

  /** The previous entry in the list. */
  private DLLEntry previous;

  /** Creates a new instance of DLLEntry
      @param name Name of person.
      @param number Number of person.
  */
  public DLLEntry (String name, String number) {
    super(name, number);
  }

  /** Gets the next entry in the list.
      @return The next entry.
  */
  public DLLEntry getNext () {
    return next;
  }

  /** Sets the next entry in the list.
      @param next The new next entry.
  */
  public void setNext (DLLEntry next) {
    this.next = next;
  }

  /** Gets the previous entry in the list.
      @return The previous entry.
  */
  public DLLEntry getPrevious () {
    return previous;
  }

  /** Sets the previous entry in the list.
      @param previous The new previous entry.
  */
  public void setPrevious (DLLEntry previous) {
    this.previous = previous;
  }
}


