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
			String itemName = params[0];
			if (world.getPlayer().getCurrentRoom().hasItem(itemName)) {
				Item item = world.getPlayer().getCurrentRoom().getItem(itemName);
				item.doTake();
			}
			else if (world.getPlayer().hasItem(itemName))
				World.print("You already have that!\n\n");
			else
				World.print("You can't see any " + itemName + " here.\n\n");
		}
		else if (params.length == 3) {
			String itemName = params[0];
			String preposition = params[1];
			String containerName = params[2];
			// Handle errors
			if (!preposition.equals("from")) {
				World.print("I'm not sure what you mean.\n\n");
				return;
			}
			// If the player already has this item in their inventory
			if (world.getPlayer().hasItem(itemName)) {
				World.print("You're already holding the " + itemName + ".\n\n");
				return;
			}

			Item theContainer = world.getPlayer().hasItem(containerName) ? world.getPlayer().getItem(containerName) : world.getPlayer().getCurrentRoom().getItem(containerName);

			// If there is not such container in this room or player's inventory
			if (theContainer == null) {
				World.print("You can't see any " + containerName + " here.\n\n");
				return;
			}

			// If the container isn't actually a Container object
			if (! (theContainer instanceof Container)) {
				World.print("The " + containerName + " can't hold things!\n\n");
				return;
			}
			
			Item theItem = ((Container)theContainer).getItem(itemName);

			// If the container doesn't contain the item requested
			if (theItem == null) {
				World.print("You can't see a " + itemName + " in the " + containerName + ".\n\n");
				return;
			}

			// If the requested item is NOT_TAKEABLE
			if (!theItem.isTakeable()) {
				World.print("The " + itemName + " is part of the " + containerName + " and can't be taken.\n\n");
				return;
			}

			// If we made it this far, then it's safe to take the item from the container
			((Container)theContainer).doTake(theItem, (Container)theContainer);

//			((Container)theContainer).removeItem(itemName);
//			world.getPlayer().addItem(theItem);
//			World.print("Taken.\n\n");
		}
	}

	@Override
	public String getHelpDescription() {
		String descrip = "[item]";
		String[] words = getCommandWords();
		if (words == null || words.length == 0) return "";
		if (words.length > 1) descrip += " (also: ";
		for (int i = 1; i < words.length; i++) {
			descrip += words[i];
			if (i < words.length - 1) descrip += ", ";
		}
		if (words.length > 1) descrip += ")";
		descrip += "\n" + words[0] + "[Item] from [container]";
		return descrip;
	}

}
