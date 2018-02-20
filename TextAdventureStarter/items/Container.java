package items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import textadventure.World;

public class Container extends Item {

	// Add an attribute to keep track of the Items in this container.
	// This could be an ArrayList, HashMap, or whatever you'd like.
	HashMap<String, Item> items;

	public Container(World world, String name, String description) {
		super(world, name, 0, Item.NOT_TAKEABLE, description);
		// Initialize this Container's list of items
		items = new HashMap<String, Item>();
	}

	public Container(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
		// Initialize your list of items here
		items = new HashMap<String, Item>();
	}

	/**
	 * Determines whether an item can be added to this Container.
	 * By default, any item can be added.
	 */
	public boolean canAddItem(Item item) {
		return true;
	}

	/**
	 * Adds an Item to this container's item list.
	 */
	public void addItem(Item item) {
		items.put(item.getName(), item);
	}

	/**
	 * Determines whether an item can be removed from this container.
	 * By default, any item can be removed.
	 */
	public boolean canRemoveItem(Item item) {
		return true;
	}	

	public Item removeItem(String itemName) {
		return items.remove(itemName);
	}

	public Item removeItem(Item item) {
		return items.remove(item.getName());
	}

	public Item getItem(String itemName) {
		return items.get(itemName);
	}

	public boolean hasItem(Item item) {
		return items.containsKey(item.getName());
	}

	public boolean hasItem(String itemName) {
		return items.containsKey(itemName);
	}

	/**
	 * Return a string describing the Container's non-scenery items
	 */
	public String getItemString() {
		Collection<Item> mapValues = items.values();
		Iterator<Item> roomItemIterator = mapValues.iterator();

		// Make a list of non-scenery items
		List<Item> roomItems = new ArrayList<Item>();

		while (roomItemIterator.hasNext()) {
			Item item = roomItemIterator.next();
			if (!(item instanceof Scenery)) {
				roomItems.add(item);
			}
		}

		String items = "a";

		// Generates a String of non-scenery items in this room.
		for (int i = 0; i < roomItems.size(); i++) {
			// If more than 2 item, print "and" before last item
			if (i != 0 && i == roomItems.size() - 1)
				items += " and";
			// If more than 2 items, separate items by a comma
			else if (roomItems.size() > 1 && i > 0 && i < roomItems.size() - 1)
				items += ",";
			Item item = roomItems.get(i);
			items += " " + item.getName();
		}

		if (items.equals("a"))
			return "nothing";
		else
			return items;
	}

	public Item doPut(Item item, Container source) {
		addItem(item);
		source.removeItem(item);		
		World.print("Done!\n\n"); // meaning you put [item] into [container]
		return item;
	}

	public Item doTake(Item item) {
		getWorld().getPlayer().addItem(item);
		this.removeItem(item);		
		World.print("Done!\n\n"); // meaning you put [item] into [container]
		return item;
	}	

	@Override
	public void doUse() {
		World.print("What do you want to do with the " + getName() + "?\n\n");
	}

	@Override
	public void doExamine() {		
		World.print(getDescription() + " Inside the " + getName() + " you see " + getItemString() + ".\n\n");		
	}
}
