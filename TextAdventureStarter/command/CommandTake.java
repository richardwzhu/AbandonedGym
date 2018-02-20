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
				item.doTake(world.getPlayer().getCurrentRoom());
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
		return "[item]";
	}

}
