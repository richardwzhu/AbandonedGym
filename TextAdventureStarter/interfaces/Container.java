package interfaces;
import items.Item;

public interface Container {

    /** Adds an Item to this Container. */
    public void addItem(Item item);

    /** Removes and returns an Item from this Container, given only its name. */
    public Item removeItem(String itemName);

    /** Removes and return an Item from this Container. */
    public Item removeItem(Item item);

    /** Moves an Item from the specified Container to this Container. */
    public Item doTake(Item item, Container container);

    /** Returns an Item held by this Container or null if not found. */
    public Item getItem(String itemName);

	/** Returns whether this Container has an item or not */
	public boolean hasItem(Item item);

	/** Returns whether this Container has an item or not by knowing only the Item's String name */
    public boolean hasItem(String itemName);

    /** Returns a String listing of the Items in this Container */
	public String getItemString();
}
