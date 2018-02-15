package items;

import interfaces.Container;
import textadventure.World;

/**
 * An OpenContainer is an Item that can contain things but is not closeable.
 * Examples are things like open boxes, jars without a lid, etc.  Once you
 * have implemented all the required methods of the Container interface,
 * you'll be able to make OpenContainer Items that can hold Items.
 */
public class OpenContainer extends Item implements Container {

	// Add an attribute to keep track of the Items in this container.
	// This could be an ArrayList, HashMap, or whatever you'd like.
	
	
	public OpenContainer(World world, String name, int weight, boolean isTakeable, String description) {
		super(world, name, weight, isTakeable, description);
		// Initialize your list of items here
	}

	// Add the required methods of the Container interface here
}