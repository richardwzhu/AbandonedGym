package items;

import interfaces.Closeable;
import textadventure.World;

public class CloseableContainer extends Container implements Closeable{
	private boolean isOpen;
	
	public CloseableContainer(World world, String name, String description, boolean open) {
		super(world, name, description);
		isOpen = open;
	}

	@Override
	public boolean isOpen() {
		return isOpen;
	}

	@Override
	public void doOpen() {
		isOpen = Closeable.OPEN;
		World.print("Opened.\n\n");
	}

	@Override
	public void doClose() {
		isOpen = Closeable.CLOSED;
		World.print("Closed.\n\n");
	}
	
	@Override
	public void doExamine() {		
		if(!isOpen) {
			World.print("The " + this.getName() + " is closed.\n\n");
		}else {
			World.print("Inside the " + this.getName() + " you see " + this.getItemString() + ".\n\n");
		}		
	}
	
	@Override
	public Item doTake(Item item) {
		if(isOpen) {
			super.doTake(item);
			return item;
		}
		World.print("The " + this.getName() + " is closed!\n\n");
		return null;
	}	

	@Override
	public Item doPut(Item item, Container source) {
		if(isOpen) {
			super.doPut(item, source);
			return item;
		}
		World.print("The " + this.getName() + " is closed!\n\n");
		return null;
	}
}
