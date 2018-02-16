package items;

import interfaces.Openable;
import textadventure.World;

/**
 * A CoseableContainer is an OpenableContainer that can contain things (because its parent
 * is OpenContainer) but is also openable/closeable.  Once you have implemented
 * all the required methods of the Openable interface, you'll be able to make
 * closeable containers such as a treasure box, cabinet, lunch box, etc.
 */
public class CloseableContainer extends OpenContainer implements Openable {

	/** Keeps track of whether this CloseableContainer is open */
	private boolean isOpen;

	public CloseableContainer(World world, String name, int weight, boolean isTakeable, boolean isOpen, String description) {
		super(world, name, weight, isTakeable, description);
		this.isOpen = isOpen;
	}

	// Add the required methods of the Openable interface here
}