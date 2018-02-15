package interfaces;

public interface Openable {

	/** Constants are allowed in interfaces and they make your program easier to read */
	public final static boolean OPEN = true;
	public final static boolean CLOSED = false;

	/** Returns whether this Openable object is open */
	public boolean isOpen();

	/** Opens this Openable object */
	public void doOpen();

	/** Closes this Openable object */
	public void doClose();
}
