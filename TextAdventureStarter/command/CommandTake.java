package command;

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
		else {
			World.print("I don't understand.\n\n");
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
