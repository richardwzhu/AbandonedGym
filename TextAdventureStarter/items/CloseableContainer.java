package items;

import interfaces.Openable;
import textadventure.World;

/**
 * A CoseableContainer is a Container that can contain things (because its parent
 * is and OpenContainer) but is also openable/closeable.  Once you have implemented
 * all the required methods of the Openable interface, you'll be able to make
 * closeable Items such as a treasure boxe, cabinet, lunch box, etc.
 */
public class CloseableContainer extends Item implements Openable {

	/** Keeps track of whether this Container is open */
	private boolean isOpen;

	public CloseableContainer(World world, String name, int weight, boolean isTakeable, boolean isOpen, String description) {
		super(world, name, weight, isTakeable, description);
		this.isOpen = isOpen;
		// Initialize your list of items here
	}

	// Add the required methods of the Openable interface here
}