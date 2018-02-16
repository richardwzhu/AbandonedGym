package command;

import interfaces.Container;
import items.Item;
import textadventure.World;

public class CommandTake extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"take", "get", "grab", "hold"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if (params.length == 1) {
			System.out.println("Section 1");
			String secondWord = params[0];
			if (world.getPlayer().getCurrentRoom().hasItem(secondWord)) {
				Item item = world.getPlayer().getCurrentRoom().getItem(secondWord);
				item.doTake();
			}
			else if (world.getPlayer().hasItem(secondWord))
				World.print("You already have that!\n\n");
			else
				World.print("You can't see any " + secondWord + " here.\n\n");
		}
		else if (params.length == 3) {
			System.out.println("Section 2");
			String item = params[0];
			String preposition = params[1];
			String container = params[2];
			// Handle errors
			if (!preposition.equals("from")) {
				World.print("I'm not sure what you mean.\n\n");
				return;
			}
			// If the player already has this item in their inventory
			if (world.getPlayer().hasItem(item)) {
				World.print("You're already holding the " + item + ".\n\n");
				return;
			}

			Item theContainer = world.getPlayer().hasItem(container) ? world.getPlayer().getItem(container) : world.getPlayer().getCurrentRoom().getItem(container);

			// If there is not such container in this room or player's inventory
			if (theContainer == null) {
				World.print("You can't see any " + container + " here.\n\n");
				return;
			}

			// If the container isn't actually a Container object
			if (! (theContainer instanceof Container)) {
				World.print("The " + container + " can't hold things!\n\n");
				return;
			}
			
			Item theItem = ((Container)theContainer).getItem(item);

			// If the container doesn't contain the item requested
			if (theItem == null) {
				World.print("You can't see a " + item + " in the " + container + ".\n\n");
				return;
			}

			// If the requested item is NOT_TAKEABLE
			if (!theItem.isTakeable()) {
				World.print("The " + item + " is part of the " + container + " and can't be taken.\n\n");
				return;
			}

			// If we made it this far, then it's safe to take the item from the container
			((Container)theContainer).removeItem(item);
			world.getPlayer().addItem(theItem);
			World.print("Taken.\n\n");
		}
	}

	@Override
	public String getHelpDescription() {
		return "[item] or take [Item] from [Container]";
	}

}
